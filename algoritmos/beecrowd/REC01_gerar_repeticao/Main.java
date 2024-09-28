package REC01_gerar_repeticao;

import java.util.Scanner;

public class Main {
    static int repeticaoN(int n, int qtd) {
        System.out.printf("%d", n);
        
        if (qtd <= 1) {
            System.out.println();
            return n;
        } else {
            System.out.printf("-");
            return repeticaoN(n, qtd - 1);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int qtd = ler.nextInt();

        repeticaoN(n, qtd);
        
        ler.close();
    }
}