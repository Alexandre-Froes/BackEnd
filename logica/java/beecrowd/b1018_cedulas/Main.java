package b1018_cedulas;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int atual = s.nextInt();
        int valor = atual;

        int nota100 = (valor / 100);
        valor -= (nota100 * 100);
        int nota50 = (valor / 50);
        valor -= (nota50 * 50);
        int nota20 = (valor / 20);
        valor -= (nota20 * 20);
        int nota10 = (valor / 10);
        valor -= (nota10 * 10);
        int nota05 = (valor / 5);
        valor -= (nota05 * 5);
        int nota2 = (valor / 2);
        valor -= (nota2 * 2);
        int nota1 = (valor / 1);
        valor -= (nota1 * 1);

        System.out.println(atual);
        System.out.println(nota100 + " nota(s) de R$ 100,00");
        System.out.println(nota50 + " nota(s) de R$ 50,00");
        System.out.println(nota20 + " nota(s) de R$ 20,00");
        System.out.println(nota10 + " nota(s) de R$ 10,00");
        System.out.println(nota05 + " nota(s) de R$ 5,00");
        System.out.println(nota2 + " nota(s) de R$ 2,00");
        System.out.println(nota1 + " nota(s) de R$ 1,00");
    }
}
