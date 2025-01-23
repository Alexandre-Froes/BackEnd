package ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        String nome = "";

        do {
            nome = ler.nextLine();

            if (nome.equals("FIM")) {
                break;
            }

            nomes.add(nome);

        }while(true);

        Collections.reverse(nomes);

        for(String i : nomes) {
            System.out.println(i);
        }

        ler.close();
    }
}

