package by.arhor.psra.validation.checker;
import by.arhor.psra.validation.exception.Status;
import java.lang.annotation.Annotation;

/**
 * Represents concrete constraint checker class.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2019
 * @param <T> constraint annotation
 * @see by.arhor.psra.validation.constraint.Constraint
 */
public interface Checker<T extends Annotation> {

    /**
     * Checks passed value for concrete constraint violation.
     *
     * @param value value to check
     * @param constraint may contain some detailed constraint bounds
     * @return false - in case of constraint violation, otherwise true
     */
    boolean check(Object value, T constraint);

    /**
     * Constructs error {@link Status} object, which contain violation status-code, class and field names where error
     * occurred.
     *
     * @param className class name of object which violated constraint
     * @param fieldName field name of object that violated constraint
     * @param constraint may contain some detailed constraint bounds
     * @return new Status object
     */
    Status getStatus(String className ,String fieldName, T constraint);

    /**
     * Returns actual constraint-annotation class, which will be used for building Checkers map as concrete Checker
     * implementation key.
     *
     * @return constraint-annotation class
     */
    Class<T> getConstraintAnnotation();

}
