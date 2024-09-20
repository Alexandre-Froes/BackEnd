package b1160_crescimento_populacional;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int t = 0;

		double pa = 0;
        double pb = 0;

        double ga = 0; 
        double gb = 0;
        
        int i, j;
		
		t = s.nextInt();
		
		for (i = 1; i <= t; i++) {
		    
		    pa = s.nextDouble();
		    pb = s.nextDouble();
		    
		    ga = s.nextDouble();
		    gb = s.nextDouble();
		    
		    for (j = 0; pa <= pb && j <= 100; j++) {
		        pa = pa + (int) (pa * ga/100);
		        pb = pb + (int) (pb * gb/100);
		        
		    }
		    
            if (j <= 100) {
                System.out.printf("%d anos.\n", j);
                
            } else {
                System.out.println("Mais de 1 seculo.");
                
            } 
		}
	}
}

