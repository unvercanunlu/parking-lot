package tr.unvercanunlu.parking_lot.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tr.unvercanunlu.parking_lot.config.AppConfig;
import tr.unvercanunlu.parking_lot.model.entity.Ticket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDatabase {

  // to store entities
  public static final ConcurrentMap<Long, Ticket> TICKETS = new ConcurrentHashMap<>();

  // to generate id for entities properly
  public static final AtomicLong TICKET_ID_COUNTER = new AtomicLong(AppConfig.TICKET_ID_START);

}
