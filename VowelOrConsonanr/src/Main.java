// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       char ch;
       Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Character: ");
        ch = scanner.next().charAt(0);

        if(ch == 'a'|| ch == 'e'|| ch == 'i'|| ch == 'o'|| ch == 'u'|| ch == 'A'|| ch == 'E'|| ch == 'I'|| ch == 'O'|| ch == 'U' )
            System.out.println("This character is a vowel");
        else System.out.println("This character is a consonant");

    }
}