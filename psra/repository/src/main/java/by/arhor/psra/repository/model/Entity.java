package by.arhor.psra.repository.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.Data;

@Data
public abstract class Entity implements Serializable, Cloneable {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
    @Field("dateTimeCreated")
    protected LocalDateTime dateTimeCreated;
    
    @Field("dateTimeUpdated")
    protected LocalDateTime dateTimeUpdated;
	
	@Field("enabled")
	protected boolean enabled;
	
}
