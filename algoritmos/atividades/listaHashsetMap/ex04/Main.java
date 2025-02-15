package ex04;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        HashSet<String> nomes = new HashSet<>();
        String nome = "";

        do {
            nome = ler.nextLine();
            if (nome.equals("fim")) {
                break;
                
            }

            nomes.add(nome);
        } while(!nome.equals("fim"));

        String nomeReal = ler.nextLine();

        if (nomes.contains(nomeReal)) {
            System.out.println("Existe");
        } else {
            System.out.println("Não existe");
        }

        ler.close();
    }
}

