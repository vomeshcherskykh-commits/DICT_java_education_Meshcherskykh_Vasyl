package CreditCalculator;
import static java.lang.Math.*;

public class CalculatorLogic {

    public void compute(String type, String principal, String periods, String interest, String payment) {
        if (interest == null) {
            System.out.println("Incorrect parameters");
            return;
        }

        double P = principal == null ? -1 : Double.parseDouble(principal);
        double n = periods == null ? -1 : Double.parseDouble(periods);
        double i = Double.parseDouble(interest) / 100 / 12;
        double A = payment == null ? -1 : Double.parseDouble(payment);

        if (i < 0 || (P < 0 && P != -1) || (A < 0 && A != -1) || (n < 0 && n != -1)) {
            System.out.println("Incorrect parameters");
            return;
        }

        if ("diff".equals(type)) {
            if (P < 0 || n < 0 || i < 0 || A != -1) {
                System.out.println("Incorrect parameters");
                return;
            }

            int total = 0;
            for (int m = 1; m <= n; m++) {
                double Dm = ceil(P / n + i * (P - (P * (m - 1)) / n));
                total += Dm;
                System.out.println("Month " + m + ": payment is " + (int) Dm);
            }
            System.out.println("Overpayment = " + (int) (total - P));
        }

        else if ("annuity".equals(type)) {
            if (P == -1) {
                double calcP = A / ((i * pow(1 + i, n)) / (pow(1 + i, n) - 1));
                System.out.println("Your loan principal = " + (int) calcP + "!");
            } else if (A == -1) {
                double calcA = P * (i * pow(1 + i, n)) / (pow(1 + i, n) - 1);
                System.out.println("Your annuity payment = " + (int) ceil(calcA) + "!");
            } else if (n == -1) {
                double calcN = ceil(log(A / (A - i * P)) / log(1 + i));
                System.out.println("It will take " + (int) calcN + " months to repay this loan!");
            }
        } else {
            System.out.println("Incorrect parameters");
        }
    }
}