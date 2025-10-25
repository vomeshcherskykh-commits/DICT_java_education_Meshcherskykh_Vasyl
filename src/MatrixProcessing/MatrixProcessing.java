package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = sc.nextDouble();

        double c = sc.nextDouble();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double val = a[i][j] * c;
                System.out.print((val % 1 == 0 ? (int) val : val) + " ");
            }
            System.out.println();
        }
    }
}
