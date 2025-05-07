package sim;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        try {
            Departamento dep = new Departamento("R");

            Funcionario f1 = new Funcionario("Xande",
            "123", 123);
    
            Gerente g1 = new Gerente("Xande(but better)",
            "1234", 234, 1000);

            dep.adicionarFunc(g1);
            dep.adicionarFunc(f1);
    
            dep.listarFunc();

        } catch(SalarioInvalidoException e) {
            System.out.println(e.getMessage());
        }

        ler.close();
    }
}
