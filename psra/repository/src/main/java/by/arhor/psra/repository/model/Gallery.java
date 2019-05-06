package by.arhor.psra.repository.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "galleries")
public class Gallery {
	
	@Id
	@Field("id")
	private String id;
	
	@Field("name")
    private String name;
	
	@Field("type")
	private Type type;
    
    @DBRef
    private List<Photo> photos;
    
    public static enum Type {
    	PUBLIC,
    	PRIVATE
    }
    
}
