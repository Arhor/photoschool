package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{Document, Field}

import scala.beans.BeanProperty

object Photo {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("photos")
class Photo extends Entity
	             with Identifiable {

	@Field("path")
	@BeanProperty
	var path: String = _
	
	@Field("name")
	@BeanProperty
	var name: String = _
	
	@Field("description")
	@BeanProperty
	var description: String = _
	
	@Field("tags")
	@BeanProperty
	var tags: Set[Tag] = _
	
	@Field("comments")
	@BeanProperty
	var comments: List[Comment] = _
    
}
