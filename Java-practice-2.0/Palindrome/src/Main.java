// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

                System.out.print("Enter a number: ");
                Scanner scanner = new Scanner(System.in);
                int number = scanner.nextInt();
                int UnchangedNum = number;
                int reversed = 0;

                while (number != 0) {
                    int digit = number % 10;         // Get the last digit
                    reversed = reversed * 10 + digit; // Append the digit
                    number /= 10;                    // Remove the last digit
                }

                System.out.println("Reversed number: " + reversed);
                if(UnchangedNum == reversed)
                    System.out.println("The number is palindrome");
                else System.out.println("The number is not palindrome");
                scanner.close();
            }
        }


