package tr.unvercanunlu.parking_lot.exception;

import java.util.Objects;
import lombok.Getter;

public class NotExistException extends RuntimeException {

  @Getter
  private final String type;

  @Getter
  private final Object value;

  public NotExistException(String type, Object value) {
    super(String.format("%s not exist: %s", type, Objects.toString(value, "null")));

    this.type = type;
    this.value = value;
  }

}
