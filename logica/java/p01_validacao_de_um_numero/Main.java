package p01_validacao_de_um_numero;

import java.util.*;

public class Main
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double num;
        num = s.nextDouble();

        if (num >= 0 && num <= 200) {
            System.out.println("Valido");
        } else {
            System.out.println("Invalido");
        }
    }
}