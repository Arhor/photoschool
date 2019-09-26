package by.arhor.psra.math;

public interface Matrix {

  int cols();

  int rows();

  Matrix transpose();

  default boolean isSquare() {
    return (cols() == rows());
  }

}
