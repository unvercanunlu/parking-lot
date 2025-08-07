package tr.unvercanunlu.parking_lot.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.unvercanunlu.parking_lot.model.constant.SpotStatus;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class ParkingSpot {

  private final int no;

  @Setter
  private SpotStatus status = SpotStatus.EMPTY;

}
