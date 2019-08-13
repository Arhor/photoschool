package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class UserDto extends Dto {

  @BeanProperty var username: String = _
  @BeanProperty var password: String = _
  @BeanProperty var role: Role = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val user = obj.asInstanceOf[UserDto]
      Objects.equals(username, user.username) &&
      Objects.equals(password, user.password) &&
      Objects.equals(role, user.role)
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getUsername,
    getPassword,
    getRole
  )

}
