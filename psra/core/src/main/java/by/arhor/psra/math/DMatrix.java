package by.arhor.psra.math;

import java.util.Arrays;
import java.util.Objects;

public class DMatrix implements Matrix {

  private static final double[] EMPTY_STORE = {};

  // FIXME - cache determinant

  private final double[] store;
  public final int cols;
  public final int rows;

  public DMatrix(final int cols, final int rows) {
    if (cols < 0 || rows < 0) {
      throw new IllegalArgumentException("Illegal initial size: " +
          "cols = " + cols + ", " +
          "rows = " + rows);
    }
    this.cols = cols;
    this.rows = rows;

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

  public double get(final int col, final int row) {
    ensureIndex(col, row);
    return store[row * cols + col];
  }

  public void set(final int col, final int row, final double value) {
    ensureIndex(col, row);
    store[row * cols + col] = value;
  }

  public boolean hasDeterminant() {
    return (cols == rows);
  }

  public double determinant() {
    if (hasDeterminant()) {
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
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        matrix.set(j, i, get(i, j));
      }
    }
    return matrix;
  }

  public DMatrix plus(DMatrix that) {
    Objects.requireNonNull(that);
    if (this.cols == that.cols && this.rows == that.rows) {
      final var matrix = new DMatrix(cols, rows);
      for (int i = 0; i < store.length; i++) {
        matrix.store[i] = this.store[i] + that.store[i];
      }
      return matrix;
    }
    throw new IllegalArgumentException("Incompatible operand size:\n" +
        "\tthis - " + this.sizeInfo() + "\n" +
        "\tthat - " + that.sizeInfo());
  }

  private void ensureIndex(final int col, final int row) {
    if (store == EMPTY_STORE
        || col >= cols
        || row >= rows) {
      throw new IndexOutOfBoundsException("Actual size: " + sizeInfo());
    }
  }

  private String sizeInfo() {
    return "cols = " + cols + ", rows = " + rows;
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
    return cols == dMatrix.cols
        && rows == dMatrix.rows
        && Arrays.equals(store, dMatrix.store);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(cols, rows);
    result = 31 * result + Arrays.hashCode(store);
    return result;
  }

  // FIXME - move to separate class - MatrixUtils
  @Override
  public String toString() {
    final var sb = new StringBuilder();
    for (int j = 0; j < rows; j++) {
      for (int i = 0; i < cols; i++) {
        sb.append(get(i, j)).append((i == cols - 1) ? '\n' : ' ');
      }
    }
    return sb.toString();
  }
}
