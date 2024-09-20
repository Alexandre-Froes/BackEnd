package erw02_validar_ano_de_nascimento;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int ano = 0;
		
	    do {
	        
	        ano = s.nextInt();
		    
		    if (ano >= 1900 && ano <= 2023) {
		        System.out.println(ano);
		        break;
		        
		    } else {
		        System.out.println("Ano invalido");
		    }
		    
		} while (ano >= 0 && ano <= 1000000);
	}
}
