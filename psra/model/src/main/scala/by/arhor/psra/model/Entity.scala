package by.arhor.psra.model

import java.time.LocalDateTime
import java.util.Objects

import by.arhor.psra.model.traits.{Auditable, Deletable, Identifiable}
import org.springframework.data.annotation.{CreatedDate, Id, LastModifiedDate}

import scala.beans.{BeanProperty, BooleanBeanProperty}

abstract class Entity
  extends Serializable
     with Identifiable[String]
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

  override def equals(obj: Any): Boolean = obj match {
    case null => false
    case value =>
      if (this eq value.asInstanceOf[AnyRef]) {
        return true
      }
      if (getClass == obj.getClass) {
        val entity = obj.asInstanceOf[Entity]
        id == entity.id &&
        dateTimeCreated == entity.dateTimeCreated &&
        dateTimeUpdated == entity.dateTimeUpdated &&
        enabled == entity.enabled
      } else {
        false
      }
  }

  override def hashCode(): Int = Objects.hash(
    getId,
    getDateTimeCreated,
    getDateTimeUpdated
  ) + (if (isEnabled) 1 else 0)

}
