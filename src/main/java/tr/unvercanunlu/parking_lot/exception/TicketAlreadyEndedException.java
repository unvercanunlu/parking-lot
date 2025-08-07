package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class TicketAlreadyEndedException extends RuntimeException {

  @Getter
  private final long ticketId;

  public TicketAlreadyEndedException(long ticketId) {
    super("Ticket already ended: ticketId=%d".formatted(ticketId));

    this.ticketId = ticketId;
  }

}
