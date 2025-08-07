package tr.unvercanunlu.parking_lot.service;

import tr.unvercanunlu.parking_lot.exception.InvalidSpotNoException;
import tr.unvercanunlu.parking_lot.exception.InvalidTicketIdException;
import tr.unvercanunlu.parking_lot.exception.InvalidVehiclePlateException;

public interface ValidationService {

  void validateVehiclePlate(String vehiclePlate) throws InvalidVehiclePlateException;

  void validateTicketId(long ticketId) throws InvalidTicketIdException;

  void validateSpotNo(int spotNo) throws InvalidSpotNoException;

}
