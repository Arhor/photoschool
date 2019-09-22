package by.arhor.psra.exception;

import by.arhor.psra.CoreVersion;
import by.arhor.psra.localization.Label;

import java.util.Objects;
import java.util.StringJoiner;

public final class EntityNotFoundException extends LocalizedException {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

	private final Label label;
	private final String fieldName;
	private final Object fieldValue;

	public EntityNotFoundException(Label label, String fieldName, Object fieldValue) {
		this.label = label;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	@Override
	public Object[] getParams() {
		return new Object[] { fieldName, fieldValue };
	}

	@Override
	public Label getLabel() {
		return label;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EntityNotFoundException that = (EntityNotFoundException) o;
		return Objects.equals(label, that.label) &&
				Objects.equals(fieldName, that.fieldName) &&
				Objects.equals(fieldValue, that.fieldValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, fieldName, fieldValue);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", EntityNotFoundException.class.getSimpleName() + "[", "]")
				.add("label=" + label)
				.add("fieldName='" + fieldName + "'")
				.add("fieldValue=" + fieldValue)
				.toString();
	}
}
