package by.arhor.psra.exception

import by.arhor.psra.CoreVersion
import by.arhor.psra.localization.Label

object LocalizedException {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

abstract class LocalizedException extends RuntimeException {
	def getLabel: Label
	def getParams: Array[AnyRef]
}
