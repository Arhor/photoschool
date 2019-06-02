package by.arhor.psra.web.error

import java.io.Serializable
import java.time.LocalDateTime
import java.util

import by.arhor.psra.CoreVersion
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

import scala.beans.BeanProperty

object ApiError {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

case class ApiError(@BeanProperty status: HttpStatus,
                    @BeanProperty errors: util.List[Error]) extends Serializable {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @BeanProperty
  val timestamp: LocalDateTime = LocalDateTime.now()

}
