package by.arhor.psra.math;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class DMatrix implements Matrix {

  private static final double[] EMPTY_STORE = {};

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

  @Override
  public DMatrix transpose() {
    final var matrix = new DMatrix(rows, cols);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix.set(j, i, get(i, j));
      }
    }
    return matrix;
  }

  public double get(final int row, final int col) {
    ensureIndex(row, col);
    return store[row * cols + col];
  }

  public void set(final int row, final int col, final double value) {
    ensureIndex(row, col);
    store[row * cols + col] = value;
  }

  public void fill(double arg) {
    Arrays.fill(store, arg);
  }

  public void clear() {
    fill(0d);
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

  public DMatrix plus(double arg) {
    final var matrix = new DMatrix(rows, cols);
    for (int i = 0; i < store.length; i++) {
      matrix.store[i] = this.store[i] + arg;
    }
    return matrix;
  }

  public DMatrix multiply(double arg) {
    final var matrix = new DMatrix(rows, cols);
    for (int i = 0; i < store.length; i++) {
      matrix.store[i] = this.store[i] * arg;
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

  private void swapRows(int i, int j) {
    if ((i >= 0) && (j >= 0) && (i < rows) && (j < rows)) {

    }
    throw new IndexOutOfBoundsException("Actual rows: " + rows);
  }

  private void swapCols(int i, int j) {
    if ((i >= 0) && (j >= 0) && (i < cols) && (j < cols)) {

    }
    throw new IndexOutOfBoundsException("Actual cols: " + cols);
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
