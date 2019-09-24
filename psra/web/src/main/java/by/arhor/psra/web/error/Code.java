package by.arhor.psra.web.error;

import java.util.Objects;

public enum Code {
  NOT_FOUND         ("10001"),
  INVALID_JSON      ("10002");

  public static Code fromString(String s) {
    for (Code code : values()) {
      if (code.value.equals(s)) {
        return code;
      }
    }
    throw new IllegalArgumentException("Unknown code: " + s);
  }

  private final String value;

  Code(String value) {
    Objects.requireNonNull(value, "Code value must not be null");
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
