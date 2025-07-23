import java.util.ArrayList;
import java.util.Scanner;

public class ThirdValuePrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<>();

        while (true) {
     System.out.println("Enter a string:");
     String input = scanner.nextLine();
     if(input.isEmpty()){
        break;
     }       
     strings.add(input);
            
        }
        System.out.println(strings.get(3));
    }
}
