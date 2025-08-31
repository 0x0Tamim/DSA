import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to get its Fibonacci: ");
        int n = sc.nextInt();

        System.out.println("Fib(" + n + ") = " + fib(n));
    }

    static int fib(int n) {
        if(n==0) return 0;
        if (n == 1)
            return 1;
        int prev1 = 1;
        int prev2 = 0;
        int curr = 0;
        for (int i = 2;i<=n;i++){
            curr = prev1+prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }
}
