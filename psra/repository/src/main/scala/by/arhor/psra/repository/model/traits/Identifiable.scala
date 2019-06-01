package by.arhor.psra.repository.model.traits

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

import scala.beans.BeanProperty

trait Identifiable {

	@Id
	@Field("id")
	@BeanProperty
	var id: String = _

}
