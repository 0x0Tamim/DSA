import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter number size of array: ");
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.print("Enter array elements to sort the array: ");
        int[] arrr = new int[n];
        for (int i = 0; i < n; i++) {
            arrr[i] = sc.nextInt();
        }

        arrr = mergeSort(arrr);
        int count = 0;
        for (int i = 1; i < arrr.length; i++) {
            if (arrr[i] == arrr[i - 1]) {
                count++;
                while (i + 1 < arrr.length && arrr[i] == arrr[i + 1]) {
                    i++;
                }
            }
        }
        System.out.println("Output: "+count);
    }

    static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    static int[] merge(int[] first, int[] second) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] mix = new int[first.length + second.length];
        while (i < first.length && j < second.length) {
            if (first[i] <= second[j]) {
                mix[k] = first[i];
                i++;
                k++;
            } else {
                mix[k] = second[j];
                j++;
                k++;
            }
        }
        while (i < first.length) {
            mix[k] = first[i];
            k++;
            i++;
        }
        while (j < second.length) {
            mix[k] = second[j];
            k++;
            j++;
        }
        return mix;
    }
}