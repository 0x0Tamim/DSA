import java.util.*;

public class Main {

    // Iterative Tabulation
    static int mcmTab(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // len = chain length
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40}; // 3 matrices: 10x20, 20x30, 30x40
        System.out.println("Minimum cost (Tabulation): " + mcmTab(arr));
    }
}
