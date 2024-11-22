package BackEnd.algoritmos.beecrowd.b5425_tratar_data;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String data = ler.nextLine();
        String[] dataSplit = data.split("/");

        System.out.println("Estamos no dia "+ dataSplit[0] +" do mês.");

        ler.close();
    }
}
