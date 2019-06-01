package by.arhor.psra.repository.model.traits

import org.springframework.data.mongodb.core.mapping.Field

import scala.beans.BooleanBeanProperty

trait Deletable {

  @Field("enabled")
  @BooleanBeanProperty
  var enabled: Boolean = _

}
