package by.arhor.psra.repository.model.traits

import scala.beans.BooleanBeanProperty

trait Deletable {

  @BooleanBeanProperty
  var enabled: Boolean = true

}
