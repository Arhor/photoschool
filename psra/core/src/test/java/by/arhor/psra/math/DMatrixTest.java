package by.arhor.psra.math;

import java.util.Arrays;

public class DMatrixTest {

  public static void main(String[] args) {
    var matrix = new DMatrix(3, 3);
    System.out.println(matrix);
    var counter = 1D;
    for (int j = 0; j < matrix.rows; j++) {
      for (int i = 0; i < matrix.cols; i ++) {
        matrix.set(i, j, counter++);
      }
    }

    for (int j = 0; j < matrix.rows; j++) {
      for (int i = 0; i < matrix.cols; i ++) {
        System.out.print(matrix.get(i, j) + " ");
      }
      System.out.println();
    }
    System.out.println();
    final var transposed = matrix.transpose();
    for (int j = 0; j < matrix.rows; j++) {
      for (int i = 0; i < matrix.cols; i ++) {
        System.out.print(transposed.get(i, j) + " ");
      }
      System.out.println();
    }
  }

}
