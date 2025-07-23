// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int n,i;
        System.out.println("Enter a number :");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int j = 2; j<=n;j++) {
            boolean isPrime = true;
            for (i = 2; i <= Math.sqrt(j); i++) {
                if (j % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime)
                System.out.println(j);
        }

    }
}