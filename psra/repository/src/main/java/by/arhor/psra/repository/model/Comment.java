package by.arhor.psra.repository.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@ToString(exclude = {"user"})
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Entity {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	@Field("content")
	private String content;
	
	@DBRef
	private User user;
	
	@Override
	public Comment clone() {
		var clone = new Comment();
		
		clone.dateTimeCreated = this.dateTimeCreated;
		clone.dateTimeUpdated = this.dateTimeUpdated;
		clone.enabled = this.enabled;
		
		clone.content = this.content;
		clone.user = this.user != null
				? this.user.clone()
				: null;
		
		return clone;
	}
	
}
