import java.util.*;

public class main {
    static int[][] dp;

    // Matrix Chain Multiplication using recursion + memoization
    static int mcm(int[] arr, int i, int j) {
        if(i==j) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        dp[i][j] = Integer.MAX_VALUE;
        for(int k = i;k<j;k++){
            int steps = mcm(arr,i,k)+mcm(arr,k+1,j)+(arr[i-1] * arr[k]*arr[j]);
            dp[i][j] = Math.min(dp[i][j], steps);

        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        // Example input
        int[] arr = {10, 20, 30, 40}; // 3 matrices: 10x20, 20x30, 30x40
        int n = arr.length;

        dp = new int[n][n];
        for (int[]row:dp
             ) {
            Arrays.fill(row,-1);

        }
        System.out.println("Minimum cost: " + mcm(arr,1, n - 1));
    }
}
