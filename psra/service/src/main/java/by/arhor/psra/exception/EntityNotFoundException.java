package by.arhor.psra.exception;

import by.arhor.psra.CoreVersion;
import by.arhor.psra.localization.Label;

public final class EntityNotFoundException extends FieldValueException {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

	public EntityNotFoundException(Label label, String fieldName, Object fieldValue) {
		super(label, fieldName, fieldValue);
	}
}
