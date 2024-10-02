package BackEnd.algoritmos.beecrowd.b1070_seis_numeros_impares;

import java.util.Scanner;

public class Main {

    static int cont;

    static int impares(int x) {
        if (cont == 6) {
            return x;
        }

        if(x % 2 == 0) {
            return impares(x + 1);

        } else {
            cont++;
            System.out.println(x);
            return impares(x + 2);
        }
    }
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int x = ler.nextInt();

        impares(x);

        ler.close();
    }
}
