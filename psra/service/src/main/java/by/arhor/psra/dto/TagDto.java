package by.arhor.psra.dto;

import java.io.Serializable;

import by.arhor.psra.CoreVersion;
import lombok.Data;

@Data
public class TagDto implements Serializable {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	private String name;
	
}
