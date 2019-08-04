package by.arhor.psra.validation.aspect;

import by.arhor.psra.validation.Validate;
import by.arhor.psra.validation.Validator;
import by.arhor.psra.validation.exception.ConstraintViolationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * Class ValidationAspect responsible for the validation of objects. Every time
 * when calling a method that contains in its signature an argument marked with
 * the {@link Validate} annotation, the corresponding validator method is called
 * to check constraints imposed on the fields of the object being checked.
 *
 * @author Maksim Buryshynets
 * @version 1.0 24 January 2018
 */
@Aspect
@Component
public class ValidationAspect {

  private Validator validator;

  @Autowired
  public ValidationAspect(Validator validator) {
    this.validator = validator;
  }

  @Before("execution(* *(.., @by.arhor.psra.validation.Validate (*), ..))")
  public void executeValidation(JoinPoint joinPoint) {
    var statusSet = getMethodArguments(joinPoint)
        .stream()
        .filter(MethodArgument::hasAnnotation)
        .map(arg -> arg.value)
        .map(validator::validate)
        .flatMap(Collection::stream)
        .collect(toSet());
    if (!statusSet.isEmpty()) {
      throw new ConstraintViolationException(statusSet);
    }
  }

  private List<MethodArgument> getMethodArguments(JoinPoint joinPoint) {
    var arguments = new ArrayList<MethodArgument>();
    var methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
    Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
    Object[] args = joinPoint.getArgs();
    for (int i = 0; i < args.length; i++) {
      arguments.add(new MethodArgument(annotations[i], args[i]));
    }
    return arguments;
  }

  private static class MethodArgument {
    private final Annotation[] annotations;
    private final Object value;

    private MethodArgument(Annotation[] annotations, Object value) {
      this.annotations = annotations;
      this.value = value;
    }

    boolean hasAnnotation() {
      return Arrays.stream(annotations)
          .map(Annotation::annotationType)
          .anyMatch(Validate.class::equals);
    }
  }
}
