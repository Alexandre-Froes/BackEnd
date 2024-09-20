package b1071_soma_de_impares_consecutivos;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int x, y, t, sum = 0;
		
		x = s.nextInt();
		y = s.nextInt();
		
		if (y > x) {
		    
		    t = y;
		    y = x;
		    x = t;
		}
		
		if(y % 2 == 0) {
		    y++;
		} else {
		    y+=2;
		}
		
		for(int i = y; i < x; i += 2) {
            sum += i;
		}
		
		System.out.println(sum);
	}
}