package by.arhor.psra.model

import java.util
import java.util.Objects

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.enums.Roles._
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object User {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("users")
class User extends Entity {

  @Indexed(unique = true)
  @BeanProperty
  var username: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var role: Role = _

  @BeanProperty
  @DBRef(`lazy` = true)
  var galleries: util.List[Gallery] = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val user = obj.asInstanceOf[User]
      username == user.username &&
      password == user.password &&
      role == user.role
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getUsername,
    getPassword,
    getRole
  )

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled" +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"username=$username, " +
    s"password=$password, " +
    s"role=$role]"
}
