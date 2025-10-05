package Hangman;

import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        stage1();
        stage2(scanner);

        scanner.close();
    }

    public static void stage1() {
        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");
    }

    public static void stage2(Scanner scanner) {
        System.out.print("Guess the word: > ");
        String guess = scanner.nextLine();

        if ("java".equals(guess)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}
