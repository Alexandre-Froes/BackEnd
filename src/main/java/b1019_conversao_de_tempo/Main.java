package b1019_conversao_de_tempo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int segundos = s.nextInt();
        int seg = segundos;

        int hora = (seg / 3600);
        seg -= (hora * 3600);
        int min = (seg / 60);
        seg -= (min * 60);
        int segund = seg;

        System.out.println(hora + ":" + min + ":" + segund);
    }
}
