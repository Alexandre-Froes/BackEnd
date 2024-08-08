package p02_contar_numeros_negativos;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int neg = 0;
		int num = 0;
		
		for(int i = 1; i <= n; i++) {
		    
		    num = s.nextInt();
		    
		   if (num < 0) {
		       neg++;
		   }
		}
		
		System.out.println(neg);
	}
}

