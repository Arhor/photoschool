package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.ErrorCode;
import by.arhor.psra.validation.constraint.NotNull;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import static by.arhor.psra.validation.exception.Status.FAILED_NULL_CHECK;

/**
 * Represents Checker implementation for checking objects for not NULL value.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class NullChecker implements Checker<NotNull> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(Object value, NotNull constraint) {
        return (value != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus(String className ,String fieldName, NotNull constraint) {
        return new Status(ErrorCode.FAILED_NULL_CHECK, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<NotNull> getConstraintAnnotation() {
        return NotNull.class;
    }

}
