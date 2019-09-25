package by.arhor.psra.math;

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
    System.out.println();
    System.out.println(matrix);

    System.out.println();
    System.out.println(matrix.transpose());

    System.out.println();
    System.out.println(matrix.determinant());
  }

}
