package STR15_validar_telefone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String telefone = ler.nextLine();
        boolean validacao;
        boolean fixo = false;

        if (telefone.length() == 14) {
            validacao = telefone.matches("^\\(\\d{2}\\) (\\d{4})-(\\d{4})$");
            fixo = (validacao) ? !fixo : fixo;
        } else {
            validacao = telefone.matches("^\\(\\d{2}\\) 9(\\d{4})-(\\d{4})$");
        }

        if (validacao) {
            if (fixo) {
                System.out.println("Fixo");
            } else {
                System.out.println("Celular");
            }
        } else {
            System.out.println("inválido");
            
        }
        ler.close();
    }
}