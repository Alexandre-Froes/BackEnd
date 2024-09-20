package p01_nota_da_prova;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double nota, porc;
        nota = s.nextDouble();
        porc = (nota*100) / 22;

        if (nota <= 22 && nota >= 0) {
            System.out.printf("Eu espero tirar %.2f%% da nota.%n" , porc);
        } else {
            System.out.println("Nota invalida.");

        }
    }
}