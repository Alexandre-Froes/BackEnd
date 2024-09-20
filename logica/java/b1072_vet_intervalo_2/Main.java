package b1072_vet_intervalo_2;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    
	    int n = s.nextInt();
	    
	    int vet[] = new int[n];
	    
	    int in = 0;
	    int out = 0;
	    
	    for (int i = 0; i < n; i++) {
	        vet[i] = s.nextInt();
	    }
	    
	    for (int i = 0; i < n; i++) {
	        
	        if (vet[i] >= 10 && vet[i] <= 20) {
	            in++;
	            
	        } else {
	            out++;
	        }
	    }
	    
	    System.out.printf("%d in\n", in);
	    System.out.printf("%d out\n", out);
	}
}
