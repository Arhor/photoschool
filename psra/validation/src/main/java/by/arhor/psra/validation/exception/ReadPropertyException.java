package by.arhor.psra.validation.exception;

/**
 * An exception is thrown if the corresponding access method is not found
 * for a specific property of the object.
 *
 * @author Maksim Buryshynets
 * @version 1.0 20 January 2019
 */
public class ReadPropertyException extends RuntimeException {

    public ReadPropertyException(ReflectiveOperationException e) {
        super(e);
    }

}
