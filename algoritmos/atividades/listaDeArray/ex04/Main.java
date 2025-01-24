package ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        String nomeAtual = "";

        do {
            nomeAtual = ler.nextLine();
            if (nomeAtual.equals("fim"))
                break;

            if (nomes.contains(nomeAtual)) {
                System.out.println("convidado existente");
                continue;
            }
            
            nomes.add(nomeAtual);
        } while (true);

        for(String nome : nomes) {
            System.out.printf(nome + " ");
        }

        // Checar se existe convidado com o nome solicitado
        nomeAtual = ler.nextLine();
        if (nomes.contains(nomeAtual)) {
            System.out.println("Convidado(a) está na lista");
        } else {
            System.out.println("Não existe convidado chamado(a)" + nomeAtual);
        }

        ler.close();
    }
}