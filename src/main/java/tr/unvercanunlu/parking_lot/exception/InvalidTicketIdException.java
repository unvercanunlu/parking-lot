package tr.unvercanunlu.parking_lot.exception;

import lombok.Getter;

public class InvalidTicketIdException extends RuntimeException {

  @Getter
  private final long ticketId;

  public InvalidTicketIdException(long ticketId) {
    super("Ticket ID invalid: ticketId=%s".formatted(ticketId));

    this.ticketId = ticketId;
  }

}
