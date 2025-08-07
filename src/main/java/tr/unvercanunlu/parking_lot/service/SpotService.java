package tr.unvercanunlu.parking_lot.service;

import tr.unvercanunlu.parking_lot.model.entity.ParkingSpot;

public interface SpotService {

  ParkingSpot add();

  ParkingSpot getByNo(int spotNo);

  void markEmpty(int spotNo);

  void markOccupied(int spotNo);

  boolean checkExistsByNo(int spotNo);

}
