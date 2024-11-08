package BackEnd.algoritmos.beecrowd.b1073_quadrado_de_pares_rescursivo;

import java.util.Scanner;

public class Main {
    static int quadrado(int n, int num) {
        if (num >= n + 1) {
            return n;

        } else if (num % 2 == 0) {
            System.out.printf("%d^2 = %d\n", num, num*num);
            return quadrado(n, num + 2);

        } else {
            return quadrado(n, num + 1);

        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int num = 2;

        quadrado(n, num);
    }
}