package STR13_validando_email;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String email = ler.nextLine();
        ler.close();
        boolean validacao = email.matches("^(\\w+)([._-]?\\w+)*@\\w+([.]\\w+)*[.]\\w{2,6}$");

        if(validacao) {
            System.out.println("Email válido");
        } else {
            System.out.println("Email inválido");
        }
        
        ler.close();   
    }
}