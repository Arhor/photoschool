package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object User {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("users")
class User extends Entity
              with Identifiable {

  import by.arhor.psra.repository.model.enums.Roles._

  @Indexed(unique = true)
  @BeanProperty
  var username: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var role: Roles = ROLE_GUEST

  @DBRef
  var galleries: List[Gallery] = Nil

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled" +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"username=$username, " +
    s"password=$password, " +
    s"role=$role, " +
    s"galleries=$galleries]"

}
