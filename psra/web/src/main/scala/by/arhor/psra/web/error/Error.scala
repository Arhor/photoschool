package by.arhor.psra.web.error

import java.io.Serializable

import by.arhor.psra.CoreVersion
import by.arhor.psra.web.error.Codes.Code

import scala.beans.BeanProperty

object Error {
	val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

case class Error (@BeanProperty var code: Code, @BeanProperty var message: String) extends Serializable
