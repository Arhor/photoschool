package by.arhor.psra.dto

import java.time.LocalDateTime

import scala.beans.BeanProperty

class PhotoDTO {

  @BeanProperty var id: String = _
  @BeanProperty var dateTimeCreated: LocalDateTime = _
  @BeanProperty var dateTimeUpdated: LocalDateTime = _
  @BeanProperty var enabled: Boolean = true

}
