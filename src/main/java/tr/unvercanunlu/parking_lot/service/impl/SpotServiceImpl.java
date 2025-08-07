package tr.unvercanunlu.parking_lot.service.impl;

import tr.unvercanunlu.parking_lot.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.model.constant.SpotStatus;
import tr.unvercanunlu.parking_lot.model.entity.EntityFactory;
import tr.unvercanunlu.parking_lot.model.entity.ParkingSpot;
import tr.unvercanunlu.parking_lot.repository.InMemoryDatabase;
import tr.unvercanunlu.parking_lot.service.SpotService;
import tr.unvercanunlu.parking_lot.service.ValidationService;

@Slf4j
public class SpotServiceImpl implements SpotService {

  private final ValidationService validationService;

  public SpotServiceImpl(ValidationService validationService) {
    this.validationService = validationService;
  }

  @Override
  public ParkingSpot getByNo(int spotNo) throws NotFoundException {
    validationService.validateSpotNo(spotNo);

    if (!checkExistsByNo(spotNo)) {
      log.error("Spot with No {} not found", spotNo);
      throw new NotFoundException("Spot", spotNo);
    }

    return InMemoryDatabase.SPOTS.get(spotNo);
  }

  @Override
  public void markEmpty(int spotNo) {
    ParkingSpot spot = getByNo(spotNo);

    spot.setStatus(SpotStatus.EMPTY);

    InMemoryDatabase.SPOTS.put(spotNo, spot);

    log.info("Spot with No {} set as empty", spotNo);
  }

  @Override
  public void markOccupied(int spotNo) {
    ParkingSpot spot = getByNo(spotNo);

    spot.setStatus(SpotStatus.OCCUPIED);

    InMemoryDatabase.SPOTS.put(spotNo, spot);

    log.info("Spot with No {} set as occupied", spotNo);
  }

  @Override
  public ParkingSpot add() {
    ParkingSpot spot = EntityFactory.createSpot(InMemoryDatabase.SPOT_COUNTER.getAndIncrement());

    InMemoryDatabase.SPOTS.put(spot.getNo(), spot);

    log.info("Spot with No {} added", spot.getNo());

    return spot;
  }

  @Override
  public boolean checkExistsByNo(int spotNo) {
    return InMemoryDatabase.SPOTS.containsKey(spotNo);
  }

}
