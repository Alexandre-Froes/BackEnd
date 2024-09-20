package b1070_seis_numeros_impares;

import java.util.*;

public class Main {
 
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        int num = s.nextInt();
        
        if(num % 2 == 0) {
            
            num++;
            
            for (int i = 0; i < 6 ; i++) {
                System.out.println(num);
                num = num+=2;
            }
                
        } else {
        
            for (int i = 0; i < 6 ; i++) {
                System.out.println(num);
                num = num+=2;
            }
        }
    }
}