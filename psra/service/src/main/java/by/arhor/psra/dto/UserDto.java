package by.arhor.psra.dto;

import java.util.List;

import by.arhor.psra.repository.model.User.Role;
import lombok.Data;

@Data
public class UserDto {
	private String id;
	private String email;
	private String password;
	private Role role;
	private List<GalleryDto> galleries;
}
