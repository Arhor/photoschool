package by.arhor.psra.exception;

import by.arhor.psra.label.Label;

public abstract class LocalizedException extends RuntimeException {

	private static final long serialVersionUID = 4742357063997099227L;
	
	private final Label label;
	
	protected final Object[] params;
	
	public LocalizedException(Label label, Object... params) {
		this.label = label;
		this.params = params;
	}
	
	public String getLabel() {
		return label.getValue();
	}
	
	public Object[] getParams() {
		return params.clone();
	}

}
