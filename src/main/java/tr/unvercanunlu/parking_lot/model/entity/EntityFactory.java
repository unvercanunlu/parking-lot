package tr.unvercanunlu.parking_lot.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import tr.unvercanunlu.parking_lot.model.constant.ParkingStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityFactory {

  public static Ticket createTicket(long ticketId, String vehiclePlate, int spotNo) {
    Ticket ticket = new Ticket(ticketId, vehiclePlate, spotNo);

    ticket.setStartedAt(LocalDateTime.now());
    ticket.setStatus(ParkingStatus.ON_GOING);
    ticket.setPrice(BigDecimal.ZERO);

    return ticket;
  }

}
