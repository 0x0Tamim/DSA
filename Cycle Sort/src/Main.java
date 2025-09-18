import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements(N, where elements range is 1 to N): ");
        int n = sc.nextInt();
        int[]arr = new int[n];
        System.out.print("Enter elements one by one: ");

        for(int i = 0;i<n;i++){
            arr[i]= sc.nextInt();
        }

        CycleSort(arr);
        System.out.println("Sorted array: "+Arrays.toString(arr));
    }

    static void CycleSort(int[]arr){
        int i = 0;
        while(i< arr.length){
            if(arr[i]!=i+1){
                int tmp = arr[arr[i]-1];
                arr[arr[i]-1]=arr[i];
                arr[i]=tmp;

            }
            else i++;
        }
    }
}
