package by.arhor.psra.exception;

import by.arhor.psra.localization.Label;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class FieldValueException extends LocalizedException {

  private final String fieldName;
  private final Object fieldValue;

  public FieldValueException(Label label, String fieldName, Object fieldValue) {
    super(label);
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  @Override
  public Object[] getParams() {
    return new Object[] { fieldName, fieldValue };
  }

  public String getFieldName() {
    return fieldName;
  }

  public Object getFieldValue() {
    return fieldValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FieldValueException that = (FieldValueException) o;
    return Objects.equals(fieldName, that.fieldName)
        && Objects.equals(fieldValue, that.fieldValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fieldName, fieldValue);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FieldValueException.class.getSimpleName() + "[", "]")
        .add("fieldName='" + fieldName + "'")
        .add("fieldValue=" + fieldValue)
        .toString();
  }
}
