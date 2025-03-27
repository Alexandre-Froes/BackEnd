public class ContaPoEstudantil extends ContaPo{
    private double limiteIsencaoTaxa;

    public ContaPoEstudantil(String titular, double saldo, double taxaRendimento, double limiteIsencaoTaxa) {
        super(titular, saldo, taxaRendimento);
        this.limiteIsencaoTaxa = limiteIsencaoTaxa;
    }

    @Override
    public boolean sacar(double qtd) {
        if (super.sacar(qtd)) {
            return true;
        } else {
            return false;
        }
    }

    public String exibeLimiteIsencao() {
        return "Limite de insenção de taxa para estudantes " +
        String.format("%.2f%%", limiteIsencaoTaxa);
    }
}
