package by.arhor.psra.exception

import by.arhor.psra.CoreVersion
import by.arhor.psra.localization.Label

import scala.beans.BeanProperty

object EntityNotFoundException {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

final class EntityNotFoundException(
	@BeanProperty val label: Label,
	@BeanProperty val fieldName: String,
	@BeanProperty val fieldValue: AnyRef
) extends LocalizedException {

	override def getParams: Array[AnyRef] = Array(fieldName, fieldValue)

}
