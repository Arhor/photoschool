package by.arhor.psra.model

import by.arhor.psra.CoreVersion
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Photo {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("photos")
class Photo extends Entity {
  
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var description: String = _

  @BeanProperty
  var path: String = _

  @BeanProperty
  var tags: Set[Tag] = Set.empty[Tag]

  @JsonIgnore
  @BeanProperty
  @DBRef(`lazy` = true)
  var comments: List[Comment] = Nil

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"description=$description, " +
    s"path=$path, " +
    s"tags=$tags]"
}
