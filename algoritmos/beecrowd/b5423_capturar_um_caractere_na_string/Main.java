package BackEnd.algoritmos.beecrowd.b5423_capturar_um_caractere_na_string;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String s = ler.nextLine();

        if (s.length() < 5) {
            System.out.println("invalida");
            
        } else {
            if (s.charAt(0) == s.charAt(s.length() - 1)) { 
                System.out.println("iguais");
            } else {
                System.out.println("diferentes");
            }
        }
        ler.close();
    }
}
