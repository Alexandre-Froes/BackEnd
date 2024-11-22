package BackEnd.algoritmos.beecrowd.b5421_tamanho_da_string;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String a = ler.nextLine();
        
        System.out.println(a.length() >= 10 ? "grande" : "pequeno");
        
        ler.close();
    }
}
