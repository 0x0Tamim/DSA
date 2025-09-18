import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: size of array (N elements, values must be from 1 to N, unique, no duplicates)
        System.out.print("Enter number of elements (N, where elements range is 1 to N): ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Taking array input
        System.out.print("Enter elements one by one: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Call Cycle Sort
        CycleSort(arr);

        // Print sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    static void CycleSort(int[] arr) {
        int i = 0;

        // Loop until we check all elements
        while (i < arr.length) {

            // Correct position of current element should be (value - 1)
            // Example: value = 3 → correct index = 2
            if (arr[i] != i + 1) {
                // Swap current element with the element at its correct position
                int correctIndex = arr[i] - 1;

                // Swap arr[i] <-> arr[correctIndex]
                int tmp = arr[correctIndex];
                arr[correctIndex] = arr[i];
                arr[i] = tmp;
            }
            else {
                // If element is already at correct position, move to next index
                i++;
            }
        }
    }
}
