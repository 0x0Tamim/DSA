// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        ArrayList<String> item = new ArrayList<>();
        System.out.println("Enter items to add, if done type (done) ");

        while(true) {
            System.out.print("Add: ");
            String a = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            if (a.isEmpty()) {
                System.out.println("Item cannot be empty.");

            }
            else if(a.equals("done"))
                break;
            else if (item.contains(a)) {
                System.out.println("Item already exist.");
            } else
                item.add(a);

        }
        System.out.println("Grocery List: "+item);
    }
}