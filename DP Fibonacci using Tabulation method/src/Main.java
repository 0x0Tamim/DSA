import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to get its Fibonacci: ");
        int n = sc.nextInt();
        int[] dp = new int[n + 1];

        System.out.println("Fib(" + n + ") = " + fib(n, dp));
    }

    static int fib(int n, int[] dp) {

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }
}
