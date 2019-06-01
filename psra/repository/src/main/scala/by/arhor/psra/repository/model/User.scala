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

	@Field("username")
	@Indexed(unique = true)
	@BeanProperty
	var username: String = _

	@Field("password")
	@BeanProperty
	var password: String = _

	@Field("role")
	@BeanProperty
	var role: RolesEnum = _

	@DBRef
	@BeanProperty
	var galleries: List[Gallery] = _

}
