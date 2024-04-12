package b1020_idade_em_dias;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int dia = s.nextInt();

        int ano = (dia / 365);
        dia -= (ano * 365);
        int mes = (dia / 30);
        dia -= (mes * 30);

        System.out.println(ano + " ano(s)");
        System.out.println(mes + " mes(es)");
        System.out.println(dia + " dia(s)");
    }
}
