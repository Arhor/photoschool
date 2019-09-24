package by.arhor.psra.function;

import java.io.OptionalDataException;

public abstract class Monoid<T> implements Semigroup<T> {

  private final Semigroup<T> semigroup;

  public Monoid(Semigroup<T> semigroup) {
    this.semigroup = semigroup;
  }

  @Override
  public T apply(T a, T b) {
    if (a == unit()) {
      return a;
    }
    if (b == unit()) {
      return b;
    }
    return semigroup.apply(a, b);
  }

  public abstract T unit();

}
