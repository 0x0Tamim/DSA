// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int fact = 1;
        for(int i=1;i<=number;i++){
            fact *= i;
            System.out.println(fact);
        }

        System.out.println("Factorial of the number is :"+ fact);
    }
}