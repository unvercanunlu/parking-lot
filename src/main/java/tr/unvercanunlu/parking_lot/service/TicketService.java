package tr.unvercanunlu.parking_lot.service;

import java.util.List;
import java.util.Optional;
import tr.unvercanunlu.parking_lot.model.entity.Ticket;

public interface TicketService {

  Optional<Ticket> getById(long ticketId);

  Ticket start(int spotNo, String vehiclePlate);

  Ticket end(long ticketId);

  List<Ticket> getBySpot(int spotNo);

  List<Ticket> getByVehicle(String vehiclePlate);

  List<Ticket> getOngoingTickets();

  List<Ticket> getEndedTickets();

  boolean checkExistsById(long ticketId);

}
