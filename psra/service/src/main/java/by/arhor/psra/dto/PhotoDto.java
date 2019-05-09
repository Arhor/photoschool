package by.arhor.psra.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
	
	private String id;
	private String path;	
	private Set<TagDto> tags;
	
}
