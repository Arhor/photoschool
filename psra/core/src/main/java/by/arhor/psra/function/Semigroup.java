package by.arhor.psra.function;

import java.util.function.BinaryOperator;

@FunctionalInterface
public interface Semigroup<T> extends BinaryOperator<T> {
}
