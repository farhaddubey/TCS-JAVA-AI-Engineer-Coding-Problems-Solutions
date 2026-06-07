import java.util.*;

public class CruiseParty {
    public static void main(String[] args) {

        System.out.println("Enter the inputs"); 
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt(); 

        int[] incoming = new int[n]; 
        int[] outgoing = new int[n]; 

        int current = 0; 
        int maxOverTime = 0; 

        System.out.println("Insert incoming - outgoing");
        for (int i = 0; i < n; i++) {
            incoming[i] = sc.nextInt(); 
            current += incoming[i]; 
            outgoing[i] = sc.nextInt();
            current -= outgoing[i]; 
            maxOverTime = Math.max(maxOverTime, current); 
        }

        System.out.println("Max over any time is : " + maxOverTime);

    }    
}
