package b1046_tempo_de_jogo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int A, B, pmaior, smaior;

        A = s.nextInt();
        B = s.nextInt();

        pmaior = B - A;
        smaior = 24 + B - A;

        if (A < B) {

            System.out.printf("O JOGO DUROU %d HORA(S)%n" , pmaior);

        } else {

            System.out.printf("O JOGO DUROU %d HORA(S)%n" , smaior);
        }
    }
}
