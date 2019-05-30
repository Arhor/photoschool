package by.arhor.psra.exception;

import by.arhor.psra.localization.Label;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public final class EntityNotFoundException extends LocalizedException {

	private static final long serialVersionUID = -773080330463111443L;
	
	private final String fieldName;
	private final Object fieldValue;
	    
    public EntityNotFoundException(Label label, String fieldName, Object fieldValue) {
    	super(label);
    	this.fieldName = fieldName;
    	this.fieldValue = fieldValue;
    }
    
    @Override
    public Object[] getParams() {
    	return new Object[] { fieldName, fieldValue };
    }

}
