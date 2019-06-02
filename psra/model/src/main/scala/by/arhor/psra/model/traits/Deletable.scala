package by.arhor.psra.model.traits

import scala.beans.BooleanBeanProperty

trait Deletable {

  @BooleanBeanProperty
  var enabled: Boolean = true

}
