package by.arhor.psra;

import java.util.Arrays;

public enum CountryCode {

  BY(375), RU(7);

  public static CountryCode parse(int code) {
    return Arrays
        .stream(values())
        .filter(c -> code == c.value)
        .findFirst()
        .orElseThrow();
  }

  private final short value;

  CountryCode(int value) {
    this.value = (short) value;
  }

  public short getValue() {
    return value;
  }
}
