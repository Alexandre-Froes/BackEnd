package b1078_tabuada;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    
	    int n, x;
	    
	    n = s.nextInt();
	    
	    for(int i = 1; i <= 10; i++) {
	        int fim = i * n; 
            System.out.printf("%d x %d = %d\n", i, n, fim);
	        
	    }
	}
}
