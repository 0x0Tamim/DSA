import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter array elements: ");
        for(int i = 0;i<n;i++){
            arr[i]= sc.nextInt();
        }
        InsertionSort(arr);
        System.out.println("Sorted array: "+Arrays.toString(arr));

    }

    static void InsertionSort(int[]arr){
        for(int i = 0;i< arr.length-1;i++){
            for(int j = i+1;j>0;j--){
                if(arr[j]<arr[j-1]){
                    int tmp = arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=tmp;
                }
            }
        }
    }
}
