import java.util.Scanner;

public class Main {

    static int lcsRec(String a, String b, int i, int j) {
        if (i == 0 || j == 0) return 0; // Base case
        if (a.charAt(i - 1) == b.charAt(j - 1))
            return 1 + lcsRec(a, b, i - 1, j - 1);
        else
            return Math.max(lcsRec(a, b, i - 1, j), lcsRec(a, b, i, j - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String a = sc.nextLine();

        System.out.print("Enter second string: ");
        String b = sc.nextLine();

        int result = lcsRec(a, b, a.length(), b.length());
        System.out.println("Length of LCS: " + result);

        sc.close();
    }
}
