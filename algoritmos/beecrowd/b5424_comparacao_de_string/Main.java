package BackEnd.algoritmos.beecrowd.b5424_comparacao_de_string;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String s = ler.nextLine();

        for(int i = 0; i < s.length(); i++) {
            System.out.printf("%d - %s%n", i, s.charAt(i));
        }

        ler.close();
    }
}
