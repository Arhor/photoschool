package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.constraint.Size;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import static by.arhor.psra.validation.exception.Status.FAILED_LENGTH_CHECK;
import static java.util.Objects.nonNull;

/**
 * Represents Checker implementation for checking string representation of
 * passed object for corresponding size (from min to max).
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class SizeChecker implements Checker<Size> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(Object value, Size constraint) {
        if (nonNull(value)) {
            int length = String.valueOf(value).length();
            return (length >= constraint.min()) && (length <= constraint.max());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus(String className ,String fieldName, Size constraint) {
        return new Status(
                FAILED_LENGTH_CHECK,
                className,
                fieldName,
                new Object[] { constraint.min(), constraint.max() }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Size> getConstraintAnnotation() {
        return Size.class;
    }
}
