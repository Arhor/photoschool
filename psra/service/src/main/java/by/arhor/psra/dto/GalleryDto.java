package by.arhor.psra.dto;

import java.io.Serializable;
import java.util.List;

import by.arhor.psra.CoreVersion;
import by.arhor.psra.repository.model.Gallery.Type;
import lombok.Data;

@Data
public class GalleryDto implements Serializable {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

	private String id;
	private String name;
	private Type type;
    private List<PhotoDto> photos;
    
}
