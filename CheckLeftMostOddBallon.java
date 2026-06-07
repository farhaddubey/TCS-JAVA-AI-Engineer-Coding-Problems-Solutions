import java.util.*;

public class CheckLeftMostOddBallon {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the no. of ballon colors: ");
        int n = sc.nextInt();
        
        char[] ballonColor = new char[n]; 


        System.out.println("Enter the char of color smaller or caps");
        for (int i = 0; i < n; i++) {
            ballonColor[i] = sc.next().charAt(0); 
            Map<Character, Integer> freq = new HashMap<>(); 
            for (char c : ballonColor) {
                freq.put(c, freq.getOrDefault(c, 0) + 1); 
            }
            boolean found = false; 
            for (char c : ballonColor) {
                if (freq.get(c) % 2 == 1) {
                    System.out.println(c); 
                    found = true; 
                    break; 
                }
            }

        if (!found) {
            System.err.println("All are even");
        }
    }
    }
}