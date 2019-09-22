package by.arhor.psra;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

  public static final Lazy<?> ZERO = new Lazy<>(() -> null) {
    @Override
    public Object get() {
      return null;
    }
    @Override
    public boolean isComputed() {
      return true;
    }
  };

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

  public <R> Lazy<R> map(Function<T, R> f) {
    return new Lazy<>(() -> f.apply(this.get()));
  }

  public <R> Lazy<R> flatMap(Function<T, Lazy<R>> f) {
    return new Lazy<>(() -> f.apply(this.get()).get());
  }

  public <R, U> Lazy<U> merge(Lazy<R> that, BiFunction<T, R, U> f) {
    return new Lazy<>(() -> f.apply(this.get(), that.get()));
  }

  public void forEach(Consumer<T> action) {
    action.accept(get());
  }
}
