package p03_processando_inteiros;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int i = 0;
		int imp = 0;
		int par = 0;
		double media = 0;
		double soma = 0;
		
        while (soma < n) {
            int num = s.nextInt();
            
            soma += num;
            
            i++;
	     
            if (num % 2 == 0) {
                par++;
                
            } else {
                imp++;
                
            }
            
            media = soma / i;
            
	    }
	    
	    System.out.printf("Pares = %d\n", par);
	    System.out.printf("Impares = %d\n", imp);
	    System.out.printf("Média = %.2f\n", media);
	}
}
