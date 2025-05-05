package iftm.tspi.ta.xande.tdd;

public class FuncionarioTercerizado extends Funcionario {
    private Double despensasAdicionais;

    public FuncionarioTercerizado(String nome, Integer horasTrabalhadas, Double valorHora, Double despensasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        this.despensasAdicionais = despensasAdicionais;
    }

    public FuncionarioTercerizado(Double despensasAdicionais) {
        this.despensasAdicionais = despensasAdicionais;
    }

    public Double getDespensasAdicionais() {
        throw new UnsupportedOperationException("Metodo ainda não implementado.");
    }

    public void setDespensasAdicionais(Double despensasAdicionais) {
        throw new UnsupportedOperationException("Metodo ainda não implementado.");
    }
}
