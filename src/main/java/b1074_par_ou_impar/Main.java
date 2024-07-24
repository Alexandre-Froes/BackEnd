package b1074_par_ou_impar;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    
	    int n, x;
	    
	    n = s.nextInt();
	    
	    for(int i = 0; i < n; i++){
        
        x = s.nextInt();
        
        if (x == 0) {
            System.out.println("NULL");
            
        } else if (x < 0 && x % 2 == 0) {
            System.out.println("EVEN NEGATIVE");
            
            } else if (x < 0 && x % 2 != 0) {
                System.out.println("ODD NEGATIVE");
                
            } else if (x > 0 && x % 2 == 0) {
                System.out.println("EVEN POSITIVE");
            
            } else if (x > 0 && x % 2 != 0) {
                System.out.println("ODD POSITIVE");
                
            }
	    }
	}
}

