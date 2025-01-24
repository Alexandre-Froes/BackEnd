package ex05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = 5;
        List<String> nomes = new ArrayList<>();

        // Adicionando nomes a lista
        for (int i = 0; i < n; i++)
            nomes.add(ler.nextLine());

        // Excluindo um nome da lista
        System.out.println("Qual dos nomes abaixo você deseja excluir?");
        for (String nome : nomes) 
            System.out.println((nomes.indexOf(nome) + 1) + " - " + nome);
        
        System.out.println("Digite o número correspondente ao nome que deseja excluir:");
        int index = ler.nextInt();
        
        nomes.remove(index - 1);

        // imprimindo sem o nome excluido
        System.out.println("Resultado:");
        for (String nome : nomes) 
            System.out.println((nomes.indexOf(nome) + 1) + " - " + nome);

        ler.close();
    }
}