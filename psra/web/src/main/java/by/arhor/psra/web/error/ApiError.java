package by.arhor.psra.web.error;

import by.arhor.psra.CoreVersion;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public final class ApiError implements Serializable {

  private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private final LocalDateTime timestamp = LocalDateTime.now();

  private final HttpStatus status;
  private final Error[] errors;

  public ApiError(HttpStatus status, Error[] errors) {
    Objects.requireNonNull(status);
    Objects.requireNonNull(errors);
    this.status = status;
    this.errors = errors;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public Error[] getErrors() {
    return errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApiError apiError = (ApiError) o;
    return status == apiError.status
        && timestamp.equals(apiError.timestamp)
        && Arrays.equals(errors, apiError.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, status, errors);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiError.class.getSimpleName() + "[", "]")
        .add("timestamp=" + timestamp)
        .add("status=" + status)
        .add("errors=" + Arrays.toString(errors))
        .toString();
  }
}
