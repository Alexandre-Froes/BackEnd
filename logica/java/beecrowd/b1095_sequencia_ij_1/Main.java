package b1095_sequencia_ij_1;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int i = 1, j = 60;
		
		for(int k = 0; k < 13; k++) {
		    
		   System.out.printf("I=%d J=%d\n", i, j);
		   
		   i += 3;
		   j -= 5;
		}
	}
}