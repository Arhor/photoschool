package by.arhor.psra.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PhotoDto {
	private String id;
	private String path;	
	private Set<TagDto> tags;
}
