package Hangman;

import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        stage1();
        stage2mod2(scanner, random);

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

    public static void stage2mod(Scanner scanner, Random random) {

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        System.out.println("HANGMAN");
        System.out.print("Guess the word: > ");
        String guess = scanner.nextLine();

        if (word.equals(guess)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }

    public static void stage2mod2(Scanner scanner, Random random) {

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        String hint = word.substring(0, 2) + "-".repeat(word.length() - 2);

        System.out.println("HANGMAN");
        System.out.print("Guess the word " + hint + ": > ");
        String guess = scanner.nextLine();

        if (word.equals(guess)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}

