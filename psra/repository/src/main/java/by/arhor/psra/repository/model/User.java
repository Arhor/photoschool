package by.arhor.psra.repository.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"galleries"})
@ToString(exclude = {"galleries"})
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User extends IdentifiedEntity {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	@Field("username")
	@Indexed(unique = true)
	private String username;
	
	@Field("password")
	private String password;
	
	@Field("role")
	private Role role;
	
	@DBRef(lazy = true)
	private List<Gallery> galleries;
	
	@Override
	public User clone() {
		User clone = new User();
		
		clone.id = this.id;
		clone.dateTimeCreated = this.dateTimeCreated;
		clone.dateTimeUpdated = this.dateTimeUpdated;
		clone.enabled = this.enabled;
		
		clone.username = this.username;
		clone.password = this.password;
		clone.role = this.role;
		clone.galleries = this.galleries != null
				? this.galleries.stream().map(Gallery::clone).collect(Collectors.toList())
				: null;
				
		return clone;
	}
	
	public enum Role {
		ROLE_GUEST,
		ROLE_USER,
		ROLE_MANAGER,
		ROLE_ADMIN
	}
	
}
