package by.arhor.psra.repository.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents photo gallery entity owned by concrete user.
 * 
 * @author Maksim Buryshynets
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"photos"})
@ToString(exclude = {"photos"})
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "galleries")
public class Gallery extends IdentifiedEntity {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	@Field("name")
    private String name;
	
	@Field("type")
	private Type type;
    
    @DBRef
    private List<Photo> photos;
    
    @Override
    public Gallery clone() {
    	var clone = new Gallery();
    	
    	clone.id = this.id;
    	clone.dateTimeCreated = this.dateTimeCreated;
    	clone.dateTimeUpdated = this.dateTimeUpdated;
    	clone.enabled = this.enabled;
    	
    	clone.name = this.name;
    	clone.type = this.type;
    	clone.photos = this.photos != null
    			? this.photos.stream().map(Photo::clone).collect(toList())
    			: null;
    			
    	return clone;
    }
    
    public static enum Type {
    	PUBLIC,
    	PROTECTED,
    	PRIVATE
    }
    
}
