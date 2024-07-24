package b1073_quadrado_de_pares;

import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int x = s.nextInt();
		double fim;
        
		for(double i = 1; i <= x ; i++) {
		    
	        if (i % 2 == 0) {
	            
    		    fim = i * i;
    		    System.out.printf("%.0f^2 = %.0f\n" , i , fim);
		    }
		}
	}
}