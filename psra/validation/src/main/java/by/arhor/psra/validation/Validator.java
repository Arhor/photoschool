package by.arhor.psra.validation;

import by.arhor.psra.validation.exception.ConstraintViolationException;
import by.arhor.psra.validation.exception.Status;

import java.util.Set;

/**
 * Each validator implementation should implement this interface.
 *
 * @author Maksim Buryshynets
 * @version 1.0 20 January 2019
 */
public interface Validator {

    /**
     * The method performs validation of passed object according to its
     * constraint annotations.
     *
     * @param  target object to validate
     * @throws ConstraintViolationException thrown in case of any constraint
     *         violation. Because of its unchecked nature
     *         should me properly handled by Exception handler class.
     */
    Set<Status> validate(Object target);

}
