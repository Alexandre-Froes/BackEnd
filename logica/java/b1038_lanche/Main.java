package b1038_lanche;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double m1, m2, m3, m4, m5;

        double pedido = s.nextDouble();
        double quant = s.nextDouble();

        if (pedido == 1) {
            m1 = 4.0 * quant;
            System.out.println("Total: R$ " + String.format("%.2f" , m1));
        } else if (pedido == 2) {
            m2 = 4.50 * quant;
            System.out.println("Total: R$ " + String.format("%.2f" , m2));
        } else if (pedido == 3) {
            m3 = 5.0 * quant;
            System.out.println("Total: R$ " + String.format("%.2f" , m3));
        } else if (pedido == 4) {
            m4 = 2.0 * quant;
            System.out.println("Total: R$ " + String.format("%.2f" , m4));
        } else {
            m5 = 1.50 * quant;
            System.out.println("Total: R$ " + String.format("%.2f" , m5));
        }
    }
}