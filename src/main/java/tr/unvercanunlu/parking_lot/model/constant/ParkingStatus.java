package tr.unvercanunlu.parking_lot.model.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParkingStatus {

  ON_GOING('S'),
  ENDED('E');

  @Getter
  private final char code;

  private static final Map<Character, ParkingStatus> STATUSES;

  static {
    Map<Character, ParkingStatus> map = new HashMap<>();

    for (ParkingStatus status : ParkingStatus.values()) {
      map.put(status.code, status);
    }

    STATUSES = Collections.unmodifiableMap(map);
  }

  public static Optional<ParkingStatus> of(Character code) {
    if (code == null) {
      throw new IllegalArgumentException("Code missing!");
    }

    return Optional.ofNullable(
        STATUSES.get(code)
    );
  }

}
