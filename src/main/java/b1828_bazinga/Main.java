package b1828_bazinga;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		String r, s;
		
		for(int i = 1; i <= t; i++) {
		    
		    s = sc.next();
		    r = sc.next();
		    
            if ( 
            (s.equals("tesoura") && r.equals("papel") ) ||
            (s.equals("papel") && r.equals("pedra") ) ||
            (s.equals("pedra") && r.equals("lagarto") ) ||
            (s.equals("lagarto") && r.equals("Spock") ) ||
            (s.equals("Spock") && r.equals("tesoura") ) ||
            (s.equals("tesoura") && r.equals("lagarto") ) ||
            (s.equals("lagarto") && r.equals("papel") ) ||
            (s.equals("papel") && r.equals("Spock") ) ||
            (s.equals("Spock") && r.equals("pedra") ) ||
            (s.equals("pedra") && r.equals("tesoura") ) ) {
                
                System.out.printf("Caso #%d: Bazinga!\n" , i);
                
            } else if (s.equals(r)) {
                System.out.printf("Caso #%d: De novo!\n", i);
                
            } else {
                System.out.printf("Caso #%d: Raj trapaceou!\n", i);
            }
		}
	}
}