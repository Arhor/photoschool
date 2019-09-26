package by.arhor.psra.math;

public final class DMatrixSquare extends DMatrix {

  public DMatrixSquare(int size) {
    super(size);
  }

  @Override
  public boolean isSquare() {
    return true;
  }

  public double det() {
    return 0;
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
}
