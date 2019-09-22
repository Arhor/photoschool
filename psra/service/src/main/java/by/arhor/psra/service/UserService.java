package by.arhor.psra.service;

import by.arhor.psra.dto.UserDto;

import java.util.List;

public interface UserService extends Service<UserDto, String> {

  List<UserDto> findLearnersByCourseId(String cid);

}
