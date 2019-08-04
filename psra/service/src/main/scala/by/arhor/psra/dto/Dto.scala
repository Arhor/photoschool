package by.arhor.psra.dto

import java.time.LocalDateTime
import java.util.Objects

import scala.beans.{BeanProperty, BooleanBeanProperty}

abstract class Dto extends Serializable with Comparable[Dto] {

  @BeanProperty var id: String = _
  @BeanProperty var dateTimeCreated: LocalDateTime = _
  @BeanProperty var dateTimeUpdated: LocalDateTime = _
  @BooleanBeanProperty var enabled: Boolean = true

  override def compareTo(that: Dto): Int = {
    val thisId = if (this.id != null) this.id else ""
    val thatId = if (that.id != null) that.id else ""
    thisId.compareTo(thatId)
  }

  override def equals(obj: Any): Boolean = {
    Option(obj) match {
      case None => false
      case Some(value) =>
        if (this eq value.asInstanceOf[AnyRef]) return true
        if (getClass == obj.getClass) {
          val dto = obj.asInstanceOf[Dto]
          Objects.equals(id, dto.id) &&
          Objects.equals(dateTimeCreated, dto.dateTimeCreated) &&
          Objects.equals(dateTimeUpdated, dto.dateTimeUpdated) &&
          enabled == dto.enabled
        } else {
          false
        }
    }
  }

  override def hashCode(): Int = Objects.hash(
    getId,
    getDateTimeCreated,
    getDateTimeUpdated
  ) + (if (isEnabled) 1 else 0)

}
