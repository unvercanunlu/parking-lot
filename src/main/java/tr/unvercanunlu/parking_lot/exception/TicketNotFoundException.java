package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class TicketNotFoundException extends RuntimeException {

  @Getter
  private final long ticketId;

  public TicketNotFoundException(long ticketId) {
    super("Ticket not found: ticketId=%d".formatted(ticketId));

    this.ticketId = ticketId;
  }

}
