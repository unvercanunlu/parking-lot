package tr.unvercanunlu.parking_lot.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import tr.unvercanunlu.parking_lot.model.constant.ParkingStatus;
import tr.unvercanunlu.parking_lot.model.constant.SpotStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityFactory {

  public static ParkingSpot createSpot(int spotNo) {
    ParkingSpot spot = new ParkingSpot(spotNo);

    spot.setStatus(SpotStatus.EMPTY);

    return spot;
  }

  public static ParkingRecord createRecord(long id, String carPlate, int spotNo) {
    ParkingRecord record = new ParkingRecord(id, carPlate, spotNo);

    record.setStartedAt(LocalDateTime.now());
    record.setStatus(ParkingStatus.ON_GOING);
    record.setPrice(BigDecimal.ZERO);

    return record;
  }

}
