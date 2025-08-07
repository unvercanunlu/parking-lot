package tr.unvercanunlu.parking_lot.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConfig {

  // price
  public static final double PRICE_START = 1.0;
  public static final double PRICE_PER_HOUR = 2.0;
  public static final double PRICE_DAILY_MAX = 40.0;

  // car plate
  public static final String CAR_PLATE_REGEX = "^[A-Za-z0-9]+(-[A-Za-z0-9]+)*$";

  // spot
  public static final int SPOT_NO_START = 1;

  // record
  public static final long RECORD_ID_START = 1;

}
