package by.arhor.psra.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.enums.Visibilities.Visibilities
import by.arhor.psra.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Gallery {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("galleries")
class Gallery extends Entity
                 with Identifiable {

  @BeanProperty
  var  name: String = _

  @BeanProperty
  var visibility: Visibilities = _
    
  @DBRef
  @BeanProperty
  var  photos: List[Photo] = Nil

}
