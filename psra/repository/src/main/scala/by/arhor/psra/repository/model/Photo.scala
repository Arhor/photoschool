package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{Document, Field}

import scala.beans.BeanProperty

object Photo {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("photos")
class Photo extends Entity
               with Identifiable {

  @BeanProperty
  var path: String = _
  
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var description: String = _
  
  @BeanProperty
  var tags: Set[Tag] = _
  
  @BeanProperty
  var comments: List[Comment] = _
    
}
