package ocp.principle.solucao;

public class ContratoCLT extends Funcionario implements Remuneravel {
    private double salario;

    public ContratoCLT(String nome, String tipo, double salario) {
        super(nome, tipo);
        this.salario = salario;
    }
    
    @Override
    public double remuneracao(){
        return salario;
    }
}

