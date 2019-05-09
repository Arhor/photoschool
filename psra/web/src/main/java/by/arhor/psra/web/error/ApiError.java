package by.arhor.psra.web.error;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class ApiError implements Serializable {

	private static final long serialVersionUID = 5507028058917355385L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

	private final HttpStatus status;
    private final List<Error> cause;

    { this.timestamp = LocalDateTime.now(); }

    public ApiError(HttpStatus status, List<Error> errors) {
        this.status = status;
        this.cause = errors;
    }

    public ApiError(HttpStatus status, Error... errors) {
        this(status, Arrays.asList(errors));
    }

    

}
