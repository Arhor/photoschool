package by.arhor.psra.model

import java.time.LocalDateTime
import java.util.Objects

import by.arhor.psra.model.traits.{Auditable, Deletable, Identifiable}
import org.springframework.data.annotation.{CreatedDate, Id, LastModifiedDate}

import scala.beans.{BeanProperty, BooleanBeanProperty}

abstract class Entity
  extends Identifiable[String]
     with Auditable
     with Deletable {

  @Id
  @BeanProperty
  var id: String = _

  @CreatedDate
  @BeanProperty
  var dateTimeCreated: LocalDateTime = _

  @LastModifiedDate
  @BeanProperty
  var dateTimeUpdated: LocalDateTime = _

  @BooleanBeanProperty
  var enabled: Boolean = true

  override def equals(other: Any): Boolean = other match {
    case that: Entity =>
      that.isInstanceOf[Entity] &&
      that.id == this.id &&
      that.dateTimeCreated == this.dateTimeCreated &&
      that.dateTimeUpdated == this.dateTimeUpdated &&
      that.enabled == this.enabled
    case _ =>
      false
  }

  override def hashCode(): Int = (id, dateTimeCreated, dateTimeUpdated, enabled).##

}
