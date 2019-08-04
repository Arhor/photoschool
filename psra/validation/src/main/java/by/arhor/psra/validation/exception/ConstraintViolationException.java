package by.arhor.psra.validation.exception;

import java.util.Collections;
import java.util.Set;

/**
 * Exception is thrown if during the validation of the object the constraints
 * imposed on its fields were violated. Contains a set of statuses of these
 * violations.
 *
 * @author Maksim Buryshynets
 * @version 1.0 20 January 2019
 */
public class ConstraintViolationException extends RuntimeException {

    private final transient Set<Status> statusSet;

    public ConstraintViolationException(Set<Status> statusSet) {
        this.statusSet = statusSet;
    }

    public Set<Status> getStatusSet() {
        return Collections.unmodifiableSet(statusSet);
    }

}
