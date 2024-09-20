package b1035_teste_de_selecao;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int A, B, C, D;

        A = s.nextInt();
        B = s.nextInt();
        C = s.nextInt();
        D = s.nextInt();

        if ( B > C && D > A && (C + D) > (A + B) && (A % 2) == 0 && D > 0 && C > 0 ) {

            System.out.println("Valores aceitos");

        } else {

            System.out.println("Valores nao aceitos");
        }
    }
}
