package by.arhor.psra.validation;

import java.lang.annotation.*;

/**
 * Indicates, that annotated method parameter must be validated.
 *
 * @author Maksim Buryshynets
 * @version 1.0 17 January 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Validate {}
