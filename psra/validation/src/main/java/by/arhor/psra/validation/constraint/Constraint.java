package by.arhor.psra.validation.constraint;

import java.lang.annotation.*;

/**
 * Indicates that marked annotation is constraint.
 *
 * @author Maksim Buryshynets
 * @version 1.0 19 January 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Constraint {
}
