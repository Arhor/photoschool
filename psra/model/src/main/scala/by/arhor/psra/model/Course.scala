package by.arhor.psra.model

import java.util

import by.arhor.psra.CoreVersion
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Course {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("courses")
class Course extends Entity {

  @Indexed(unique = true)
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var description: String = _

  @DBRef(`lazy` = true)
  @BeanProperty
  var teacher: User = _

  @DBRef(`lazy` = true)
  @BeanProperty
  var learners: util.List[User] = _

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"description=$description]"
}
