package p01_bolsa_de_estudos;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double renda, falta, nota;

        nota = s.nextDouble();
        falta = s.nextDouble();
        renda = s.nextDouble();

        if (nota >= 8 && falta <= 10 && renda < 2000 || nota > 9.5 && falta == 0) {
            System.out.println("Pode receber bolsa");

        } else {
            System.out.println("Não pode rebecer bolsa");
        }
    }
}
