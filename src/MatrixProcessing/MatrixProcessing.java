package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("0. Exit");
            System.out.print("Your choice: > ");

            int choice = sc.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> addMatrices(sc);
                case 2 -> multiplyByConstant(sc);
                case 3 -> multiplyMatrices(sc);
                default -> System.out.println("Unknown option.");
            }
        }
    }

    static void addMatrices(Scanner sc) {
        System.out.print("Enter size of first matrix: > ");
        int n1 = sc.nextInt(), m1 = sc.nextInt();
        System.out.println("Enter first matrix:");
        double[][] A = readMatrix(sc, n1, m1);

        System.out.print("Enter size of second matrix: > ");
        int n2 = sc.nextInt(), m2 = sc.nextInt();
        System.out.println("Enter second matrix:");
        double[][] B = readMatrix(sc, n2, m2);

        if (n1 != n2 || m1 != m2) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        double[][] result = new double[n1][m1];
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < m1; j++)
                result[i][j] = A[i][j] + B[i][j];

        System.out.println("The result is:");
        printMatrix(result);
    }

    static void multiplyByConstant(Scanner sc) {
        System.out.print("Enter size of matrix: > ");
        int n = sc.nextInt(), m = sc.nextInt();
        System.out.println("Enter matrix:");
        double[][] matrix = readMatrix(sc, n, m);
        System.out.print("Enter constant: > ");
        double c = sc.nextDouble();

        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[i][j] = matrix[i][j] * c;

        System.out.println("The result is:");
        printMatrix(result);
    }

    static void multiplyMatrices(Scanner sc) {
        System.out.print("Enter size of first matrix: > ");
        int n1 = sc.nextInt(), m1 = sc.nextInt();
        System.out.println("Enter first matrix:");
        double[][] A = readMatrix(sc, n1, m1);

        System.out.print("Enter size of second matrix: > ");
        int n2 = sc.nextInt(), m2 = sc.nextInt();
        System.out.println("Enter second matrix:");
        double[][] B = readMatrix(sc, n2, m2);

        if (m1 != n2) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        double[][] result = new double[n1][m2];
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < m2; j++)
                for (int k = 0; k < m1; k++)
                    result[i][j] += A[i][k] * B[k][j];

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
}
