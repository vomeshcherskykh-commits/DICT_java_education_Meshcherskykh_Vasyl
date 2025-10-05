package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        stage1();
        stage2(scanner);
        stage3(scanner);
        stage4(scanner);

        scanner.close();
    }

    public static void stage1() {
        System.out.println("Hello! My name is BOT.");
        System.out.println("I was created in 2025.");
    }

    public static void stage2(Scanner scanner) {
        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();
        System.out.println("What a great name you have, " + name + "!");
    }

    public static void stage3(Scanner scanner) {
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int rem3 = scanner.nextInt();
        int rem5 = scanner.nextInt();
        int rem7 = scanner.nextInt();

        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
        System.out.println("Your age is " + age + "; that's a good time to start programming!");
    }

    public static void stage4(Scanner scanner) {
        System.out.println("Now I will prove to you that I can count to any number you want!");
        int num = scanner.nextInt();

        for (int i = 0; i <= num; i++) {
            System.out.println(i + "!");
        }
    }
}