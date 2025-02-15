package ex08;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public final static String MENU = """
        1 - Adicionar produto
        2 - Buscar Produto por ID
        3 - Remover Produto por ID
        4 - Listar Produtos
        5 - Sair
                        """;
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        HashMap<String, Produto> produtos = new HashMap<>();
        int num = 0;

        String id = "";

        do {
            System.out.println(MENU);
            num = ler.nextInt();
            ler.nextLine();

            switch (num) {
                case 1:
                    System.out.println("Digite o ID do produto:");
                    id = ler.next();
                    ler.nextLine();

                    System.out.println("Digite o nome do produto:");
                    String nome = ler.nextLine();

                    Produto produto = new Produto(id, nome);
                    produtos.put(id, produto);

                    System.out.println(produto.getNome() + " adicionado à lista");
                    break;
                case 2:
                    System.out.println("Digite o ID do produto:");
                    id = ler.next();

                    if (produtos.containsKey(id)) {
                        System.out.println(produtos.get(id).getNome());
                    } else {
                        System.out.println("Produto não encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID do produto:");
                    id = ler.next();
                    if (produtos.containsKey(id)) {
                        produtos.remove(id);
                        System.out.println("Produto removido da lista");
                    } else {
                        System.out.println("Produto não encontrado");
                    }
                    break;
                case 4:
                    for (String key : produtos.keySet()) {
                        System.out.println("- " + produtos.get(key).getNome());
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Nenhuma opção válida foi escolhida");
                    break;
            }
        } while (num != 5);

        ler.close();
    }
}