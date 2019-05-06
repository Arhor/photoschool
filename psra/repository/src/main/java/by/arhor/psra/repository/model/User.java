package by.arhor.psra.repository.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	
	@Id
	@Field("id")
    private String id;
	
	@Field("email")
	@Indexed(unique = true)
	private String email;
	
	@Field("password")
	private String password;
	
	@Field("role")
	private Role role;
	
	@DBRef(lazy = true)
	private List<Gallery> galleries;
	
	public static enum Role {
		ROLE_GUEST,
		ROLE_USER,
		ROLE_ADMIN
	}
	
}
