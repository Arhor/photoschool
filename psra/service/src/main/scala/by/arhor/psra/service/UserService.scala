package by.arhor.psra.service

import by.arhor.psra.model.User
import org.springframework.security.core.userdetails.UserDetailsService

trait UserService extends Service
                     with UserDetailsService {

  override type Entity = User
  override type ID = String

  def findLearnersByCourseId(cid: ID): List[User]

}
