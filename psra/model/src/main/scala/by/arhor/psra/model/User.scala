package by.arhor.psra.model

import java.util

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.enums.Roles._
import com.fasterxml.jackson.annotation.JsonIgnore
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

  @JsonIgnore
  @BeanProperty
  @DBRef(`lazy` = true)
  var galleries: util.List[Gallery] = _

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled" +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"username=$username, " +
    s"password=$password, " +
    s"role=$role]"

}
