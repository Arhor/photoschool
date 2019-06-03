package by.arhor.psra.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.enums.Visibilities.Visibilities
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Gallery {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("galleries")
class Gallery extends Entity {

  @BeanProperty
  var name: String = _

  @BeanProperty
  var visibility: Visibilities = _

  @DBRef
  @BeanProperty
  var  photos: List[Photo] = Nil

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"visibility=$visibility, " +
    s"photos=$photos]"

}
