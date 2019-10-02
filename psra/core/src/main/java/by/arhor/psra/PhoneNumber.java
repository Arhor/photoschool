package by.arhor.psra;

import java.util.Objects;
import java.util.StringJoiner;

public class PhoneNumber {

  private final short code;
  private final short prefix;
  private final int number;

  public PhoneNumber(short code, short prefix, int number) {
    this.code = code;
    this.prefix = prefix;
    this.number = number;
  }

  public short getCode() {
    return code;
  }

  public short getPrefix() {
    return prefix;
  }

  public int getNumber() {
    return number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber that = (PhoneNumber) o;
    return code == that.code
        && prefix == that.prefix
        && number == that.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, prefix, number);
  }

  @Override
  public String toString() {
    return new StringJoiner("-", PhoneNumber.class.getSimpleName() + "[", "]")
        .add("+" + code)
        .add("(" + prefix + ")")
        .add("" + number)
        .toString();
  }
}
