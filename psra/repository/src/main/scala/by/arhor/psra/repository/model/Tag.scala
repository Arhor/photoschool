package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion

import scala.beans.BeanProperty

object Tag {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

class Tag extends Serializable {

  @BeanProperty
  var name: String

}
