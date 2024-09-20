package b1051_imposto_de_renda;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double salario, total;
        double valor01, valor02;

        salario = s.nextDouble();

        valor01 = 1000 * 0.08;
        valor02 = 1500 * 0.18;

        if (salario <= 2000) {
            System.out.println("Isento");

        } else if (salario > 2000 && salario <= 3000) {
            salario -= 2000;
            total = salario * 0.08;
            System.out.println("R$ " + String.format("%.2f" , total));

        } else if (salario > 3000 && salario <= 4500) {
            salario -= 3000;
            total = (salario * 0.18) + valor01;
            System.out.println("R$ " + String.format("%.2f" , total));

        } else {
            salario -= 4500;
            total = (salario * 0.28) + valor01 + valor02;
            System.out.println("R$ " + String.format("%.2f" , total));
        }
    }
}
