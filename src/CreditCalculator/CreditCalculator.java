package CreditCalculator;

import java.util.Scanner;
import static java.lang.Math.*;

public class CreditCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What do you want to calculate?\ntype \"n\" for number of monthly payments,\ntype \"a\" for annuity monthly payment amount,\ntype \"p\" for loan principal:");

        String type = sc.next();

        if (type.equals("n")) {

            System.out.println("Enter the loan principal:");
            double P = sc.nextDouble();

            System.out.println("Enter the monthly payment:");
            double A = sc.nextDouble();

            System.out.println("Enter the loan interest:");
            double interest = sc.nextDouble() / 100;

            double i = interest / 12;

            double n = ceil(log(A / (A - i * P)) / log(1 + i));
            int totalMonths = (int) n;

            int years = totalMonths / 12;
            int months = totalMonths % 12;

            System.out.print("It will take ");
            if (years > 0) System.out.print(years + " years");
            if (years > 0 && months > 0) System.out.print(" and ");
            if (months > 0) System.out.print(months + " months");
            System.out.println(" to repay this loan!");

        } else if (type.equals("a")) {

            System.out.println("Enter the loan principal:");
            double P = sc.nextDouble();

            System.out.println("Enter the number of periods:");
            int n = sc.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = sc.nextDouble() / 100;

            double i = interest / 12;

            double A = P * (i * pow(1 + i, n)) / (pow(1 + i, n) - 1);
            System.out.println("Your annuity payment = " + (int) ceil(A) + "!");

        } else if (type.equals("p")) {

            System.out.println("Enter the annuity payment:");
            double A = sc.nextDouble();

            System.out.println("Enter the number of periods:");
            int n = sc.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = sc.nextDouble() / 100;

            double i = interest / 12;

            double P = A / ((i * pow(1 + i, n)) / (pow(1 + i, n) - 1));
            System.out.println("Your loan principal = " + (int) floor(P) + "!");

        }
    }
}
