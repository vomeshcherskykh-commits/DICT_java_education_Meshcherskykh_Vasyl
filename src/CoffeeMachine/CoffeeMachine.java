package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    enum State {
        CHOOSING_ACTION, BUYING, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS, EXIT
    }

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    static State state = State.CHOOSING_ACTION;

    public static void main(String[] args) {
        while (state != State.EXIT) {
            handleInput();
        }
    }

    static void handleInput() {
        switch (state) {
            case CHOOSING_ACTION -> {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String action = scanner.next();

                switch (action) {
                    case "buy" -> state = State.BUYING;
                    case "fill" -> state = State.FILLING_WATER;
                    case "take" -> take();
                    case "remaining" -> printState();
                    case "exit" -> state = State.EXIT;
                }
            }
            case BUYING -> buy();
            case FILLING_WATER -> fillWater();
            case FILLING_MILK -> fillMilk();
            case FILLING_BEANS -> fillBeans();
            case FILLING_CUPS -> fillCups();
        }
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
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        if (choice.equals("back")) {
            state = State.CHOOSING_ACTION;
            return;
        }

        int type = Integer.parseInt(choice);
        int needWater = 0, needMilk = 0, needBeans = 0, cost = 0;

        switch (type) {
            case 1 -> { needWater = 250; needBeans = 16; cost = 4; }
            case 2 -> { needWater = 350; needMilk = 75; needBeans = 20; cost = 7; }
            case 3 -> { needWater = 200; needMilk = 100; needBeans = 12; cost = 6; }
        }

        if (water < needWater) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < needMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < needBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= needWater;
            milk -= needMilk;
            beans -= needBeans;
            cups--;
            money += cost;
        }
        state = State.CHOOSING_ACTION;
    }

    static void fillWater() {
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        state = State.FILLING_MILK;
    }

    static void fillMilk() {
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        state = State.FILLING_BEANS;
    }

    static void fillBeans() {
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += scanner.nextInt();
        state = State.FILLING_CUPS;
    }

    static void fillCups() {
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
        state = State.CHOOSING_ACTION;
    }

    static void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }
}