import java.util.*; 

public class countPrior {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the size of the array."); 

        int n = sc.nextInt(); 
        int[] arr = new int[n]; 

        int count = 1; 
        int greaterTillNow = 0; 

        System.out.println("Enter the elements of the array.");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); 

            if (i > 0 && arr[i] > greaterTillNow) count++; 
            greaterTillNow = Math.max(greaterTillNow, arr[i]); 
        }

        System.out.println("Output is : " + count); 


    }
}
