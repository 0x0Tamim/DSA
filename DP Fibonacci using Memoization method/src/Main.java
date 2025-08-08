import java.util.*;
public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to ger its fibonacci:");
        int n = sc.nextInt();
        int[]dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println("Fib("+n+") = "+fib(n,dp));

    }

    static int fib(int n,int[]dp){

        if(n<=1) return n;
        if(dp[n] != -1) return dp[n];
        dp[n] = fib(n-1,dp)+fib(n-2,dp);
        return dp[n];

    }

}