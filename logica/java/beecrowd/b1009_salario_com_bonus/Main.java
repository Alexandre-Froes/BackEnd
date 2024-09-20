package b1009_salario_com_bonus;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        String nome = s.next();
        double salario = s.nextDouble();
        double vendas = s.nextDouble();
        double comissao = vendas * 0.15;
        double total = salario + comissao;

        System.out.println("TOTAL = R$ " + String.format("%.2f" , total));
    }
}
