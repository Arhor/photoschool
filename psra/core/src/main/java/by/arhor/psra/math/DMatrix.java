package by.arhor.psra.math;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class DMatrix implements Matrix {

  private static final double[] EMPTY_STORE = {};

  // FIXME - cache determinant

  private final double[] store;
  public final int rows;
  public final int cols;

  public DMatrix(final int rows, final int cols) {
    if ((rows < 0) || (cols < 0)) {
      throw new IllegalArgumentException("Illegal initial size: " + toString());
    }
    this.rows = rows;
    this.cols = cols;

    final int size = cols * rows;

    this.store = (size >= 0) ? new double[size] : EMPTY_STORE;
  }

  public DMatrix(final int size) {
    this(size, size);
  }

  @Override
  public int cols() {
    return cols;
  }

  @Override
  public int rows() {
    return rows;
  }

  public double get(final int row, final int col) {
    ensureIndex(row, col);
    return store[row * cols + col];
  }

  public void set(final int row, final int col, final double value) {
    ensureIndex(row, col);
    store[row * cols + col] = value;
  }

  public DMatrix plus(DMatrix that) {
    Objects.requireNonNull(that);
    if ((this.cols == that.cols) && (this.rows == that.rows)) {
      final var matrix = new DMatrix(cols, rows);
      for (int i = 0; i < store.length; i++) {
        matrix.store[i] = this.store[i] + that.store[i];
      }
      return matrix;
    }
    throw new IllegalArgumentException("Incompatible operand size:\n" +
        "\tthis - " + this.toString() + "\n" +
        "\tthat - " + that.toString());
  }

  public boolean isSquare() {
    return (cols == rows);
  }

  public double determinant() {
    if (isSquare()) {
      return 0;
    }
    throw new UnsupportedOperationException("Determinant can be computed only for square matrices");
  }

  // FIXME - fix computations
  private double computeDeterminant(int i, int j) {
    final var even = (i + 1) % 2 == 0;
    if ((cols - i) == 2) {
      final var a = get(i, j);
      final var b = get(i + 1, j);
      final var c = get(i, j + 1);
      final var d = get(i + 1, j + 1);
      return (even ? 1 : -1) * (a * d - c * b);
    } else {
      var accumulator = 0;
      for (int index = i; index < cols; index++) {
        accumulator += computeDeterminant(index, j + 1);
      }
      return accumulator;
    }
  }

  public DMatrix transpose() {
    final var matrix = new DMatrix(rows, cols);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix.set(j, i, get(i, j));
      }
    }
    return matrix;
  }

  private void ensureIndex(final int col, final int row) {
    if (store == EMPTY_STORE
        || row >= rows
        || col >= cols) {
      throw new IndexOutOfBoundsException("Actual size: " + toString());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DMatrix dMatrix = (DMatrix) o;
    return rows == dMatrix.rows
        && cols == dMatrix.cols
        && Arrays.equals(store, dMatrix.store);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(rows, cols);
    result = 31 * result + Arrays.hashCode(store);
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DMatrix.class.getSimpleName() + "[", "]")
        .add("rows=" + rows)
        .add("cols=" + cols)
        .toString();
  }
}
