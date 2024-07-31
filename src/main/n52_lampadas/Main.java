package n52_lampadas;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int t = s.nextInt();
		int num;
		int a = 0, b = 0;
		
		for(int i = 0; i < t; i++) {
		    num = s.nextInt();
		    
		    if (num == 1) {
		        a ^= 1;
		        
		    } else {
		        a ^= 1;
		        b ^= 1;
		        
		    }
    }
		System.out.println(a);
		System.out.println(b);
	}
}
