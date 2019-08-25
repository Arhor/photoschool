package by.arhor.psra.validation;

import by.arhor.psra.validation.checker.Checker;
import by.arhor.psra.validation.constraint.Constraint;
import by.arhor.psra.validation.exception.ConstraintViolationException;
import by.arhor.psra.validation.exception.Status;
import by.arhor.psra.validation.exception.ReadPropertyException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Concrete implementation of {@link Validator} interface.
 *
 * @author Maksim Buryshynets
 * @version 1.0 20 January 2019
 */
@Component
public class ValidatorImpl implements Validator {

  private final Map<Class<? extends Annotation>, Checker<? extends Annotation>> checkerMap = new HashMap<>();

  @Autowired
  public ValidatorImpl(List<Checker<? extends Annotation>> checkers) {
    initialize(checkers);
  }

  /**
   * {@inheritDoc}
   *
   * @param target object to validate
   */
  @Override
  public Set<Status> validate(Object target) {
    return validateObject(target);
  }

  private void initialize(Collection<Checker<? extends Annotation>> checkers) {
    checkers.forEach(checker -> checkerMap.put(checker.getConstraintAnnotation(), checker));
  }

  /*
   * Possible stack overflow by recursive calling of methods during validation of closed object graphs.
   */
  @SuppressWarnings("unchecked")
  private Set<Status> validateObject(Object object) {
    Objects.requireNonNull(object);
    Set<Status> statusSet = new HashSet<>();
    String className = object.getClass().getSimpleName();
    for (Field field : object.getClass().getDeclaredFields()) {
      String fieldName = field.getName();
      for (Annotation annotation : field.getDeclaredAnnotations()) {
        if (isConstraint(annotation)) {
          Checker checker = checkerMap.get(annotation.annotationType());
          Object value = getProperty(object, fieldName);
          if (!checker.check(value, annotation)) {
            Status status = checker.getStatus(className, fieldName, annotation);
            statusSet.add(status);
          }
        } else if (isValidate(annotation)) {
          final Object value = getProperty(object, fieldName);
          statusSet.addAll(processNestedValidation(value));
        }
      }
    }
    return statusSet;
  }

  /*
   * Validates each object of passed Iterable interface implementation.
   */
  private Set<Status> validateIterable(Iterable iterable) {
    Set<Status> statusSet = new HashSet<>();
    for (Object object : iterable) {
      statusSet.addAll(validateObject(object));
    }
    return statusSet;
  }

  private Set<Status> processNestedValidation(Object value) {
    Set<Status> statusSet = new HashSet<>();
    if (value instanceof Iterable) {
      statusSet.addAll(validateIterable((Iterable) value));
    } else if (Objects.nonNull(value)) {
      statusSet.addAll(validateObject(value));
    }
    return statusSet;
  }

  private boolean isValidate(Annotation annotation) {
    return annotation.annotationType().equals(Validate.class);
  }

  private boolean isConstraint(Annotation annotation) {
    return annotation.annotationType().isAnnotationPresent(Constraint.class);
  }

  private Object getProperty(Object object, String propertyName) {
    try {
      return PropertyUtils.getProperty(object, propertyName);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new ReadPropertyException(e);
    }
  }

}
