package machine;

import java.net.Inet4Address;
import java.util.Scanner;

public class CoffeeMachine {

    static int milk;
    static int water;
    static int beans;
    static int balance;
    static int cups;

    private static void init() {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        balance = 550;
    }

    private static void printState() {
        System.out.
                printf("The coffee machine has:%n%d ml of water%n%d ml of milk%n%d g of coffee beans%n%d disposable cups%n$%d of money%n",
                        water, milk, beans, cups, balance);
    }

    private static void fill(int w, int m, int b, int c) {
        milk += m;
        water += w;
        beans += b;
        cups += c;
    }

    private static void makeCoffee(int w, int m, int b, int price) {
        water -= w;
        milk -= m;
        beans -= b;
        balance += price;
        cups--;
    }

    private static int checkSupply(int w, int m, int b) {
        if (water < w) return 0;
        if (milk < m) return 1;
        if (beans < b) return 2;
        if (cups <= 0) return 3;
        return -1;
    }
    private static void buy(int option) {
        int resource = switch (option) {
            case 1 -> checkSupply(250, 0, 16);
            case 2 -> checkSupply(350, 75, 20);
            case 3 -> checkSupply(200, 100, 12);
            default -> -1;
        };
        if (resource == -1) {
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            switch (resource) {
                case 0 -> System.out.println("Sorry, not enough water!");
                case 1 -> System.out.println("Sorry, not enough milk!");
                case 2 -> System.out.println("Sorry, not enough coffee beans!");
                case 3 -> System.out.println("Sorry, not enough disposable cups!");
            }
            return;
        }
        switch (option) {
            case 1 -> makeCoffee(250, 0, 16, 4);
            case 2 -> makeCoffee(350, 75, 20, 7);
            case 3 -> makeCoffee(200, 100, 12, 6);
        }
    }

    private static void take() {
        System.out.println("I gave you $" + balance);
        balance = 0;
    }

    private static void isSupplyEnough(int option) {

    }

    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String option = scanner.nextLine();
                    if ("back".equals(option)) break;
                    buy(Integer.parseInt(option));
                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add: ");
                    int waterfill = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many ml of milk you want to add: ");
                    int milkfill = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    int beanfill = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many disposable cups you want to add: ");
                    int cupfill = Integer.parseInt(scanner.nextLine());
                    fill(waterfill, milkfill, beanfill, cupfill);
                    break;
                case "take":
                    take();
                    break;
                case "exit":
                    exit = true;
                    break;
                case "remaining":
                    printState();
                    break;
            }
        }
    }
}
