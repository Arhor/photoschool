package by.arhor.psra.validation.exception;

import by.arhor.psra.validation.Code;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents error-status object. Contains error code, Class and Field names,
 * where the error occurred.
 *
 * @author Maksim Buryshynets
 * @version 1.0 20 January 2019
 */
public class Status {

  public static final int FAILED_NULL_CHECK         = 100;
  public static final int FAILED_POSITIVE_CHECK     = 101;
  public static final int FAILED_LENGTH_CHECK       = 102;
  public static final int FAILED_DECIMAL_CHECK      = 103;
  public static final int FAILED_EMPTY_CHECK        = 104;
  public static final int FAILED_PATTERN_CHECK      = 105;

  private final Code code;
  private final String className;
  private final String fieldName;
  private Object[] optionalParams;

  public Status(Code code, String className, String fieldName, Object[] optionalParams) {
    this.code = code;
    this.className = className;
    this.fieldName = fieldName;
    this.optionalParams = optionalParams;
  }

  public Status(Code code, String className, String fieldName) {
    this(code, className, fieldName, null);
  }

  public Code getCode() {
    return code;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getClassName() {
    return className;
  }

  public Object[] getOptionalParams() {
    return optionalParams;
  }

  // FIXME: require refactoring
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Status status = (Status) o;
    return code == status.code &&
        Objects.equals(className, status.className) &&
        Objects.equals(fieldName, status.fieldName) &&
        Arrays.equals(optionalParams, status.optionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, className, fieldName) + Arrays.hashCode(optionalParams);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Status.class.getSimpleName() + "[", "]")
        .add("code=" + code)
        .add("className='" + className + "'")
        .add("fieldName='" + fieldName + "'")
        .add("optionalParams=" + Arrays.toString(optionalParams))
        .toString();
  }
}
