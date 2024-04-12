package b1008_salario;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        int number = s.nextInt();
        int horas = s.nextInt();
        double salarioh = s.nextDouble();
        double salario = horas * salarioh;

        System.out.println("NUMBER = " + number);
        System.out.println("SALARY = U$ " + String.format("%.2f" , salario));
    }
}
