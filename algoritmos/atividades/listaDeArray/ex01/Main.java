package ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Integer soma = 0;
        List<Integer> numeros = new ArrayList<>();
        
        Integer n = 0;

        do {
            soma += n;
            numeros.add(n);
            
            n = ler.nextInt();

        } while(n > 0);

        System.out.println(numeros.size() - 1);
        System.out.println("soma = " + soma);

        ler.close();
    }
}
