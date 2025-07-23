import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element: ");
            arr[i] = sc.nextInt();
        }

        // Create copies so that original arr is not modified repeatedly
        int[] ascArr = Arrays.copyOf(arr, n);
        int[] descArr = Arrays.copyOf(arr, n);

        // Sorting
        AscendingSort(ascArr);
        DescendingSort(descArr);

        System.out.print("Ascending sorting: ");
        System.out.println(Arrays.toString(ascArr));

        System.out.print("Descending sorting: ");
        System.out.println(Arrays.toString(descArr));

        // Search
        System.out.print("Enter target to find its index: ");
        int target = sc.nextInt();

        System.out.println("Target index in ascending array: " + binarySearch(ascArr, target));
        System.out.println("Target index in descending array: " + binarySearch(descArr, target));
    }

    public static void AscendingSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void DescendingSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        boolean isAsc = arr[start] < arr[end];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (isAsc) {
                if (target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target < arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1; // not found
    }
}
