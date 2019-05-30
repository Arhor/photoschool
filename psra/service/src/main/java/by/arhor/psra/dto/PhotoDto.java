package by.arhor.psra.dto;

import java.io.Serializable;
import java.util.Set;

import by.arhor.psra.CoreVersion;
import lombok.Data;

@Data
public class PhotoDto implements Serializable {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	private String id;
	private String path;
    private String name;
    private String description;
	private Set<TagDto> tags;
	
}
