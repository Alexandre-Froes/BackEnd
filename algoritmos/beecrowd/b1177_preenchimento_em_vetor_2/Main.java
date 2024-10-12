package b1177_preenchimento_em_vetor_2;

import java.util.Scanner;

public class Main {
    static void preencherVetor(int[] vetor, int t, int i, int n) {
        if (i >= n) {
            return;
        }
        vetor[i] = i % t;
        preencherVetor(vetor, t, i + 1, n);
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n  = 10;
        int[] vetor = new int[n];
        int t = ler.nextInt();

        preencherVetor(vetor, t, 0, n);

        for(int i = 0; i < n; i++) {
            System.out.printf("N[%d] = %d%n", i, vetor[i]);
        }

        ler.close();
    }
}
