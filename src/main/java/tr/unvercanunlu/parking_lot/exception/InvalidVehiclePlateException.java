package tr.unvercanunlu.parking_lot.exception;

import java.util.Objects;
import lombok.Getter;

public class InvalidVehiclePlateException extends RuntimeException {

  @Getter
  private final String vehiclePlate;

  public InvalidVehiclePlateException(String vehiclePlate) {
    super("Vehicle Plate invalid: vehiclePlate=%s".formatted(Objects.toString(vehiclePlate, "null")));

    this.vehiclePlate = vehiclePlate;
  }

}
