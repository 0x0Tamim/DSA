// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter a number: ");
        int a;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        System.out.println("Multiplication Table of "+a+" :");

        for(int i = 1;i<=10;i++){
            System.out.println(a+" × "+i+" = "+a*i);
        }
    }
}