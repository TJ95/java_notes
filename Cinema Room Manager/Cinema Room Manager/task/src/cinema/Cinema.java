package cinema;

import java.util.*;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SeatReservation reservation = new SeatReservation();
        reservation.getRowsAndSeatsNum(scanner);
        reservation.initFreeSeats(scanner);
        //reservation.printTicketPrice(scanner);
        //reservation.displaySeats();
        reservation.menu(scanner);
    }
}



class SeatReservation {
    static int rows;
    static int seats;
    static int[][] bookedSeats;
    static int row;
    static int seat;
    static int purchased;
    static int total_seats;
    static int income;
    static int total_income;
    static double percent;

    static void getRowsAndSeatsNum(Scanner scanner){
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        System.out.println();
    }
    
    static void initFreeSeats(Scanner scanner) {
        bookedSeats = new int[rows][seats];
        purchased = 0;
        total_seats = rows * seats;
        income = 0;
        total_income = total_seats > 60 ? 
            rows / 2 * seats * 10 + (rows - rows/2) * seats * 8 : total_seats * 10;
        percent = 0;
        displaySeats();        
    }
    
    static void displaySeats() {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0)               //empty corner
                    System.out.print("  ");
                else if (i == 0)                    //seats numbers
                    System.out.print(j + " ");
                else if (j == 0)                    //rows numbers
                    System.out.print(i + " ");
                else {                              //booked (B) or free (S) seat
                    System.out.print(bookedSeats[i-1][j-1] == 0 ? "S " : "B ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    static void printTicketPrice(Scanner scanner) {
        //getRowAndSeat(scanner);
        int price = calcTicketPrice();
        System.out.println("Ticket price: $" + price);
        System.out.println();
    }
    
    static void getRowAndSeat(Scanner scanner) {
        System.out.println("Enter a row number:");
        row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seat = scanner.nextInt();
        if (row > rows || seat > seats) { 
            System.out.println("Wrong input!");
        } else if (bookedSeats[row - 1][seat - 1] == 1) {
            System.out.println("\nThat ticket has already been purchased!");
            System.out.println();
            getRowAndSeat(scanner);
        } else {
            bookedSeats[row-1][seat-1] = 1;
            purchased++;
            percent = purchased / total_seats;
            income += calcTicketPrice();
            printTicketPrice(scanner);
        }
    }
    
    static int calcTicketPrice() {
        return rows * seats <= 60
            ? 10
            : row <= (rows / 2) ? 10 : 8;
    }
    
    static void showStats() {
        System.out.printf("Number of purchased tickets: %d", purchased);
        System.out.println();
        percent = (double) purchased / total_seats;
        System.out.printf("Percentage: %.2f%%%n", percent*100);
        System.out.printf("Current income: $%d%n",income);
        System.out.printf("Total income: $%d%n", total_income);
        System.out.println();
    }
    
    static void menu(Scanner scanner) {
        while (true) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            System.out.println();
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                displaySeats();
            } else if (cmd == 2) {
                getRowAndSeat(scanner);
            } else if (cmd == 3) {
                showStats();
            } else if (cmd == 0) {
                break;
            } else {
                //System.out.println("Inavlid command");
            }
        }
    }

}