package by.arhor.psra.exception;

import by.arhor.psra.localization.Label;

abstract public class LocalizedException extends RuntimeException {

	public abstract Label getLabel();

	public abstract Object[] getParams();

}
