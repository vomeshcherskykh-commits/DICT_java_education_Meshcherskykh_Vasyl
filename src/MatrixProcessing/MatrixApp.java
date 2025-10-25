package MatrixProcessing;

import java.util.Scanner;

public class MatrixApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: > ");

            int choice = sc.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> addMatrices(sc);
                case 2 -> multiplyByConstant(sc);
                case 3 -> multiplyMatrices(sc);
                case 4 -> transposeMatrix(sc);
                case 5 -> calculateDeterminant(sc);
                case 6 -> inverseMatrix(sc);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addMatrices(Scanner sc) {
        System.out.print("Enter size of first matrix: > ");
        Matrix A = readMatrix(sc);
        System.out.print("Enter size of second matrix: > ");
        Matrix B = readMatrix(sc);

        Matrix res = MatrixCalculator.add(A, B);
        if (res == null) System.out.println("The operation cannot be performed.");
        else {
            System.out.println("The result is:");
            res.print();
        }
    }

    private static void multiplyByConstant(Scanner sc) {
        System.out.print("Enter size of matrix: > ");
        Matrix A = readMatrix(sc);
        System.out.print("Enter constant: > ");
        double c = sc.nextDouble();
        System.out.println("The result is:");
        MatrixCalculator.multiplyByConstant(A, c).print();
    }

    private static void multiplyMatrices(Scanner sc) {
        System.out.print("Enter size of first matrix: > ");
        Matrix A = readMatrix(sc);
        System.out.print("Enter size of second matrix: > ");
        Matrix B = readMatrix(sc);
        Matrix res = MatrixCalculator.multiply(A, B);
        if (res == null) System.out.println("The operation cannot be performed.");
        else {
            System.out.println("The result is:");
            res.print();
        }
    }

    private static void transposeMatrix(Scanner sc) {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: > ");
        int t = sc.nextInt();
        System.out.print("Enter matrix size: > ");
        Matrix A = readMatrix(sc);

        Matrix res = switch (t) {
            case 1 -> MatrixCalculator.transposeMain(A);
            case 2 -> MatrixCalculator.transposeSide(A);
            case 3 -> MatrixCalculator.transposeVertical(A);
            case 4 -> MatrixCalculator.transposeHorizontal(A);
            default -> null;
        };

        if (res != null) {
            System.out.println("The result is:");
            res.print();
        }
    }

    private static void calculateDeterminant(Scanner sc) {
        System.out.print("Enter matrix size: > ");
        Matrix A = readMatrix(sc);
        System.out.println("Enter matrix:");
        double det = MatrixCalculator.determinant(A);
        System.out.println("The result is:");
        System.out.println((int) det);
    }

    private static void inverseMatrix(Scanner sc) {
        System.out.print("Enter matrix size: > ");
        Matrix A = readMatrix(sc);
        Matrix inv = MatrixCalculator.inverse(A);
        if (inv == null)
            System.out.println("This matrix doesn't have an inverse.");
        else {
            System.out.println("The result is:");
            inv.print();
        }
    }

    private static Matrix readMatrix(Scanner sc) {
        int n = sc.nextInt(), m = sc.nextInt();
        System.out.println("Enter matrix:");
        double[][] data = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                data[i][j] = sc.nextDouble();
        return new Matrix(data);
    }
}
