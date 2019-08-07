package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.UserDto
import by.arhor.psra.model.User

trait UserService extends Service[User, UserDto, String] {

  def findLearnersByCourseId(cid: String): util.List[UserDto]

}
