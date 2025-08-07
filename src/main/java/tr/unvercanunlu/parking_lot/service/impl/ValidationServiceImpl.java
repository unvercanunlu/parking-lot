package tr.unvercanunlu.parking_lot.service.impl;

import java.util.regex.Pattern;
import tr.unvercanunlu.parking_lot.config.AppConfig;
import tr.unvercanunlu.parking_lot.exception.InvalidException;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.service.ValidationService;

@Slf4j
public class ValidationServiceImpl implements ValidationService {

  @Override
  public void validateCarPlate(String carPlate) throws InvalidException {
    if ((carPlate == null) || carPlate.isEmpty()|| !Pattern.matches(AppConfig.CAR_PLATE_REGEX,carPlate)) {
      log.error("Car Plate invalid: {}", carPlate);
      throw new InvalidException("Car Plate", carPlate);
    }
  }

  @Override
  public void validateParkingRecordId(long recordId) throws InvalidException {
    if (recordId < AppConfig.RECORD_ID_START) {
      log.error("Record ID invalid: {}", recordId);
      throw new InvalidException("Record ID", recordId);
    }
  }

  @Override
  public void validateSpotNo(int spotNo) throws InvalidException {
    if (spotNo < AppConfig.SPOT_NO_START) {
      log.error("Spot No invalid: {}", spotNo);
      throw new InvalidException("Spot No", spotNo);
    }
  }

}
