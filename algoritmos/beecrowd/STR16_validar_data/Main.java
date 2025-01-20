package STR16_validar_data;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String data = ler.nextLine();
        boolean validacao = false;
        
        validacao = data.matches("^(0\\d|[1-2]\\d|3[0-1])/(0\\d|1[1-2])/(?!0000)\\d{4}$");

        if (validacao) {
            System.out.println("Data válida");
        } else {
            System.out.println("Data inválida");
        }

        ler.close();
    }
}