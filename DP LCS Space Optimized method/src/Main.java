import java.util.Scanner;

public class Main {

    // Space-optimized LCS (only stores two rows)
    static int lcsOpt(String a, String b) {
        int n = a.length(), m = b.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];
                else
                    curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr.clone(); // copy current row to previous
        }
        return prev[m];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String a = sc.nextLine();

        System.out.print("Enter second string: ");
        String b = sc.nextLine();

        int length = lcsOpt(a, b);

        System.out.println("Length of LCS = " + length);

        sc.close();
    }
}
