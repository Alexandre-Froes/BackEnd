package erw01_sistema_de_votacao;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int x = s.nextInt();
		
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int n = 0;
		int b = 0;
		
		while (x > 0 && x <= 5) {
		    switch (x) {
		        case 1:
		            c1++;
		            break;
		            
		        case 2:
		            c2++;
		            break;
		        
		        case 3:
		            c3++;
		            break;
		            
		        case 4:
		            n++;
		            break;
		            
		        case 5:
		            b++;
		            break;
		    }
		    
		    x = s.nextInt();
		}
		
		System.out.printf("Candidato 1 : %d voto(s)\n", c1);
		System.out.printf("Candidato 2 : %d voto(s)\n", c2);
		System.out.printf("Candidato 3 : %d voto(s)\n", c3);
		System.out.printf("Nulos : %d voto(s)\n", n);
		System.out.printf("Brancos : %d voto(s)\n", b);
	}
}