package by.arhor.psra.exception;

import by.arhor.psra.label.Label;
import lombok.Getter;

public final class EntityNotFoundException extends LocalizedException {

	private static final long serialVersionUID = -773080330463111443L;

	@Getter
    private final String searchCriteriaName;
	
	@Getter
    private final Object searchCriteriaValue;
    
    public EntityNotFoundException(Label label,
    							   String searchCriteriaName,
    							   Object searchCriteriaValue) {
    	super(label);
    	this.searchCriteriaName = searchCriteriaName;
    	this.searchCriteriaValue = searchCriteriaValue;
    }

}
