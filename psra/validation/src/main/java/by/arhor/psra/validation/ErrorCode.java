package by.arhor.psra.validation;

public enum ErrorCode implements Code {

  FAILED_NULL_CHECK      (100),
  FAILED_POSITIVE_CHECK  (101),
  FAILED_LENGTH_CHECK    (102),
  FAILED_DECIMAL_CHECK   (103),
  FAILED_EMPTY_CHECK     (104),
  FAILED_PATTERN_CHECK   (105);

  public final int value;

  ErrorCode(int value) {
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }
}
