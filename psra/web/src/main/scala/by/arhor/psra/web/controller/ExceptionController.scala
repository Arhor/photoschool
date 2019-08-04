package by.arhor.psra.web.controller

import java.io.IOException

import by.arhor.psra.exception.{EntityNotFoundException, LocalizedException}
import by.arhor.psra.web.error.{ApiError, Codes, Error}
import com.fasterxml.jackson.core.{JsonParser, JsonProcessingException}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import collection.JavaConverters._

object ExceptionController {
  private val log = LoggerFactory.getLogger(classOf[ExceptionController])
}

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class ExceptionController(@Autowired private val messageSource: MessageSource) extends ResponseEntityExceptionHandler {

  import ExceptionController._

  @ExceptionHandler(Array(classOf[EntityNotFoundException]))
  @ResponseStatus(HttpStatus.NOT_FOUND)
  def handleEntityNotFound(ex: EntityNotFoundException, request: WebRequest): ApiError = {
    log.error("Resource not found", ex)
    ApiError(
      HttpStatus.NOT_FOUND,
      List(extractCause(ex, request)).asJava
    )
  }

  @ExceptionHandler(Array(classOf[JsonProcessingException]))
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @throws(classOf[IOException])
  def jsonProcessingException(ex: JsonProcessingException, request: WebRequest): ApiError = {
    log.error("JSON processing exception", ex)

    lazy val parser = ex.getProcessor.asInstanceOf[JsonParser]

    lazy val value = Option(parser.getCurrentName) match {
      case Some(name) => name
      case None => parser.getText
    }

    lazy val loc = ex.getLocation

    lazy val params = Array(
      value,
      loc.getLineNr,
      loc.getColumnNr
    ).asInstanceOf[Array[AnyRef]]

    lazy val error = Error(
      Codes.INVALID_JSON,
      messageSource.getMessage(
        "error.json.parse",
        params,
        request.getLocale))

    ApiError(
      HttpStatus.BAD_REQUEST,
      List(error).asJava)
  }

  private def extractCause(ex: LocalizedException, request: WebRequest): Error =
    Error(
      Codes.NOT_FOUND,
      messageSource.getMessage(
        ex.getLabel,
        ex.getParams,
        request.getLocale))
}
