package p02_encontrar_o_maior_numero;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		
		int maior = Integer.MIN_VALUE;
		int num = 0;
		
		for(int i = 1; i <= n; i++) {
		    
		    num = s.nextInt();
		    
		   if (num > maior) {
		       maior = num;
		   }
		}
		
		System.out.printf("Maior = %d\n" ,  maior);
	}
}
