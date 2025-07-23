import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // Clear the newline left after nextInt()

        String[] student = new String[n];
        boolean[] atndnce = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student " + (i + 1) + " name: ");
            student[i] = sc.nextLine(); // Now works correctly

            System.out.print("Is he/she present? (true or false): ");
            atndnce[i] = sc.nextBoolean();
            sc.nextLine(); // Clear the newline after nextBoolean()
        }

        System.out.println("Students: " + Arrays.toString(student));
        System.out.println("Their attendance list: " + Arrays.toString(atndnce));



       for (int i = 0; i < n; i++) {
             String a = new String();
            if(atndnce[i]== true) {
                a = "present";
            }
            else a ="absent";
            System.out.println(student[i]+" is "+a);

        }
    }
}
