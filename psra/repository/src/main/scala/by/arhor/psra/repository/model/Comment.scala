package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field}

import scala.beans.BeanProperty

object Comment {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("comments")
class Comment extends Entity
                 with Identifiable {

  @BeanProperty
  var content: String = _
  
  @DBRef
  @BeanProperty
  var user: User = _

}
