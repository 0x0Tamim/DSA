// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        System.out.print("Enter length of array: ");
        int n = sc.nextInt();
        System.out.print("Enter array elements to sort the arry:");
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        selectionSort(arr);
        System.out.println("Sorted array: "+Arrays.toString(arr));

    }
    static void selectionSort(int[]arr){
        for (int i = 0;i<arr.length;i++){
            int last = arr.length-1-i;
            int maxIndex = getMax(arr,last);
            swap(arr,maxIndex,last);
        }
    }
    public static int getMax(int[]arr,int lastIndx){
        int max = 0;
        for(int i = 1;i<= lastIndx;i++){
            if(arr[i]>arr[max]){
                max = i;
            }
        }
        return max;
    }

    public static void swap(int[]arr,int p,int q){
        int tmp = arr[p];
        arr[p] = arr[q];
        arr[q] = tmp;
    }

}