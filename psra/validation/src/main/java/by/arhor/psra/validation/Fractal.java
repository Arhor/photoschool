package by.arhor.psra.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fractal {

  private Fractal() {}

  public static <T, R> Collection<R> deepDive(T target, Predicate<Annotation> filter, Function<T, R> job) {
    if (target != null) {
      Field[] fields = target.getClass().getDeclaredFields();
      for (var f : fields) {
        Annotation[] annotations = f.getDeclaredAnnotations();
        for (var a : annotations) {
          if (filter.test(a)) {

          }
        }
      }
      return null;
    } else {
      throw new IllegalArgumentException("Deep dive target must not be null");
    }
  }

  private static <T, R> Collection<R> traverse(
      Iterable<T> seq, Predicate<Annotation> filter,
      Function<T, R> job) {
    if (seq != null) {
      var result = new ArrayList<R>();
      for (T target : seq) {
        var d = deepDive(target, filter, job);
        if (d != null) {
          result.addAll(d);
        }
      }
      return result;
    } else {
      throw new IllegalArgumentException("Deep dive target must not be null");
    }
  }
}
