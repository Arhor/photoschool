package by.arhor.psra.dto;

import java.io.Serializable;
import java.util.List;

import by.arhor.psra.CoreVersion;
import by.arhor.psra.repository.model.User.Role;
import lombok.Data;

@Data
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	private String id;
	private String username;
	private String password;
	private Role role;
	private boolean enabled;
	private List<GalleryDto> galleries;
	
}
