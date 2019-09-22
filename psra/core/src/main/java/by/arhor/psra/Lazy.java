package by.arhor.psra;

import java.util.function.Function;
import java.util.function.Supplier;

public class Lazy<T> {

  private final Supplier<T> source;
  private boolean computed;
  private T value;

  private Lazy(Supplier<T> source) {
    this.source = source;
  }

  public static <T> Lazy<T> eval(Supplier<T> source) {
    return new Lazy<>(source);
  }

  synchronized public T getValue() {
    if (!computed) {
      value = source.get();
      computed = true;
    }
    return value;
  }

//  public <R> Lazy<R> map(Function<T, R> f) {
//
//  }
}
