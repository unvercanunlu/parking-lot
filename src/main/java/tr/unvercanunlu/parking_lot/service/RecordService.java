package tr.unvercanunlu.parking_lot.service;

import tr.unvercanunlu.parking_lot.exception.NotExistException;
import java.util.List;
import tr.unvercanunlu.parking_lot.model.entity.ParkingRecord;

public interface RecordService {

  ParkingRecord getById(long recordId);

  ParkingRecord start(int spotNo, String carPlate) throws NotExistException;

  ParkingRecord end(long recordId);

  List<ParkingRecord> getBySpot(int spotNo) throws NotExistException;

  List<ParkingRecord> getByCar(String carPlate);

  List<ParkingRecord> getOngoingRecords();

  List<ParkingRecord> getEndedRecords();

  boolean checkExistsById(long recordId);

}
