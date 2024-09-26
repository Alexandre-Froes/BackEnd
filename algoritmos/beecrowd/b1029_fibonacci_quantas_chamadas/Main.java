package BackEnd.algoritmos.beecrowd.b1029_fibonacci_quantas_chamadas;

import java.util.Scanner;

public class Main {

    static int cont;
    
    static int fib (int n) {
        cont++;

        if (n == 0 || n == 1) {
            return n;

        } else {
            return fib(n - 1) + fib(n - 2);

        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int num = ler.nextInt();
        int n = 0;

        for(int i = 0; i < num; i++) {
            cont = -1;
            n = ler.nextInt();
            int fib = fib(n);

            System.out.printf("fib(%d) = %d calls = %d%n", n, cont, fib);
        }

        ler.close();
    }
}