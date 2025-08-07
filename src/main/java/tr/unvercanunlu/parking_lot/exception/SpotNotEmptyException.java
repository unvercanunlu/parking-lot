package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class SpotNotEmptyException extends RuntimeException {

  @Getter
  private final int spotNo;

  public SpotNotEmptyException(int spotNo) {
    super(String.format("Spot not empty: %d", spotNo));

    this.spotNo = spotNo;
  }

}
