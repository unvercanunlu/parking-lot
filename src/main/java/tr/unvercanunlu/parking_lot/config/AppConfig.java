package tr.unvercanunlu.parking_lot.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConfig {

  public static final double PRICE_START = 1.0;
  public static final double PRICE_PER_HOUR = 2.0;
  public static final double PRICE_DAILY_MAX = 40.0;
  public static final String CAR_PLATE_REGEX = "^[A-Za-z0-9]+(-[A-Za-z0-9]+)*$";
  public static final int SPOT_NO_START = 1;
  public static final long RECORD_ID_START = 1;

}
