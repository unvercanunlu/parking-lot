package tr.unvercanunlu.parking_lot.service;

import tr.unvercanunlu.parking_lot.exception.InvalidException;

public interface ValidationService {

  void validateCarPlate(String carPlate) throws InvalidException;

  void validateParkingRecordId(long id) throws InvalidException;

  void validateSpotNo(int spotNo) throws InvalidException;

}
