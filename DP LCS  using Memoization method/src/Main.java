import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int lcsMemo(String a, String b, int i, int j,int[][]dp) {
        if(i==0 || j==0) return 0;
        if(dp[i][j]!= -1)
            return dp[i][j];
        if(a.charAt(i-1)==b.charAt(j-1))
            return dp[i][j] = 1+ lcsMemo(a,b,i-1,j-1,dp);
        else
            return dp[i][j] = Math.max(lcsMemo(a, b, i, j-1,dp),lcsMemo(a, b, i-1, j,dp));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String a = sc.nextLine();

        System.out.print("Enter second string: ");
        String b = sc.nextLine();
        
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int[]row:dp
             ) {
            Arrays.fill(row,-1);
            
        }

        System.out.println("Size of LCS = "+lcsMemo(a,b,a.length(),b.length(),dp));


    }
}
