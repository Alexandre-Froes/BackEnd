package b1103_alarme_despertador;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
        int hi = s.nextInt();
        int mi = s.nextInt();

        int hf = s.nextInt();
        int mf = s.nextInt();
        
        int total = 0;
		
	    while (hi != 0 || hf != 0 || mi != 0 || mf != 0) {
	        
	    int m1 = hi * 60 + mi;
        int m2 = hf * 60 + mf;  
        
        if (m2 >= m1) {
           total = m2 - m1;
            
        } else {
           total = (24 * 60 - m1) + m2; 
            
        }
        
        System.out.println(total);
        
        hi = s.nextInt();
        mi = s.nextInt();
        
        hf = s.nextInt();
        mf = s.nextInt();
        
		}
	}
}
