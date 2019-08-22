package by.arhor.psra.model.traits

import java.time.LocalDateTime

trait Auditable {

  def getDateTimeCreated: LocalDateTime

  def getDateTimeUpdated: LocalDateTime

}
