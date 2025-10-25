package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter matrix size: > ");
        int n = sc.nextInt(), m = sc.nextInt();
        System.out.println("Enter matrix:");
        double[][] matrix = readMatrix(sc, n, m);

        if (n != m) {
            System.out.println("Matrix must be square.");
            return;
        }

        System.out.println("The result is:");
        System.out.println((int) determinant(matrix));
    }

    static double[][] readMatrix(Scanner sc, int n, int m) {
        double[][] mat = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                mat[i][j] = sc.nextDouble();
        return mat;
    }

    static double determinant(double[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0]*m[1][1] - m[0][1]*m[1][0];

        double det = 0;
        for (int k = 0; k < n; k++) {
            double[][] minor = new double[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int col = 0;
                for (int j = 0; j < n; j++) {
                    if (j == k) continue;
                    minor[i - 1][col++] = m[i][j];
                }
            }
            det += Math.pow(-1, k) * m[0][k] * determinant(minor);
        }
        return det;
    }
}
