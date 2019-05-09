package by.arhor.psra.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.repository.model.User;

@Component
public class UserMapperImpl extends AbstractMapper<User, UserDto> {

	@Autowired
	public UserMapperImpl(ModelMapper mapper) {
		super(mapper);
	}

	@Override
	public UserDto mapToDto(User entity) {
		return mapper.map(entity, UserDto.class);
	}

	@Override
	public User mapToEntity(UserDto dto) {
		return mapper.map(dto, User.class);
	}

}
