import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int lcsTab(String a, String b) {
      int m = a.length();
      int n = b.length();
      int[][]dp = new int[m+1][n+1];

      for(int i = 1;i<=m;i++){
          for(int j = 1;j<=n;j++){
              if(a.charAt(i-1) == b.charAt(j-1)){
                  dp[i][j] = 1+dp[i-1][j-1];
              }
              else
                 dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
          }
      }
      return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String a = sc.nextLine();

        System.out.print("Enter second string: ");
        String b = sc.nextLine();

        System.out.println("Size of LCS = "+lcsTab(a,b));


    }
}
