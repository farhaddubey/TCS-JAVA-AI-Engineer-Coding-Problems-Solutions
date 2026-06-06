import java.util.*;

public class Automobile {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in); 
        int v = sc.nextInt(); 
        int w = sc.nextInt(); 

        if (w >= 2 && w % 2 == 0 && v < w) {
            int tw = (4*v - w) / 2; 
            int fw = v - tw; 

            System.out.println("Tw : " + tw + "FW : " + fw);
        } else {
            System.out.println("INVALID INPUT.");
        }
    }
}
