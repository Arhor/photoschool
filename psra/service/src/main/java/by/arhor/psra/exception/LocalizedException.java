package by.arhor.psra.exception;

import by.arhor.psra.label.Label;

public abstract class LocalizedException extends RuntimeException {

	private static final long serialVersionUID = 4742357063997099227L;
	
	private final Label label;
	
	public LocalizedException(Label label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label.getValue();
	}

}
