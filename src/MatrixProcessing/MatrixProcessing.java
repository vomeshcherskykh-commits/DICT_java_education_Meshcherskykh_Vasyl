package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: > ");
        int choice = sc.nextInt();

        System.out.print("Enter matrix size: > ");
        int n = sc.nextInt(), m = sc.nextInt();
        System.out.println("Enter matrix:");
        double[][] matrix = readMatrix(sc, n, m);

        double[][] result = switch (choice) {
            case 1 -> transposeMain(matrix);
            case 2 -> transposeSide(matrix);
            case 3 -> transposeVertical(matrix);
            case 4 -> transposeHorizontal(matrix);
            default -> matrix;
        };

        System.out.println("The result is:");
        printMatrix(result);
    }

    static double[][] readMatrix(Scanner sc, int n, int m) {
        double[][] mat = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                mat[i][j] = sc.nextDouble();
        return mat;
    }

    static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (int j = 0; j < row.length; j++)
                System.out.print(row[j] + (j == row.length - 1 ? "" : " "));
            System.out.println();
        }
    }

    static double[][] transposeMain(double[][] m) {
        int n = m.length, k = m[0].length;
        double[][] t = new double[k][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k; j++)
                t[j][i] = m[i][j];
        return t;
    }

    static double[][] transposeSide(double[][] m) {
        int n = m.length, k = m[0].length;
        double[][] t = new double[k][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k; j++)
                t[k - 1 - j][n - 1 - i] = m[i][j];
        return t;
    }

    static double[][] transposeVertical(double[][] m) {
        int n = m.length, k = m[0].length;
        double[][] t = new double[n][k];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k; j++)
                t[i][j] = m[i][k - 1 - j];
        return t;
    }

    static double[][] transposeHorizontal(double[][] m) {
        int n = m.length, k = m[0].length;
        double[][] t = new double[n][k];
        for (int i = 0; i < n; i++)
            t[i] = m[n - 1 - i];
        return t;
    }
}
