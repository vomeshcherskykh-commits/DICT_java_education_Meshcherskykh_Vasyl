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

        double det = determinant(matrix);
        if (Math.abs(det) < 1e-9) {
            System.out.println("This matrix doesn't have an inverse.");
            return;
        }

        double[][] inv = inverse(matrix);
        System.out.println("The result is:");
        for (double[] row : inv) {
            for (int j = 0; j < row.length; j++)
                System.out.printf("%.2f%s", row[j], j == row.length - 1 ? "" : " ");
            System.out.println();
        }
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

    static double[][] inverse(double[][] a) {
        int n = a.length;
        double[][] aug = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) aug[i][j] = a[i][j];
            aug[i][n + i] = 1;
        }

        for (int i = 0; i < n; i++) {
            double pivot = aug[i][i];
            for (int j = 0; j < 2 * n; j++) aug[i][j] /= pivot;
            for (int k = 0; k < n; k++) {
                if (k == i) continue;
                double factor = aug[k][i];
                for (int j = 0; j < 2 * n; j++)
                    aug[k][j] -= factor * aug[i][j];
            }
        }

        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(aug[i], n, inv[i], 0, n);
        return inv;
    }
}
