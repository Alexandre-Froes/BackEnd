package BackEnd.algoritmos.beecrowd.b1170_sequencia_de_numeros_e_soma;

import java.util.Scanner;

public class Main {
    static int somarNumeros(int n, int m, int soma) {
        if (n > m) {
            return soma;
        } else {
            System.out.printf("%d ", n);
            return somarNumeros(n + 1, m, soma += n);
        }
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int m = ler.nextInt();
        int t = 0;
        int soma = 0;

		while ( m > 0 && n > 0 ) {
            if (n > m) {
                t = n;
                n = m;
                m = t;
            }
            
            soma = somarNumeros(n, m, soma);

            System.out.printf("Sum=%d\n", soma);
            
            n = ler.nextInt();
            m = ler.nextInt();

            soma = 0;
		}

        ler.close();
    }
}