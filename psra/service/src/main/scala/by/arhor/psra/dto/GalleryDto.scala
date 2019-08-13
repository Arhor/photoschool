package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class GalleryDto extends Dto {

  @BeanProperty var name: String = _
  @BeanProperty var visibility: Visibility = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val gallery = obj.asInstanceOf[GalleryDto]
      Objects.equals(name, gallery.name) &&
      Objects.equals(visibility, gallery.visibility)
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getName,
    getVisibility
  )

}
