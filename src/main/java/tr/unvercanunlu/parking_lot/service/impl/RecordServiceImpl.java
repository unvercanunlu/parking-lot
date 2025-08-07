package tr.unvercanunlu.parking_lot.service.impl;

import tr.unvercanunlu.parking_lot.exception.NotExistException;
import tr.unvercanunlu.parking_lot.exception.NotFoundException;
import tr.unvercanunlu.parking_lot.exception.RecordAlreadyEndedException;
import tr.unvercanunlu.parking_lot.exception.SpotNotEmptyException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.model.constant.ParkingStatus;
import tr.unvercanunlu.parking_lot.model.constant.SpotStatus;
import tr.unvercanunlu.parking_lot.model.entity.EntityFactory;
import tr.unvercanunlu.parking_lot.model.entity.ParkingRecord;
import tr.unvercanunlu.parking_lot.model.entity.ParkingSpot;
import tr.unvercanunlu.parking_lot.repository.InMemoryDatabase;
import tr.unvercanunlu.parking_lot.service.CalculationService;
import tr.unvercanunlu.parking_lot.service.RecordService;
import tr.unvercanunlu.parking_lot.service.ValidationService;

@Slf4j
public class RecordServiceImpl implements RecordService {

  private final ValidationService validationService;
  private final CalculationService calculationService;

  public RecordServiceImpl(
      ValidationService validationService,
      CalculationService calculationService
  ) {
    this.validationService = validationService;
    this.calculationService = calculationService;
  }

  @Override
  public ParkingRecord getById(long recordId) throws NotFoundException {
    validationService.validateParkingRecordId(recordId);

    if (!checkExistsById(recordId)) {
      log.error("Record with ID {} not found", recordId);
      throw new NotFoundException("Record", recordId);
    }

    return InMemoryDatabase.RECORDS.get(recordId);
  }

  @Override
  public ParkingRecord start(int spotNo, String carPlate) throws NotExistException {
    validationService.validateCarPlate(carPlate);
    validationService.validateSpotNo(spotNo);

    if (!checkSpotExistsByNo(spotNo)) {
      log.error("Spot with No {} not exist", spotNo);
      throw new NotExistException("Spot", spotNo);
    }

    ParkingSpot spot = InMemoryDatabase.SPOTS.get(spotNo);

    if ((spot.getStatus() == null) || !spot.getStatus().equals(SpotStatus.EMPTY)) {
      log.error("Spot with No {} not empty", spotNo);
      throw new SpotNotEmptyException(spotNo);
    }

    spot.setStatus(SpotStatus.OCCUPIED);

    InMemoryDatabase.SPOTS.put(spot.getNo(), spot);

    log.info("Spot with No {} set as occupied", spotNo);

    ParkingRecord record = EntityFactory.createRecord(
        InMemoryDatabase.RECORD_COUNTER.getAndIncrement(),
        carPlate,
        spotNo
    );

    InMemoryDatabase.RECORDS.put(record.getId(), record);

    log.info("Record with ID {} started", spotNo);

    return record;
  }

  @Override
  public ParkingRecord end(long recordId) {
    ParkingRecord record = getById(recordId);

    if (record.getEndedAt() != null) {
      log.error("Record with ID {} already ended", recordId);
      throw new RecordAlreadyEndedException(record.getId());
    }

    LocalDateTime now = LocalDateTime.now();

    BigDecimal parkingPrice = calculationService.calculateParkingPrice(record.getStartedAt(), now);

    record.setPrice(parkingPrice);
    record.setEndedAt(now);
    record.setStatus(ParkingStatus.ENDED);

    InMemoryDatabase.RECORDS.put(record.getId(), record);

    log.info("Record with ID {} ended", record.getId());

    if (checkSpotExistsByNo(record.getSpotNo())) {
      InMemoryDatabase.SPOTS.get(record.getSpotNo())
          .setStatus(SpotStatus.EMPTY);

      log.info("Spot with No {} set as empty", record.getSpotNo());
    }

    return record;
  }

  @Override
  public List<ParkingRecord> getBySpot(int spotNo) throws NotExistException {
    validationService.validateSpotNo(spotNo);

    if (!checkSpotExistsByNo(spotNo)) {
      log.error("Spot with No {} not exist", spotNo);
      throw new NotExistException("Spot", spotNo);
    }

    List<ParkingRecord> records = InMemoryDatabase.RECORDS.values()
        .stream()
        .filter(record -> record.getSpotNo() == spotNo)
        .collect(Collectors.toList());

    log.info("{} Record(s) found for Spot with No {}", records.size(), spotNo);

    return records;
  }

  @Override
  public List<ParkingRecord> getByCar(String carPlate) {
    validationService.validateCarPlate(carPlate);

    List<ParkingRecord> records = InMemoryDatabase.RECORDS.values()
        .stream()
        .filter(record -> (record != null)
            && (record.getCarPlate() != null)
            && carPlate.equals(record.getCarPlate()))
        .collect(Collectors.toList());

    log.info("{} Record(s) found by Car with Plate {}", records.size(), carPlate);

    return records;
  }

  @Override
  public List<ParkingRecord> getOngoingRecords() {
    List<ParkingRecord> records = InMemoryDatabase.RECORDS.values()
        .stream()
        .filter(record -> (record != null)
            && (record.getStatus() != null)
            && record.getStatus().equals(ParkingStatus.ON_GOING))
        .collect(Collectors.toList());

    log.info("{} ongoing Record(s) found", records.size());

    return records;
  }

  @Override
  public List<ParkingRecord> getEndedRecords() {
    List<ParkingRecord> records = InMemoryDatabase.RECORDS.values()
        .stream()
        .filter(record -> (record != null)
            && (record.getStatus() != null)
            && record.getStatus().equals(ParkingStatus.ENDED))
        .collect(Collectors.toList());

    log.info("{} ended Record(s) found", records.size());

    return records;
  }

  @Override
  public boolean checkExistsById(long recordId) {
    return InMemoryDatabase.RECORDS.containsKey(recordId);
  }

  private boolean checkSpotExistsByNo(int no) {
    return InMemoryDatabase.SPOTS.containsKey(no);
  }

}
