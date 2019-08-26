package by.arhor.psra.dto

import java.time.LocalDateTime
import java.util.Objects

import by.arhor.psra.traits.{Auditable, Deletable, Identifiable}

import scala.beans.{BeanProperty, BooleanBeanProperty}

abstract class Dto
  extends Identifiable[String]
    with Auditable
    with Deletable {

  @BeanProperty var id: String = _
  @BeanProperty var dateTimeCreated: LocalDateTime = _
  @BeanProperty var dateTimeUpdated: LocalDateTime = _
  @BooleanBeanProperty var enabled: Boolean = true

  override def equals(other: Any): Boolean = other match {
    case that: Dto =>
      that.isInstanceOf[Dto] &&
      that.id == this.id &&
      that.dateTimeCreated == this.dateTimeCreated &&
      that.dateTimeUpdated == this.dateTimeUpdated &&
      that.enabled == this.enabled
    case _ =>
      false
  }

  override def hashCode(): Int = (id, dateTimeCreated, dateTimeUpdated, enabled).##

}
