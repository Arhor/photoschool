package by.arhor.psra.web.controller

import by.arhor.psra.exception.{EntityNotFoundException, LocalizedException}
import by.arhor.psra.web.error.{ApiError, Codes, Error}
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

  private def extractCause(ex: LocalizedException, request: WebRequest): Error =
    Error(
      Codes.NOT_FOUND,
      messageSource.getMessage(
        ex.getLabel,
        ex.getParams,
        request.getLocale)
    )

}
