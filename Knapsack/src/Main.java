import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        System.out.print("Enter knapsack capacity: ");
        double capacity = sc.nextDouble();

        // Each item: [value, weight]
        double[][] items = new double[n][2];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value of item " + (i + 1) + ": ");
            items[i][0] = sc.nextDouble();
            System.out.print("Enter weight of item " + (i + 1) + ": ");
            items[i][1] = sc.nextDouble();
        }

        Arrays.sort(items, (a, b) -> Double.compare(b[0]/b[1], a[0]/a[1]));

        double totalValue = 0;


        for (int i = 0; i < n; i++) {
            if (capacity >= items[i][1]) {
                // take whole item
                totalValue += items[i][0];
                capacity -= items[i][1];
            } else {

                totalValue += items[i][0] * (capacity / items[i][1]);
                break;
            }
        }

        System.out.printf("Maximum value in knapsack = %.2f\n", totalValue);
    }
}
