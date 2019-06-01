package by.arhor.psra.web.error

import java.io.Serializable
import java.time.LocalDateTime

import by.arhor.psra.CoreVersion
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

import scala.beans.BeanProperty

object ApiError {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

case class ApiError(status: HttpStatus, errors: Error*) extends Serializable {

  // if it fails - try to remove 'T' from the pattern
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyyThh:mm:ss")
  @BeanProperty
  val timestamp: LocalDateTime = LocalDateTime.now()

}
