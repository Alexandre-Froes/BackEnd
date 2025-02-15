package ex05;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public final static String MENU = """
        1 - Adicionar convidado
        2 - Remover convidado
        3 - Verificar convidado
        4 - Listar convidados
        5 - Quantidade de convidados
        6 - Remover todos os convidados
        7 - Sair
                        """;
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        HashSet<String> convidados = new HashSet<>();
        String nome = "";
        int num = 0;
        
        do {
            System.out.println(MENU);
            num = ler.nextInt();
            ler.nextLine();

            switch (num) {
                case 1:
                    nome = ler.nextLine();
                    convidados.add(nome);
                    System.out.println(nome + " adicionado à lista");
                    break;
                case 2:
                    nome = ler.nextLine();
                    if(convidados.contains(nome)) {
                        convidados.remove(nome);
                        System.out.println("Convidado removido da lista");
                    } else {
                        System.out.println("Convidado não existe");
                    }
                    break;
                case 3:
                    nome = ler.nextLine();
                    if(convidados.contains(nome)) {
                        System.out.println("Convidado está na lista");
                    } else {
                        System.out.println("Convidado não está na lista");
                    }
                    break;
                case 4:
                    for (String string : convidados) {
                        System.out.println("- " + string);
                    }
                    break;
                case 5:
                    System.out.println("Quantidade de convidados: " + convidados.size());
                    break;
                case 6:
                    convidados.clear();
                    System.out.println("Todos os convidados foram removidos");
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Nenhuma opção válida foi escolhida");
                    break;
            }
        } while (num != 7);

        ler.close();
    }
}