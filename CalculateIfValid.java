import java.util.*; 

public class CalculateIfValid {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); 
        String input = sc.nextLine(); 
        
        int result = 0; 

        for (char c : input.toCharArray()) {
            if (c == '*') {
                result++; 
            } else if (c == '#') {
                result--; 
            }
        }

        System.out.println("Result is : " + result); 
    }    
}
