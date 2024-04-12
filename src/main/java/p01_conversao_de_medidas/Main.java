package p01_conversao_de_medidas;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        int m, cm, mm;
        double km, milhas;

        m = s.nextInt();

        cm = m * 100;
        mm = m * 1000;
        km = m / 1000.0;
        milhas = m / 1609.0;

        System.out.printf("%d metros%n" , m);
        System.out.printf("%d centímetros%n" , cm);
        System.out.printf("%d milímetros%n" , mm);
        System.out.printf("%.4f quilômetros%n" , km);
        System.out.printf("%.4f milhas%n" , milhas);
    }
}