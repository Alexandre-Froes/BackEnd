package erw03_funcionarios;

import java.util.*;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		double soma = 0;
		int empoderadas = 0;
		int i = 0;
		char n = 's';
		
	    int maior = Integer.MIN_VALUE;
        int menor = Integer.MAX_VALUE;
		
		do {
		    
		    //leitura
    		int idade  = s.nextInt();
    		char sexo = s.next().charAt(0);
    		double sal = s.nextDouble();
    		n = s.next().charAt(0);
		    
		    //media
		    i++;
		    soma += sal;
		    
		    //maior e menor idade
		    if (idade < menor) {
		        menor = idade;
		    }  
		    if (idade > maior) {
		        maior = idade;
		    }
		    
		    //empoderadas
		    if (sexo == 'f' && sal >= 3000) {
		        empoderadas++;
		    }
		    
		} while (n == 's');
		
		double media = soma / i;
		
		System.out.printf("Media salarios = R$ %.2f\n", media);
		System.out.printf("Menor e maior idade = %d e %d\n", menor, maior);
		System.out.printf("Salarios de mulheres acima de R$ 3000.00 = %d\n", empoderadas);
	}
}

