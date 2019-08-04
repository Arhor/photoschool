package by.arhor.psra.validation.checker;

import by.arhor.psra.validation.constraint.Pattern;
import by.arhor.psra.validation.exception.Status;
import org.springframework.stereotype.Component;

import static by.arhor.psra.validation.exception.Status.FAILED_PATTERN_CHECK;
import static java.util.Objects.nonNull;

/**
 * Represents Checker implementation for checking string representation of
 * passed object for matching corresponding RegExp pattern.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Component
public final class PatternChecker implements Checker<Pattern> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(Object value, Pattern constraint) {
        if (nonNull(value)) {
            String string = String.valueOf(value);
            String pattern = constraint.value();
            return string.matches(pattern);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus(String className ,String fieldName, Pattern constraint) {
        return new Status(
                FAILED_PATTERN_CHECK,
                className,
                fieldName,
                new Object[] {constraint.message()});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Pattern> getConstraintAnnotation() {
        return Pattern.class;
    }

}
