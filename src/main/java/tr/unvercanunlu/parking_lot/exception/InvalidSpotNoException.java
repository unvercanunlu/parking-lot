package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class InvalidSpotNoException extends RuntimeException {

  @Getter
  private final int spotNo;

  public InvalidSpotNoException(int spotNo) {
    super("Spot No invalid: spotNo=%d".formatted(spotNo));

    this.spotNo = spotNo;
  }

}
