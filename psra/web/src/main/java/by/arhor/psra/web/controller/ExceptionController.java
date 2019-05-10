package by.arhor.psra.web.controller;

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

import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.exception.LocalizedException;
import by.arhor.psra.web.error.ApiError;
import by.arhor.psra.web.error.Error;
import by.arhor.psra.web.error.Error.Code;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	private MessageSource messageSource;
	
	@Autowired
	public ExceptionController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
		log.error("Resource not found", ex);
		return new ApiError(HttpStatus.NOT_FOUND, extractCause(ex, request));
	}

    private Error extractCause(LocalizedException ex, WebRequest request) {
        return new Error(
                Code.NOT_FOUND,
                messageSource.getMessage(
		                ex.getLabel(),
		                ex.getParams(),
		                request.getLocale()
                ));
    }

}
