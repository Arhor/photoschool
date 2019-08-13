package by.arhor.psra.exception

import by.arhor.psra.CoreVersion

import scala.beans.BeanProperty

object LocalizedException {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

abstract class LocalizedException extends RuntimeException {
	def getLabel: String
	def getParams: Array[AnyRef]
}
