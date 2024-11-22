package BackEnd.algoritmos.beecrowd.b5428_buscando_frase_dentro_outra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String s1 = ler.nextLine();
        String s2 = ler.nextLine();

        if(s1.indexOf(s2) == -1) {
            System.out.println(s1);
        } else {
            String corte = s1.substring(s1.indexOf(s2));
            System.out.println(corte);
        }

        ler.close();
    }
}