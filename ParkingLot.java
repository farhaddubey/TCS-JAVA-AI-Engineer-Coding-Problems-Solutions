import java.util.*; 


public class ParkingLot {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the inputs");
        int r = sc.nextInt();
        int c = sc.nextInt(); 

        int[][] matrix = new int[r + 1][c + 1]; 

        int globalCount = 0; 
        int resultRow = -1; 
        for (int i = 0; i < r; i++) {
            int count = 0; 
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt(); 
                if (matrix[i][j] == 1) {
                    count++; 
                }
            }
            if (globalCount < count) {
                globalCount = count; 
                resultRow = i; 
            }
        }

        System.out.println("Result is : " + resultRow + 1);
    }
}
