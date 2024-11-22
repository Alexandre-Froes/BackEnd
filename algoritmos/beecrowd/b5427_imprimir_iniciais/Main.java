package BackEnd.algoritmos.beecrowd.b5427_imprimir_iniciais;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String t = ler.nextLine();

        String[] palavras = t.split(" ");

        for(int i = 0; i < palavras.length; i++) {
            if (palavras[i].length() > 3) {
                System.out.println(palavras[i].substring(0, 3));
            }
        }


        ler.close();
    }
}
