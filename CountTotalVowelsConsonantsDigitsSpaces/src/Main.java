// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.print("Enter a string: ");
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.nextLine();
        int vowels=0,consonants=0,digits=0,spaces = 0;
        input = input.toLowerCase();

        for(char ch: input.toCharArray()){
            if(Character.isDigit(ch)){
                digits++;
            }
            else if(Character.isWhitespace(ch)){
                spaces++;

            }
            else if(ch>='a' && ch<= 'z'){
                if("aeiou".indexOf(ch) != -1){
                    vowels++;
                }
                else consonants++;
            }
        }

        System.out.println("Total Digit: "+digits);
        System.out.println("Total Vowel: "+vowels);
        System.out.println("Total Consonant: "+consonants);
        System.out.println("Total Spaces: "+spaces);

    }
}