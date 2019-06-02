package by.arhor.psra.model

import by.arhor.psra.CoreVersion

import scala.beans.BeanProperty

object Tag {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

case class Tag(@BeanProperty var name: String) extends Serializable {

  override def toString: String = s"${getClass.getSimpleName} [name=$name]"

}
