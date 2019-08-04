package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.constraint.Positive;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static by.arhor.psra.validation.exception.Status.FAILED_POSITIVE_CHECK;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/**
 * Represents Checker implementation for checking numeric representation of
 * passed object for positive value.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class PositiveChecker implements Checker<Positive> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(Object value, Positive constraint) {
        if (nonNull(value)) {
            String stringValue = value.toString();
            if (isCreatable(stringValue)) {
                BigDecimal number = new BigDecimal(stringValue);
                System.out.println("VALUE: " + number);
                return number.compareTo(ZERO) > 0;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus(String className ,String fieldName, Positive constraint) {
        return new Status(FAILED_POSITIVE_CHECK, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Positive> getConstraintAnnotation() {
        return Positive.class;
    }

}
