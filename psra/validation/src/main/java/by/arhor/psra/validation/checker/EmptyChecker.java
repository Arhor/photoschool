package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.ErrorCode;
import by.arhor.psra.validation.constraint.NotEmpty;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import static by.arhor.psra.validation.exception.Status.FAILED_EMPTY_CHECK;
import static java.util.Objects.nonNull;

/**
 * Represents Checker implementation for checking string representation of
 * passed object for emptiness.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class EmptyChecker implements Checker<NotEmpty> {

  @Override
  public boolean check(Object value, NotEmpty constraint) {
    return value != null
        && (value.toString().trim().length() > 0);
  }

  @Override
  public Status getStatus(String className , String fieldName, NotEmpty constraint) {
    return new Status(ErrorCode.FAILED_EMPTY_CHECK, className, fieldName);
  }

  @Override
  public Class<NotEmpty> getConstraintAnnotation() {
    return NotEmpty.class;
  }

}
