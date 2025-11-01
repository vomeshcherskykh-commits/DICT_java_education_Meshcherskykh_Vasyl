package CreditCalculator;

public class CreditCalculator {
    public static void main(String[] args) {
        CalculatorLogic calc = new CalculatorLogic();

        calc.compute(
                System.getProperty("type"),
                System.getProperty("principal"),
                System.getProperty("periods"),
                System.getProperty("interest"),
                System.getProperty("payment")
        );
    }
}