package by.arhor.psra.dto;

import java.io.Serializable

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.enums.Roles.Roles

import scala.beans.{BeanProperty, BooleanBeanProperty}

object UserDto {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

class UserDto extends Serializable {

  @BeanProperty var id: String = _
  @BeanProperty var username: String = _
  @BeanProperty var password: String = _
  @BeanProperty var role: Roles = _
  @BeanProperty var galleries: List[GalleryDto] = Nil
  @BooleanBeanProperty var enabled: Boolean = true

}
