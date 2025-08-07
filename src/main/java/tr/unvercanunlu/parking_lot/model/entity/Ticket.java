package tr.unvercanunlu.parking_lot.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.unvercanunlu.parking_lot.model.constant.ParkingStatus;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Ticket {

  @Getter
  private final long id;

  @Getter
  private final String vehiclePlate;

  @Getter
  private final int spotNo;

  @Getter
  @Setter
  private LocalDateTime startedAt = LocalDateTime.now();

  @Getter
  @Setter
  private LocalDateTime endedAt = null;

  @Getter
  @Setter
  private ParkingStatus status = ParkingStatus.ON_GOING;

  @Getter
  @Setter
  private BigDecimal price = BigDecimal.ZERO;

}
