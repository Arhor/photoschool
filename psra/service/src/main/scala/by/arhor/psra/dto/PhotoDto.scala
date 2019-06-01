package by.arhor.psra.dto

import by.arhor.psra.CoreVersion

import scala.beans.BeanProperty

object PhotoDto {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

class PhotoDto extends Serializable {

	@BeanProperty var id: String = _
	@BeanProperty var path: String = _
	@BeanProperty var name: String = _
	@BeanProperty var description: String = _
	@BeanProperty var tags: Set[TagDto] = _
	
}
