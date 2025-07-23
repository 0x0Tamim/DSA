import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.print("Enter number size of array: ");
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.print("Enter array elements to sort the array: ");
        int[] arrr = new int[n];
        for(int i = 0;i<n;i++){
            arrr[i] = sc.nextInt();
        }

        System.out.println("Sorted array is: "+Arrays.toString(mergeSort(arrr)));

    }

        static int[] mergeSort(int[] arr){
        if(arr.length==1){
            return arr;
        }
        int mid = arr.length / 2;

        int[] left = mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

        return merge(left,right);
        }

        static int[] merge(int[] first, int[] second){

        int i = 0;
        int j = 0;
        int k = 0;
        int[] mix = new int[first.length + second.length];
        while (i<first.length && j< second.length){


            if(first[i]<=second[j]){
                mix[k] = first[i];
                i++;
                k++;
            }
            else {
                mix[k] = second[j];
                j++;
                k++;
            }



        }
            while (i<first.length){
                mix[k]=first[i];
                k++;
                i++;
            }
            while (j<second.length){
                mix[k]=second[j];
                k++;
                j++;
            }
            return mix;
        }
}