package by.arhor.psra.repository.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.repository.model.traits.Identifiable
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field}

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
  var learners: List[User] = _
  
}
