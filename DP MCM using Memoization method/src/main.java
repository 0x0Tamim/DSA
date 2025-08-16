import java.util.*;

public class main {
    static int[][] dp;

    // Matrix Chain Multiplication using recursion + memoization
    static int mcm(int[] arr, int i, int j) {
        if (i == j) return 0;  // Only one matrix → no multiplication cost
        if (dp[i][j] != -1) return dp[i][j];

        int minCost = Integer.MAX_VALUE;

        // Try every possible split
        for (int k = i; k < j; k++) {
            int cost = mcm(arr, i, k)
                    + mcm(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j];

            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    public static void main(String[] args) {
        // Example input
        int[] arr = {10, 20, 30, 40}; // 3 matrices: 10x20, 20x30, 30x40
        int n = arr.length;

        dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println("Minimum cost: " + mcm(arr, 1, n - 1));
    }
}
