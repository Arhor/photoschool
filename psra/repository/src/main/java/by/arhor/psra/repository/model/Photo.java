package by.arhor.psra.repository.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "photos")
public class Photo {
	
	@Id
	@Field("id")
	private String id;
	
	@Field("path")
    private String path;
	
	@Field("tags")
	private Set<Tag> tags;
    
}
