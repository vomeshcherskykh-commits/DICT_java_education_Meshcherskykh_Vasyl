package MatrixProcessing;

public class MatrixCalculator {

    public static Matrix add(Matrix a, Matrix b) {
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) return null;
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++)
            for (int j = 0; j < a.getCols(); j++)
                result[i][j] = a.get(i, j) + b.get(i, j);
        return new Matrix(result);
    }

    public static Matrix multiplyByConstant(Matrix a, double c) {
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++)
            for (int j = 0; j < a.getCols(); j++)
                result[i][j] = a.get(i, j) * c;
        return new Matrix(result);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.getCols() != b.getRows()) return null;
        double[][] result = new double[a.getRows()][b.getCols()];
        for (int i = 0; i < a.getRows(); i++)
            for (int j = 0; j < b.getCols(); j++)
                for (int k = 0; k < a.getCols(); k++)
                    result[i][j] += a.get(i, k) * b.get(k, j);
        return new Matrix(result);
    }

    public static Matrix transposeMain(Matrix m) {
        double[][] t = new double[m.getCols()][m.getRows()];
        for (int i = 0; i < m.getRows(); i++)
            for (int j = 0; j < m.getCols(); j++)
                t[j][i] = m.get(i, j);
        return new Matrix(t);
    }

    public static Matrix transposeSide(Matrix m) {
        double[][] t = new double[m.getCols()][m.getRows()];
        for (int i = 0; i < m.getRows(); i++)
            for (int j = 0; j < m.getCols(); j++)
                t[m.getCols() - 1 - j][m.getRows() - 1 - i] = m.get(i, j);
        return new Matrix(t);
    }

    public static Matrix transposeVertical(Matrix m) {
        double[][] t = new double[m.getRows()][m.getCols()];
        for (int i = 0; i < m.getRows(); i++)
            for (int j = 0; j < m.getCols(); j++)
                t[i][j] = m.get(i, m.getCols() - 1 - j);
        return new Matrix(t);
    }

    public static Matrix transposeHorizontal(Matrix m) {
        double[][] t = new double[m.getRows()][m.getCols()];
        for (int i = 0; i < m.getRows(); i++)
            for (int j = 0; j < m.getCols(); j++)
                t[i][j] = m.get(m.getRows() - 1 - i, j);
        return new Matrix(t);
    }

    public static double determinant(Matrix m) {
        int n = m.getRows();
        if (n != m.getCols()) throw new IllegalArgumentException("Matrix must be square");
        if (n == 1) return m.get(0, 0);
        if (n == 2) return m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);

        double det = 0;
        for (int k = 0; k < n; k++) {
            double[][] minor = new double[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int colIndex = 0;
                for (int j = 0; j < n; j++) {
                    if (j == k) continue;
                    minor[i - 1][colIndex++] = m.get(i, j);
                }
            }
            det += Math.pow(-1, k) * m.get(0, k) * determinant(new Matrix(minor));
        }
        return det;
    }

    public static Matrix inverse(Matrix a) {
        int n = a.getRows();
        double det = determinant(a);
        if (Math.abs(det) < 1e-9) return null;

        double[][] aug = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) aug[i][j] = a.get(i, j);
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

        return new Matrix(inv);
    }
}
