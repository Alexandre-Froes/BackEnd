package ocp.principle.solucao;

public class ContratoEstagio extends Funcionario implements Remuneravel {
    private double bolsa;

    public ContratoEstagio(String nome, String tipo, double bolsa) {
        super(nome, tipo);
        this.bolsa = bolsa;
    }

    @Override
    public double remuneracao() {
        return bolsa;  
    }
    
}

