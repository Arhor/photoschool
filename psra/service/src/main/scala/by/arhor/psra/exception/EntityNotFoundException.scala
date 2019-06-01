package by.arhor.psra.exception

import by.arhor.psra.CoreVersion
import by.arhor.psra.localization.Label

object EntityNotFoundException {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

final class EntityNotFoundException(val label: Label,
																		val fieldName: String,
																		val fieldValue: AnyRef) extends LocalizedException {

	override def getLabel: String = label.getValue.toString

	override def getParams: Array[AnyRef] = Array(fieldName, fieldValue)

}
