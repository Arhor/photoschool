package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class GalleryDto extends Dto {

  @BeanProperty var name: String = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val gallery = obj.asInstanceOf[GalleryDto]
      name == gallery.name
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getName
  )

}
