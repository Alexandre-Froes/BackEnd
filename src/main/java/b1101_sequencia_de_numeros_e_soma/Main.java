package b1101_sequencia_de_numeros_e_soma;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int m = s.nextInt();
		int n = s.nextInt();
		
		int t = 0;
		int soma = 0;
		
		while ( m > 0 && n > 0 ) {
		    
            if (n > m) {
                t = n;
                n = m;
                m = t;
            }
            
            for (int i = 0; n <= m; i++) {
                soma += n;
                
                System.out.printf("%d ", n);
                
                n++;
            }
            
            System.out.printf("Sum=%d\n", soma);
            
            m = s.nextInt();
            n = s.nextInt();
            
            soma = 0;
		}
	}
}

