package BackEnd.algoritmos.beecrowd.b5419_comparacao_de_string;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String a = ler.next();
        String b = ler.next();

        if (a.equals(b)) {
            System.out.println("correto");
        } else {
            System.out.println("incorreto");
        }


        ler.close();
    }
}