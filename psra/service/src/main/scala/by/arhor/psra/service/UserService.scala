package by.arhor.psra.service

import by.arhor.psra.dto.UserDto
import org.springframework.security.core.userdetails.UserDetailsService

trait UserService extends Service with UserDetailsService {

  override type DTO = UserDto
  override type ID  = String

}
