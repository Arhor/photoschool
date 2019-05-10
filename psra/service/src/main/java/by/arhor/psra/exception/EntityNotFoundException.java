package by.arhor.psra.exception;

import by.arhor.psra.label.Label;

public final class EntityNotFoundException extends LocalizedException {

	private static final long serialVersionUID = -773080330463111443L;
	
	private static class Field {
    	private static int fieldId;
    	private static final int SEARCH_CRITERIA_NAME  = fieldId++;
    	private static final int SEARCH_CRITERIA_VALUE = fieldId++;
    }
	    
    public EntityNotFoundException(Label label, String fieldName, Object fieldValue) {
    	super(label, fieldName, fieldValue);
    }
    
    public String getFieldName() {
    	return String.valueOf(params[Field.SEARCH_CRITERIA_NAME]);
    }
    
    public Object getFieldValue() {
    	return params[Field.SEARCH_CRITERIA_VALUE];
    }

}
