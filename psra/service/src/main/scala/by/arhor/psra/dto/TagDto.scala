package by.arhor.psra.dto

import java.io.Serializable

import by.arhor.psra.CoreVersion

import scala.beans.BeanProperty

object TagDto {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

class TagDto extends Serializable {

	@BeanProperty var name: String = _
	
}
