package by.arhor.psra.model.traits

import java.time.LocalDateTime

import scala.beans.BeanProperty

trait Datable {

  @BeanProperty
  var dateTimeCreated: LocalDateTime = _

  @BeanProperty
  var dateTimeUpdated: LocalDateTime = _

}
