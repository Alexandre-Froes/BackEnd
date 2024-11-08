package BackEnd.algoritmos.beecrowd.b1607_avance_as_letras;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();

        for(int i = 0; i < n; i++){
            String a = ler.next();
            String b = ler.next();
            int cont = 0;

            for(int j = 0; j < a.length(); j++){
                int diferenca = b.charAt(j) - a.charAt(j);
                if (diferenca < 0) {
                    diferenca += 26;
                }
                cont += diferenca;
            }

            System.out.println(cont);
        }

        ler.close();
    }
}