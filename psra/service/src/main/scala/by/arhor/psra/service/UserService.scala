package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService

trait UserService extends Service
                     with UserDetailsService {

  override type DTO = UserDTO
  override type ID  = String

  def findLearnersByCourseId(cid: ID): util.List[UserDTO]

}
