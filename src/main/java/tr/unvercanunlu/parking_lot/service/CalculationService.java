package tr.unvercanunlu.parking_lot.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CalculationService {

  BigDecimal calculateParkingPrice(LocalDateTime startedAt, LocalDateTime endedAt);

}
