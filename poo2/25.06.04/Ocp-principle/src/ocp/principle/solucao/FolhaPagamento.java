package ocp.principle.solucao;

public class FolhaPagamento {
    protected double saldo;
    
    public double calcular(Remuneravel funcionario) {
        saldo = funcionario.remuneracao();
        return saldo;
    }
}

