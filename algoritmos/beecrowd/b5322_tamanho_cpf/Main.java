package BackEnd.algoritmos.beecrowd.b5322_tamanho_cpf;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String cpf = ler.nextLine();
        
        System.out.println(cpf.length() == 11 ? "valido" : "invalido");
        ler.close();
    }
}
