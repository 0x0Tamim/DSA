// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
public class Main {
    public static final int Prime = 101;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String txt = sc.nextLine();
        System.out.print("Enter pattern to search in the text: ");
        String pattern = sc.nextLine();
        Search(txt,pattern);

    }

    public static double calculateHash(String str){
       double hash = 0;
       for(int i = 0;i<str.length();i++){
           hash += str.charAt(i)*Math.pow(Prime,i);
       }
       return hash;
    }

    public static double updateHash(double prevHash, char oldChar, char newChar, int patternLength){
        double newHash = (prevHash - oldChar)/Prime + newChar*Math.pow(Prime,patternLength - 1);
        return newHash;
    }

    public static void Search(String txt, String ptrn){
        int patternLength = ptrn.length();
        double txtHash = calculateHash(txt.substring(0, patternLength));

        double patternHash = calculateHash(ptrn);

        for(int i = 0;i<=txt.length() - patternLength;i++){
            if(txtHash == patternHash){
                if(txt.substring(i,i+patternLength).equals(ptrn)){
                    System.out.println("Pattern found at index "+i);
                }
            }

            if(i<txt.length()-patternLength){
                txtHash = updateHash(txtHash,txt.charAt(i),txt.charAt(i+patternLength),patternLength);
            }
        }
    }

}