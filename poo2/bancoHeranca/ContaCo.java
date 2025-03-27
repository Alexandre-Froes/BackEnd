public class ContaCo extends ContaBancaria {
    protected double limiteChequeEspecial;

    public ContaCo(String titular, double saldo, double limiteChequeEspecial) {
        super(titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double qtd) {
        if((saldo + limiteChequeEspecial) >= qtd) {
            saldo -= (qtd - limiteChequeEspecial);
            return true;
        } else {
            return false;
        }
    }
    
    String exibeLimiteChequeEspecial() {
        return "Limite de Cheque Especial: R$ " +
        String.format("%.2f", limiteChequeEspecial);
    }
}
