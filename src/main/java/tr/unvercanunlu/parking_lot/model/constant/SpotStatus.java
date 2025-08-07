package tr.unvercanunlu.parking_lot.model.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpotStatus {

  EMPTY('E'),
  OCCUPIED('O');

  @Getter
  private final char code;

  private static final Map<Character, SpotStatus> STATUSES;

  static {
    Map<Character, SpotStatus> map = new HashMap<>();

    for (SpotStatus status : SpotStatus.values()) {
      map.put(status.code, status);
    }

    STATUSES = Collections.unmodifiableMap(map);
  }

  public static Optional<SpotStatus> of(Character code) {
    if (code == null) {
      throw new IllegalArgumentException("Code missing!");
    }

    return Optional.ofNullable(
        STATUSES.get(code)
    );
  }

}
