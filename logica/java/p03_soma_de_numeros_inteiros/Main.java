package p03_soma_de_numeros_inteiros;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		double n = s.nextDouble();
		double soma = 0;
		int i = 0;
		
        while (n >= 0.0) {
            soma += n;
            i++;

            n = s.nextDouble();
        }
        
	    System.out.printf("Soma = %.2f\n", soma);
	    System.out.printf("Quantidade = %d\n", i);
	}
}