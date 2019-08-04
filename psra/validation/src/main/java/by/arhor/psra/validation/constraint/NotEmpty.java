package by.arhor.psra.validation.constraint;

import java.lang.annotation.*;

/**
 * Indicates that marked field must not be empty.
 *
 * @author Maksim Buryshynets
 * @version 1.0 19 January 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint
public @interface NotEmpty {
}
