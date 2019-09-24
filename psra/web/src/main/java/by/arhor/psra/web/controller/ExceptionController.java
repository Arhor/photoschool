package by.arhor.psra.web.controller;

import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.exception.LocalizedException;
import by.arhor.psra.web.error.ApiError;
import by.arhor.psra.web.error.Code;
import by.arhor.psra.web.error.Error;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

  private final MessageSource messageSource;

  @Autowired
  public ExceptionController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler({EntityNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
    log.error("Resource not found", ex);
    return new ApiError(
        HttpStatus.NOT_FOUND,
        new Error[] { extractCause(ex, request) });
  }

  @ExceptionHandler({JsonProcessingException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError jsonProcessingException(
      JsonProcessingException ex,
      WebRequest request) throws IOException {

    log.error("JSON processing exception", ex);

    final var parser = (JsonParser) ex.getProcessor();

    final var value = (parser.getCurrentName() != null)
        ? parser.getCurrentName()
        : parser.getText();

    final var location = ex.getLocation();

    final var params = new Object[]{
        value,
        location.getLineNr(),
        location.getColumnNr()
    };

    final var errors = new Error[]{
        new Error(
            Code.INVALID_JSON,
            messageSource.getMessage(
            "error.json.parse",
                params,
                request.getLocale()))};

    return new ApiError(HttpStatus.BAD_REQUEST, errors);
  }

  private Error extractCause(LocalizedException ex, WebRequest request) {
    return new Error(
        Code.NOT_FOUND,
        messageSource.getMessage(
            ex.getLabel().getValue(),
            ex.getParams(),
            request.getLocale()));
  }
}
