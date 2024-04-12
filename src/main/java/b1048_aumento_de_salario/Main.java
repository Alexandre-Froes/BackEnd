package b1048_aumento_de_salario;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double salario,  total;
        double aumento;

        salario = s.nextDouble();

        if (salario > 0 && salario <= 400) {
            total = (salario * 0.15) + salario;
            aumento = total - salario;
            System.out.println("Novo salario: " + String.format("%.2f" , total));
            System.out.println("Reajuste ganho: " + String.format("%.2f" , aumento));
            System.out.println("Em percentual: 15 %");

        } else if (salario > 400 && salario <= 800) {
            total = (salario * 0.12) + salario;
            aumento = total - salario;
            System.out.println("Novo salario: " + String.format("%.2f" , total));
            System.out.println("Reajuste ganho: " + String.format("%.2f" , aumento));
            System.out.println("Em percentual: 12 %");

        } else if (salario > 800 && salario <= 1200) {
            total = (salario * 0.10) + salario;
            aumento = total - salario;
            System.out.println("Novo salario: " + String.format("%.2f" , total));
            System.out.println("Reajuste ganho: " + String.format("%.2f" , aumento));
            System.out.println("Em percentual: 10 %");

        } else if (salario > 1200 && salario <= 2000) {
            total = (salario * 0.07) + salario;
            aumento = total - salario;
            System.out.println("Novo salario: " + String.format("%.2f" , total));
            System.out.println("Reajuste ganho: " + String.format("%.2f" , aumento));
            System.out.println("Em percentual: 7 %");

        } else {
            total = (salario * 0.04) + salario;
            aumento = total - salario;
            System.out.println("Novo salario: " + String.format("%.2f" , total));
            System.out.println("Reajuste ganho: " + String.format("%.2f" , aumento));
            System.out.println("Em percentual: 4 %");
        }
    }
}
