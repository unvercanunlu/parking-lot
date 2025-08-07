package tr.unvercanunlu.parking_lot.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tr.unvercanunlu.parking_lot.config.AppConfig;
import tr.unvercanunlu.parking_lot.model.entity.ParkingRecord;
import tr.unvercanunlu.parking_lot.model.entity.ParkingSpot;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDatabase {

  // to store entities
  public static final ConcurrentMap<Integer, ParkingSpot> SPOTS = new ConcurrentHashMap<>();
  public static final ConcurrentMap<Long, ParkingRecord> RECORDS = new ConcurrentHashMap<>();

  // to generate id for entities properly
  public static final AtomicInteger SPOT_COUNTER = new AtomicInteger(AppConfig.SPOT_NO_START);
  public static final AtomicLong RECORD_COUNTER = new AtomicLong(AppConfig.RECORD_ID_START);

}
