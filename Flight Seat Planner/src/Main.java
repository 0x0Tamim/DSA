import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] seat = new String[5][4];

        // Generate seats randomly
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                seat[i][j] = Math.random() < 0.5 ? "O" : "X";
            }
        }

        // Show initial grid
        System.out.println("Current Grid:");
        printGrid(seat);

        int x, y;

        while (true) {
            System.out.print("Book a seat by seat index (row[1-5] column[1-4]): ");
            x = sc.nextInt();
            y = sc.nextInt();

            if (x < 1 || x > 5 || y < 1 || y > 4) {
                System.out.println("Please input a valid index.");
            } else if (seat[x - 1][y - 1].equals("X")) {
                System.out.println("Seat already taken.");
            } else {
                seat[x - 1][y - 1] = "X";
                System.out.println("✅ Seat booked successfully.");
                break;
            }
        }

        System.out.println("Updated Grid:");
        printGrid(seat);
    }

    // Grid print method
    public static void printGrid(String[][] seat) {
        System.out.print("   ");
        for (int col = 1; col <= seat[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        for (int i = 0; i < seat.length; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < seat[i].length; j++) {
                System.out.print(seat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
