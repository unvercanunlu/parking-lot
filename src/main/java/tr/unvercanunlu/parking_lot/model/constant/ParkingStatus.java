package tr.unvercanunlu.parking_lot.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParkingStatus {

  ON_GOING('S'),
  ENDED('E');

  @Getter
  private final char code;

}
