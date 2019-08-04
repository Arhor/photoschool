package by.arhor.psra.validation.constraint;

import java.lang.annotation.*;

/**
 * Indicates that annotated value should be checked for length.
 *
 * @author Maksim Buryshynets
 * @version 1.0 22 January 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint
public @interface Size {

    /**
     * Minimal length for annotated value.
     *
     * @return minimal length.
     */
    int min();

    /**
     * Maximal length for annotated value.
     *
     * @return maximal length.
     */
    int max();

}
