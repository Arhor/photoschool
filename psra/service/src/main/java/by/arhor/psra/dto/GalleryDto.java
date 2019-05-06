package by.arhor.psra.dto;

import java.util.List;

import by.arhor.psra.repository.model.Gallery.Type;
import lombok.Data;

@Data
public class GalleryDto {
	private String id;
	private String name;
	private Type type;
    private List<PhotoDto> photos;
}
