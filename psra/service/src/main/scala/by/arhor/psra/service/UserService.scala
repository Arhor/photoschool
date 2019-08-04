package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.UserDto
import by.arhor.psra.model.User

trait UserService extends Service {

  override type M = User
  override type D = UserDto
  override type K = String

  def findLearnersByCourseId(cid: K): util.List[D]

}
