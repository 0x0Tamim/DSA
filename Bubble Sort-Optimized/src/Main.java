// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[]arr = new int[n];
        System.out.print("Enter array elements: ");
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Array before sorting: "+ Arrays.toString(arr));
        BubbleSort(arr);
        System.out.print("Array after sorting: "+Arrays.toString(arr));
    }
    public static void BubbleSort(int[]arr){
        int n = arr.length;
        boolean swapped;
        for(int i = 0;i<n-1;i++){
            swapped = false;
            for(int j = 0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swapped = true;
                }

                }
            if(!swapped){
                break;
            }
        }
    }
}