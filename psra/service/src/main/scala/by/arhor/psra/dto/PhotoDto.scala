package by.arhor.psra.dto

import java.util
import java.util.Objects

import scala.beans.BeanProperty

class PhotoDto extends Dto {

  @BeanProperty var name: String = _
  @BeanProperty var description: String = _
  @BeanProperty var path: String = _
  @BeanProperty var tags: util.Set[String] = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val photo = obj.asInstanceOf[PhotoDto]
      Objects.equals(name, photo.name) &&
      Objects.equals(description, photo.description) &&
      Objects.equals(path, photo.path) &&
      Objects.equals(tags, photo.tags)
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() +
    Objects.hash(getName, getDescription, getPath) +
    (if (getTags != null) getTags.stream.mapToInt(_.hashCode).sum() else 0)

}
