package BackEnd.algoritmos.beecrowd.b5426_contar_ocorrencias_de_paralvras;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int cont = 0;

        String frase = ler.nextLine();
        String[] palavras = frase.split(" ");

        String palavra = ler.nextLine();

        for(int i = 0; i < palavras.length; i++) {
            if(palavras[i].contains(palavra)) {
                cont++;
            }
        }

        System.out.println(cont);

        ler.close();
    }
}
