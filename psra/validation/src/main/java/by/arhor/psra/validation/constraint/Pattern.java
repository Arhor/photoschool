package by.arhor.psra.validation.constraint;

import java.lang.annotation.*;

/**
 * Indicates that marked field must be a string, that matches specific
 * RegExp pattern.
 *
 * @author Maksim Buryshynets
 * @version 1.0 1 March 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint
public @interface Pattern {

    /**
     * Specified RegExp pattern.
     *
     * @return RegExp pattern for matching.
     */
    String value();

    /**
     * Allowed characters or message in human-readable form.
     *
     * @return allowed characters (in case of constraint violation).
     */
    String message() default "";
}
