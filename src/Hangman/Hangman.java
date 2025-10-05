package Hangman;

import java.util.*;

public class Hangman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        stage1();
        stage2mod5(scanner);

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

    public static void stage2mod3(Scanner scanner, Random random) {

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        char[] hidden = new char[word.length()];
        Arrays.fill(hidden, '-');

        for (int attempts = 8; attempts > 0; attempts--) {
            System.out.println(hidden);
            System.out.print("Input a letter: > ");
            char letter = scanner.nextLine().charAt(0);

            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    hidden[i] = letter;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("That letter doesn't appear in the word");
            }

            if (new String(hidden).equals(word)) break;
        }

        System.out.println("Thanks for playing!");
        System.out.println("We'll see how well you did in the next stage");
    }

    public static void stage2mod4(Scanner scanner, Random random) {

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        char[] hidden = new char[word.length()];
        Arrays.fill(hidden, '-');
        int lives = 8;

        while (lives > 0) {
            System.out.println(hidden);
            System.out.print("Input a letter: > ");
            String input = scanner.nextLine();

            if (input.length() != 1) continue;
            char letter = input.charAt(0);

            if (word.indexOf(letter) == -1) {
                System.out.println("That letter doesn't appear in the word");
                lives--;
            }
            else if (new String(hidden).indexOf(letter) != -1) {
                System.out.println("No improvements");
                lives--;
            }
            else {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        hidden[i] = letter;
                    }
                }
            }

            if (new String(hidden).equals(word)) {
                System.out.println(hidden);
                System.out.println("You guessed the word!");
                System.out.println("You survived!");
                return;
            }
        }
        System.out.println("You lost!");
    }

    public static void stage2mod5(Scanner scanner) {

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[new Random().nextInt(words.length)];

        char[] hidden = new char[word.length()];
        Arrays.fill(hidden, '-');

        int lives = 8;

        while (lives > 0) {
            System.out.println(hidden);
            System.out.print("Input a letter: > ");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println("You should input a single letter");
                continue;
            }

            char letter = input.charAt(0);
            if (letter < 'a' || letter > 'z') {
                System.out.println("Please enter a lowercase English letter");
                continue;
            }

            if (new String(hidden).indexOf(letter) != -1) {
                System.out.println("You've already guessed this letter");
                continue;
            }

            if (word.indexOf(letter) == -1) {
                System.out.println("That letter doesn't appear in the word");
                lives--;
            } else {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        hidden[i] = letter;
                    }
                }
            }

            if (new String(hidden).equals(word)) {
                System.out.println(hidden);
                System.out.println("You guessed the word " + word + "!");
                System.out.println("You survived!");
                return;
            }
        }

        System.out.println("You lost!");
    }
}

