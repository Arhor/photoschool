package by.arhor.psra.repository.model;

import java.io.Serializable;

import by.arhor.psra.CoreVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable, Cloneable {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
    private String name;
    
    @Override
    public Tag clone() {
    	return new Tag(this.name);
    }
    
}
