package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion

object Tag {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

case class Tag(var name: String) extends Serializable
