package BackEnd.algoritmos.beecrowd.b5420_custo_de_moradia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        double n = ler.nextInt();
        String cidade = ler.next();

        switch (cidade.toLowerCase()) {
            case "brasilia":
                System.out.printf("O custo de vida em %s e R$ %.2f%n", cidade, n * 2);
                break;
            case "uberlandia":
                System.out.printf("O custo de vida em %s e R$ %.2f%n", cidade, n);
                break;           
            default:
                System.out.printf("O custo de vida em %s e R$ %.2f%n", cidade, n / 2);    
                break;
        }

        ler.close();
    }
}
