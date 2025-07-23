// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int x,y,z;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the values of x,y and z gradually: ");
        x = scanner.nextInt();
        y = scanner.nextInt();
        z = scanner.nextInt();

        if(x>y && x>z) System.out.println("x is greater than y and z");
        else if(y>z && y>x) System.out.println("y is greater than x and z");
        else if(x==y && y==z) System.out.println("x,y,z are equal");
        else System.out.println("z is greater than x and y");
        }
    }
