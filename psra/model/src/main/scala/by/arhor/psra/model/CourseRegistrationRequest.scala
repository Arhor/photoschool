package by.arhor.psra.model

import scala.beans.BeanProperty

abstract class CourseRegistrationRequest extends RegistrationRequest {
  @BeanProperty
  var course: Course = _
}
