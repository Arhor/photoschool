package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.enums.Roles.RolesEnum
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field}

import scala.beans.BeanProperty

object User {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("users")
class User extends Entity
              with Identifiable {

  @Indexed(unique = true)
  @BeanProperty
  var username: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var role: RolesEnum = _

  @DBRef
  var galleries: List[Gallery] = _

}
