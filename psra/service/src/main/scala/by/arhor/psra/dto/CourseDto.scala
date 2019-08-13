package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class CourseDto extends Dto {

  @BeanProperty var name: String = _
  @BeanProperty var description: String = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val course = obj.asInstanceOf[CourseDto]
      name == course.name &&
      description == course.description
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getName,
    getDescription
  )
}
