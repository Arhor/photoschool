package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.enums.Visibilities.VisibilitiesEnum
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field}

object Gallery {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("galleries")
class Gallery extends Entity
	               with Identifiable {

	@Field("name")
	var  name: String = _

	@Field("type")
	var visibility: VisibilitiesEnum = _
    
	@DBRef
	var  photos: List[Photo] = _

}
