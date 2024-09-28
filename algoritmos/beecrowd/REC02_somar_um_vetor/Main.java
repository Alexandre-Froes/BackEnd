package REC02_somar_um_vetor;

import java.util.Scanner;

public class Main {
    static int soma;

    static int somando(Scanner ler, int[] valores, int i, int n) {
        if (i == n) {
            return soma;
        } else {
            valores[i] = ler.nextInt();
            soma += valores[i];
            return somando(ler, valores, i + 1, n);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int[] valores = new int[n];
        int i = 0;

        int somado = somando(ler, valores, i, n);

        System.out.println(somado);

    }
}
