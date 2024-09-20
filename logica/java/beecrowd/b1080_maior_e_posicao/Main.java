package b1080_maior_e_posicao;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int tempi = 0, tempx = 0;
        int i = 0;
        
        for(i = 1; i <= 100; i++) {
            int x = s.nextInt();
            
            if (x > tempx){
                tempx = x;
                tempi = i;
            }
        }
        System.out.println(tempx);
        System.out.println(tempi);
	}
}

