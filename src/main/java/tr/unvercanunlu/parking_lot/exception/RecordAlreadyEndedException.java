package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class RecordAlreadyEndedException extends RuntimeException {

  @Getter
  private final long recordId;

  public RecordAlreadyEndedException(long recordId) {
    super(String.format("Record already ended: %d", recordId));

    this.recordId = recordId;
  }

}
