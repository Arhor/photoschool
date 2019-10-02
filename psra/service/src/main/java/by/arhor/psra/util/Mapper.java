package by.arhor.psra.util;

import java.util.function.Function;

@FunctionalInterface
public interface Mapper<T, R> extends Function<T, R> {

  /**
   * Alias for {@link Function#apply} method
   */
  default R map(T t) {
    return apply(t);
  }

}
