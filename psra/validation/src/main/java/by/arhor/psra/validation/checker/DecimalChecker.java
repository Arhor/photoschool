package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.constraint.Decimal;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static by.arhor.psra.validation.exception.Status.FAILED_DECIMAL_CHECK;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/**
 * Represents Checker implementation for checking numeric (decimal) objects.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class DecimalChecker implements Checker<Decimal> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(Object value, Decimal constraint) {
        if (nonNull(value)) {
            String stringValue = String.valueOf(value);
            if (isCreatable(stringValue)) {
                BigDecimal decimal = new BigDecimal(stringValue);

                int precision = decimal.precision();
                int scale = decimal.scale();

                return (precision <= constraint.precision()) && (scale <= constraint.scale());
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus(String className, String fieldName, Decimal constraint) {
        return new Status(
                FAILED_DECIMAL_CHECK,
                className,
                fieldName,
                new Object[] { constraint.precision(), constraint.scale() }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Decimal> getConstraintAnnotation() {
        return Decimal.class;
    }

}
