package by.arhor.psra.exception;

import by.arhor.psra.localization.Label;

import java.util.Objects;

abstract public class LocalizedException extends RuntimeException {

	private final Label label;

	public LocalizedException(Label label) {
		Objects.requireNonNull(label);
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	public Object[] getParams() {
		return new Object[0];
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocalizedException that = (LocalizedException) o;
		return Objects.equals(label, that.label);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label);
	}
}
