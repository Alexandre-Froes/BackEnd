package b1075_resto_2;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    
	    int n, x;
	    
	    n = s.nextInt();
	    
	    for(int i = 1; i <= 10000; i++) {
	        if (i % n == 2) { 
	            System.out.println(i); 
	        }
	    }
	}
}

