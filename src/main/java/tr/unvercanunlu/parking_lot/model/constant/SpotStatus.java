package tr.unvercanunlu.parking_lot.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpotStatus {

  EMPTY('E'),
  OCCUPIED('O');

  @Getter
  private final char code;

}
