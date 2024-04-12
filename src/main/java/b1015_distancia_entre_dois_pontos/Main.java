package b1015_distancia_entre_dois_pontos;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double x1 = s.nextDouble();
        double y1 = s.nextDouble();

        double x2 = s.nextDouble();
        double y2 = s.nextDouble();

        double total = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        System.out.println(String.format("%.4f" , total));
    }
}


