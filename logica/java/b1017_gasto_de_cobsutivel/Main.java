package b1017_gasto_de_cobsutivel;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double horas = s.nextDouble();
        double km = s.nextDouble();
        double total = horas * km;
        double litros = total / 12;

        System.out.printf("%.3f\n" , litros);
    }
}