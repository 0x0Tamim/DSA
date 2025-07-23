// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int a;
        System.out.println("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
         a = scanner.nextInt();
        System.out.println("Reverse of the number is: ");
        int reversed = 0;

        while(a !=0 ){
            int digit = a%10;
            reversed = (reversed*10)+digit;
            a /= 10;
        }

        System.out.println(reversed);
    }
}