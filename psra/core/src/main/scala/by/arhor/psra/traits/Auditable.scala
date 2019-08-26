package by.arhor.psra.traits

import java.time.LocalDateTime

trait Auditable {

  def getDateTimeCreated: LocalDateTime

  def getDateTimeUpdated: LocalDateTime

}
