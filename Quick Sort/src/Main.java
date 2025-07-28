// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] nums = new int[n];

        System.out.println("Enter sorted array elements (can be negative):");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        QuickSort(nums,0,nums.length-1);
        System.out.println("Sorted array: "+Arrays.toString(nums));
    }


    public static void QuickSort(int[] nums, int low, int hi){

        if(low>=hi){
            return;
        }

        int s = low;
        int e = hi;
        int m = low+(hi - low)/2;
        int pivot = nums[m];
        while(s<=e){
            while (nums[s]<pivot){
                s++;
            }

            while (nums[e]>pivot){
                e--;
            }

            if(s<=e){
                int tmp = nums[s];
                nums[s] = nums[e];
                nums[e] = tmp;
                s++;
                e--;
            }
        }

         QuickSort(nums,low,e);

        QuickSort(nums,s,hi);


    }

}