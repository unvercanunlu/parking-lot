package tr.unvercanunlu.parking_lot.exception;

import java.util.Objects;
import lombok.Getter;

public class NotFoundException extends RuntimeException {

  @Getter
  private final String type;

  @Getter
  private final Object value;

  public NotFoundException(String type, Object value) {
    super(String.format("%s not found: %s", type, Objects.toString(value, "null")));

    this.type = type;
    this.value = value;
  }

}
