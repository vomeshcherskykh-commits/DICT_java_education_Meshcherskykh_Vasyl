package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        stage1();
        stage2();
    }

    public static void stage1() {
        System.out.println("Hello! My name is BOT.");
        System.out.println("I was created in 2025.");
    }

    public static void stage2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();
        System.out.println("What a great name you have, " + name + "!");
        scanner.close();
    }
}