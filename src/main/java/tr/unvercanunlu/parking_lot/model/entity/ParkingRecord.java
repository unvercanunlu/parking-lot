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
@Getter
@RequiredArgsConstructor
public class ParkingRecord {

  private final long id;
  private final String carPlate;
  private final int spotNo;

  @Setter
  private LocalDateTime startedAt = LocalDateTime.now();

  @Setter
  private LocalDateTime endedAt = null;

  @Setter
  private ParkingStatus status = ParkingStatus.ON_GOING;

  @Setter
  private BigDecimal price = BigDecimal.ZERO;

}
