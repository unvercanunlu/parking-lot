package tr.unvercanunlu.parking_lot.service.impl;

import java.util.Objects;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.config.AppConfig;
import tr.unvercanunlu.parking_lot.exception.InvalidSpotNoException;
import tr.unvercanunlu.parking_lot.exception.InvalidTicketIdException;
import tr.unvercanunlu.parking_lot.exception.InvalidVehiclePlateException;
import tr.unvercanunlu.parking_lot.service.ValidationService;

@Slf4j
public class ValidationServiceImpl implements ValidationService {

  @Override
  public void validateVehiclePlate(String vehiclePlate) throws InvalidVehiclePlateException {
    if ((vehiclePlate == null) || vehiclePlate.isEmpty() || !Pattern.matches(AppConfig.VEHICLE_PLATE_REGEX, vehiclePlate)) {
      log.error("Vehicle Plate invalid: {}", Objects.toString(vehiclePlate, "null"));

      throw new InvalidVehiclePlateException(vehiclePlate);
    }
  }

  @Override
  public void validateTicketId(long ticketId) throws InvalidTicketIdException {
    if (ticketId < AppConfig.TICKET_ID_START) {
      log.error("Ticket ID invalid: {}", ticketId);

      throw new InvalidTicketIdException(ticketId);
    }
  }

  @Override
  public void validateSpotNo(int spotNo) throws InvalidSpotNoException {
    if ((spotNo < AppConfig.SPOT_NO_START) || (spotNo > AppConfig.SPOT_NO_END)) {
      log.error("Spot No invalid: {}", spotNo);

      throw new InvalidSpotNoException(spotNo);
    }
  }

}
