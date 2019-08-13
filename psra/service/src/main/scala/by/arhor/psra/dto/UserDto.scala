package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class UserDto extends Dto {

  @BeanProperty var username: String = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val user = obj.asInstanceOf[UserDto]
      username == user.username
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getUsername
  )
}
