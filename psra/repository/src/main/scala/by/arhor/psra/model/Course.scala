package by.arhor.psra.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.traits.Identifiable
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Course {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("courses")
class Course extends Entity
                with Identifiable {

  @Indexed(unique = true)
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var description: String = _
  
  @DBRef
  @BeanProperty
  var teacher: User = _

  @DBRef
  @BeanProperty
  var learners: List[User] = Nil

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"description=$description, " +
    s"teacher=$teacher, " +
    s"learners=$learners]"

}
