package sim;

import java.util.ArrayList;

public class Departamento {
    private String nome;
    private ArrayList<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFunc(Funcionario func) {
        funcionarios.add(func);
    }

    public void listarFunc() { 
        System.out.printf("\nDepartamento: %s\n\n", nome);
        for(Funcionario f : funcionarios) {
            System.out.printf("%s\n", f.mostrarDados());
        } 
    }
}
