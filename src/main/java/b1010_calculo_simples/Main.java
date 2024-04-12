package b1010_calculo_simples;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        // peça 01
        int num01 = s.nextInt();
        int quant01 = s.nextInt();
        double preco01 = s.nextDouble();

        // peça 02
        int num02 = s.nextInt();
        int quant02 = s.nextInt();
        double preco02 = s.nextDouble();

        double total = quant01 * preco01 + quant02 * preco02;

        System.out.println("VALOR A PAGAR: R$ " + String.format("%.2f" , total));
    }
}
