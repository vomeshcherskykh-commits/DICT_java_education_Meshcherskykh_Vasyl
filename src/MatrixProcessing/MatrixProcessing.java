package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        double[][] a = new double[n1][m1];
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < m1; j++)
                a[i][j] = sc.nextDouble();

        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        double[][] b = new double[n2][m2];
        for (int i = 0; i < n2; i++)
            for (int j = 0; j < m2; j++)
                b[i][j] = sc.nextDouble();

        if (n1 != n2 || m1 != m2) {
            System.out.println("ERROR");
            return;
        }

        double[][] sum = new double[n1][m1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                sum[i][j] = a[i][j] + b[i][j];
                System.out.print((sum[i][j] % 1 == 0 ? (int) sum[i][j] : sum[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
