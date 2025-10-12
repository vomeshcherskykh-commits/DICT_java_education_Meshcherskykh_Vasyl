package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    public static void main(String[] args) {

        printState();
        System.out.println("Write action (buy, fill, take):");
        String action = scanner.next();

        switch (action) {
            case "buy" -> buy();
            case "fill" -> fill();
            case "take" -> take();
        }

        printState();
    }

    static void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> { water -= 250; beans -= 16; cups--; money += 4; }
            case 2 -> { water -= 350; milk -= 75; beans -= 20; cups--; money += 7; }
            case 3 -> { water -= 200; milk -= 100; beans -= 12; cups--; money += 6; }
        }
    }

    static void fill() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        cups += scanner.nextInt();
    }

    static void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }
}
