package b1049_animal;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        String coluna = s.nextLine();
        String classe = s.nextLine();
        String dieta = s.nextLine();

        if (coluna.equals("vertebrado")) {
            if (classe.equals("ave")) {
                if (dieta.equals("carnivoro")) {
                    System.out.println("aguia");
                } else {
                    System.out.println("pomba");
                }

            } else {
                if (dieta.equals("onivoro")) {
                    System.out.println("homem");
                } else {
                    System.out.println("vaca");
                }
            }

        } else {
            if (classe.equals("inseto")) {
                if (dieta.equals("hematofago")) {
                    System.out.println("pulga");
                } else {
                    System.out.println("lagarta");
                }

            } else {
                if (dieta.equals("hematofago")) {
                    System.out.println("sanguessuga");
                } else {
                    System.out.println("minhoca");
                }
            }
        }
    }
}