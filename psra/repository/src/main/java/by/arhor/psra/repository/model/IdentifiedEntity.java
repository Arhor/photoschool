package by.arhor.psra.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class IdentifiedEntity extends Entity {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

	@Id
	@Field("id")
    protected String id;

}
