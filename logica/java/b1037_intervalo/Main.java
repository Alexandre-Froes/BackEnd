package b1037_intervalo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double num = s.nextDouble();

        if (num >= 0 && num <= 25) {
            System.out.println("Intervalo [0,25]");
        } else if (num > 25 && num <= 50) {
            System.out.println("Intervalo (25,50]");
        } else if (num > 50 && num <= 75) {
            System.out.println("Intervalo (50,75]");
        } else if (num > 25 && num <= 100) {
            System.out.println("Intervalo (75,100]");
        } else
            System.out.println("Fora de intervalo");
    }
}
