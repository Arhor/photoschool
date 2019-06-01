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

case class ApiError(@BeanProperty val status: HttpStatus, @BeanProperty val errors: Error*) extends Serializable {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @BeanProperty
  val timestamp: LocalDateTime = LocalDateTime.now()

}
