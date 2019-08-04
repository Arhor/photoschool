package by.arhor.psra.model

import java.util.Objects

import by.arhor.psra.model.traits.{Datable, Deletable, Identifiable}

abstract class Entity extends Serializable
                         with Identifiable
                         with Datable
                         with Deletable {

  override def equals(obj: Any): Boolean = {
    Option(obj) match {
      case None => false
      case Some(value) =>
        if (this eq value.asInstanceOf[AnyRef]) return true
        if (getClass == obj.getClass) {
          val dto = obj.asInstanceOf[Entity]
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
