package ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();
        int soma = 0;
        int maior = 0;
        List<Integer> numeros = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            numeros.add(ler.nextInt());
            soma += numeros.get(i);

            if (numeros.get(i) > maior) {
                maior = numeros.get(i);    
            }
        }
        // quebra de linha
        System.out.println();

        // tds os numeros da lista
        for(int x : numeros) {
            System.out.printf("%d ", x);
        }

        // soma da lista
        System.out.println("\n" + soma);

        // maior da lista
        System.out.println(maior);

        // removendo os numeros impares
        numeros.removeIf(x -> x % 2 != 0);
        // imprimindo a lista só com par
        for(int x : numeros) {
            System.out.printf("%d ", x);
        }

        ler.close();
    }
}