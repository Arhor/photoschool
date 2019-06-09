package by.arhor.util;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

  private final Supplier<T> supplier;
  private boolean supplied;
  private T value;

  private Lazy(Supplier<T> supplier) {
    Objects.requireNonNull(supplier);
    this.supplier = supplier;
  }

  public static <T> Lazy<T> eval(Supplier<T> supplier) {
    Objects.requireNonNull(supplier);
    return new Lazy<>(supplier);
  }

  private T supply() {
    value = supplier.get();
    supplied = true;
    return value;
  }

  @Override
  public T get() {
    return supplied ? value : supply();
  }

  public Lazy<T> filter(Predicate<T> filter) {
    Objects.requireNonNull(supplier);
    return new Lazy<>(() -> filter.test(get())
        ? value
        : null);
  }

  public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(mapper);
    return new Lazy<>(() -> get() != null
        ? mapper.apply(value)
        : null);
  }

  public <R> Lazy<R> flatMap(Function<? super T, Lazy<R>> mapper) {
    Objects.requireNonNull(mapper);
    return mapper.apply(get());
  }

}
