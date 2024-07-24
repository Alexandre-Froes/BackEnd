package b1074_2_par_ou_impar;

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
        } else {
            if (x % 2 == 0) System.out.printf("EVEN ");
            if (x % 2 != 0) System.out.printf("ODD ");
            if (x < 0) System.out.printf("NEGATIVE\n");
            if (x > 0) System.out.printf("POSITIVE\n");
            }
	    }
	}
}

