package by.arhor.psra.repository.model.traits

import java.time.LocalDateTime

import org.springframework.data.mongodb.core.mapping.Field

import scala.beans.BeanProperty

trait Datable {

  @Field("dateTimeCreated")
  @BeanProperty
  var dateTimeCreated: LocalDateTime = _

  @Field("dateTimeUpdated")
  @BeanProperty
  var dateTimeUpdated: LocalDateTime = _

}
