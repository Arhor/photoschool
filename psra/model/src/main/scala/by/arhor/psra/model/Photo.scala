package by.arhor.psra.model

import java.util
import java.util.Objects

import by.arhor.psra.CoreVersion
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Photo {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("photos")
class Photo extends Entity {
  
  @BeanProperty var name: String = _
  @BeanProperty var description: String = _
  @BeanProperty var path: String = _
  @BeanProperty var tags: util.Set[String] = _

  @BeanProperty
  @DBRef(`lazy` = true)
  var comments: util.List[Comment] = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val photo = obj.asInstanceOf[Photo]
      name == photo.name &&
      description == photo.description &&
      path == photo.path &&
      tags == photo.tags
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getName,
    getDescription,
    getPath
  ) + (if (getTags != null) getTags.stream.mapToInt(_.hashCode).sum else 0)

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
