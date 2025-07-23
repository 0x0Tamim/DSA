import java.util.*;
public class Main {
    public static void main(String[] args) {
       int x,y,z;
       Scanner scanner = new Scanner(System.in);
       x = scanner.nextInt();
       y = scanner.nextInt();
        System.out.println("Now x = "+x+" and y = "+y);

        z = x;
        x = y;
        y = z;

        System.out.println("After swapping x = "+x+" and y = "+y);
    }
}