package by.arhor.psra.function;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Lazy<T> implements RichSupplier<T> {

  private final Supplier<T> source;

  private boolean computed;
  private T value;

  private Lazy(Supplier<T> source) {
    this.source = source;
  }

  public static <T> Lazy<T> eval(Supplier<T> source) {
    Objects.requireNonNull(source, "Lazy evaluation source must not be null");
    return new Lazy<>(source);
  }

  public T get() {
    if (!computed) {
      synchronized (this) {
        if (!computed) {
          value = source.get();
          computed = true;
        }
      }
    }
    return value;
  }

  public boolean isComputed() {
    return computed;
  }

  @Override
  public <R> Lazy<R> map(Function<T, R> f) {
    return new Lazy<>(() -> f.apply(this.get()));
  }

  @Override
  public <R> Lazy<R> flatMap(Function<T, Lazy<R>> f) {
    return new Lazy<>(() -> f.apply(this.get()).get());
  }

  @Override
  public <R, U> Lazy<U> merge(Lazy<R> that, BiFunction<T, R, U> f) {
    return new Lazy<>(() -> f.apply(this.get(), that.get()));
  }
}
