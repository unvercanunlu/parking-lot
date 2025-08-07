package tr.unvercanunlu.parking_lot.service.impl;

import tr.unvercanunlu.parking_lot.config.AppConfig;
import tr.unvercanunlu.parking_lot.exception.InvalidException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import tr.unvercanunlu.parking_lot.service.CalculationService;

@Slf4j
public class CalculationServiceImpl implements CalculationService {

  @Override
  public BigDecimal calculateParkingPrice(LocalDateTime startedAt, LocalDateTime endedAt) {
    if ((startedAt == null) || (endedAt == null) || startedAt.isAfter(endedAt)) {
      log.error("Date invalid: start {} and end {}", startedAt, endedAt);
      throw new InvalidException("Date", String.format("start=%s end=%s", startedAt, endedAt));
    }

    Duration parkingDuration = Duration.between(startedAt, endedAt);
    long parkingDays = parkingDuration.toDays();
    long parkingHoursToday = parkingDuration.toHoursPart();

    BigDecimal parkingPrice = BigDecimal.valueOf(AppConfig.PRICE_DAILY_MAX * parkingDays);
    double parkingPriceToday = AppConfig.PRICE_START + (parkingHoursToday * AppConfig.PRICE_PER_HOUR);
    parkingPriceToday = Math.max(parkingPriceToday, AppConfig.PRICE_DAILY_MAX);
    parkingPrice = parkingPrice.add(BigDecimal.valueOf(parkingPriceToday));

    parkingPrice = parkingPrice.setScale(2, RoundingMode.HALF_UP);

    log.info("Parking price calculated: {} start {} and end{}", parkingPrice, startedAt, endedAt);

    return parkingPrice;
  }

}
