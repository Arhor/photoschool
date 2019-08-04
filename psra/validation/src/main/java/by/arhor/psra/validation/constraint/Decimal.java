package by.arhor.psra.validation.constraint;

import java.lang.annotation.*;

/**
 * Indicates that marked field must be decimal value with specified precision
 * and scale.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint
public @interface Decimal {

    /**
     * Maximal precision for annotated value value.
     *
     * @return maximal precision.
     */
    int precision();

    /**
     * Maximal scale for annotated value.
     *
     * @return maximal scale.
     */
    int scale();

}
