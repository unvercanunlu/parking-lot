package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class SpotOccupiedException extends RuntimeException {

  @Getter
  private final int spotNo;

  public SpotOccupiedException(int spotNo) {
    super("Spot No occupied: spotNo=%d".formatted(spotNo));

    this.spotNo = spotNo;
  }

}
