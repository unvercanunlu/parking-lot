package tr.unvercanunlu.parking_lot.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.exception.SpotOccupiedException;
import tr.unvercanunlu.parking_lot.exception.TicketAlreadyEndedException;
import tr.unvercanunlu.parking_lot.exception.TicketNotFoundException;
import tr.unvercanunlu.parking_lot.model.constant.ParkingStatus;
import tr.unvercanunlu.parking_lot.model.entity.EntityFactory;
import tr.unvercanunlu.parking_lot.model.entity.Ticket;
import tr.unvercanunlu.parking_lot.repository.InMemoryDatabase;
import tr.unvercanunlu.parking_lot.service.CalculationService;
import tr.unvercanunlu.parking_lot.service.TicketService;
import tr.unvercanunlu.parking_lot.service.ValidationService;

@Slf4j
public class TicketServiceImpl implements TicketService {

  private final ValidationService validationService;
  private final CalculationService calculationService;

  public TicketServiceImpl(
      ValidationService validationService,
      CalculationService calculationService
  ) {
    this.validationService = validationService;
    this.calculationService = calculationService;
  }

  @Override
  public Optional<Ticket> getById(long ticketId) {
    validationService.validateTicketId(ticketId);

    return Optional.ofNullable(
        InMemoryDatabase.TICKETS.get(ticketId)
    );
  }

  @Override
  public Ticket start(int spotNo, String vehiclePlate) {
    validationService.validateVehiclePlate(vehiclePlate);
    validationService.validateSpotNo(spotNo);

    LocalDateTime now = LocalDateTime.now();

    long occupiedSpotCount = InMemoryDatabase.TICKETS.values()
        .stream()
        .filter(ticket -> (ticket != null)
            && (ticket.getSpotNo() == spotNo)
            && ticket.getStartedAt().isBefore(now)
            && ((ticket.getEndedAt() == null) || ticket.getEndedAt().isAfter(now)))
        .count();

    if (occupiedSpotCount > 0) {
      log.error("Spot No occupied: spotNo=%d".formatted(spotNo));

      throw new SpotOccupiedException(spotNo);
    }

    Ticket ticket = EntityFactory.createTicket(
        InMemoryDatabase.TICKET_ID_COUNTER.getAndIncrement(),
        vehiclePlate,
        spotNo
    );

    InMemoryDatabase.TICKETS.put(ticket.getId(), ticket);

    log.info("Ticket with ID {} started", spotNo);

    return ticket;
  }

  @Override
  public Ticket end(long ticketId) {
    Optional<Ticket> optionalTicket = getById(ticketId);

    if (optionalTicket.isEmpty()) {
      log.error("Ticket with ID {} not found", ticketId);

      throw new TicketNotFoundException(ticketId);
    }

    Ticket ticket = optionalTicket.get();

    if (ticket.getEndedAt() != null) {
      log.error("Ticket with ID {} already ended", ticketId);

      throw new TicketAlreadyEndedException(ticket.getId());
    }

    LocalDateTime now = LocalDateTime.now();

    BigDecimal parkingPrice = calculationService.calculateParkingPrice(ticket.getStartedAt(), now);

    ticket.setPrice(parkingPrice);
    ticket.setEndedAt(now);
    ticket.setStatus(ParkingStatus.ENDED);

    InMemoryDatabase.TICKETS.put(ticket.getId(), ticket);

    log.info("Ticket with ID {} ended", ticket.getId());

    return ticket;
  }

  @Override
  public List<Ticket> getBySpot(int spotNo) {
    validationService.validateSpotNo(spotNo);

    List<Ticket> tickets = InMemoryDatabase.TICKETS.values()
        .stream()
        .filter(ticket -> (ticket != null)
            && (ticket.getSpotNo() == spotNo))
        .toList();

    log.info("{} Ticket(s) found for Spot with No {}", tickets.size(), spotNo);

    return tickets;
  }

  @Override
  public List<Ticket> getByVehicle(String vehiclePlate) {
    validationService.validateVehiclePlate(vehiclePlate);

    List<Ticket> tickets = InMemoryDatabase.TICKETS.values()
        .stream()
        .filter(ticket -> (ticket != null)
            && (ticket.getVehiclePlate() != null)
            && vehiclePlate.equals(ticket.getVehiclePlate()))
        .toList();

    log.info("{} Ticket(s) found by Vehicle with Plate {}", tickets.size(), vehiclePlate);

    return tickets;
  }

  @Override
  public List<Ticket> getOngoingTickets() {
    List<Ticket> tickets = InMemoryDatabase.TICKETS.values()
        .stream()
        .filter(ticket -> (ticket != null)
            && (ticket.getStatus() != null)
            && ticket.getStatus().equals(ParkingStatus.ON_GOING))
        .toList();

    log.info("{} ongoing Ticket(s) found", tickets.size());

    return tickets;
  }

  @Override
  public List<Ticket> getEndedTickets() {
    List<Ticket> tickets = InMemoryDatabase.TICKETS.values()
        .stream()
        .filter(ticket -> (ticket != null)
            && (ticket.getStatus() != null)
            && ticket.getStatus().equals(ParkingStatus.ENDED))
        .toList();

    log.info("{} ended Ticket(s) found", tickets.size());

    return tickets;
  }

  @Override
  public boolean checkExistsById(long ticketId) {
    validationService.validateTicketId(ticketId);

    return InMemoryDatabase.TICKETS.containsKey(ticketId);
  }

}
