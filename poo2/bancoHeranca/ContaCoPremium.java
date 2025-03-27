public class ContaCoPremium extends ContaCo {
    private double cashBackPercentual;

    public ContaCoPremium(String titular, double saldo, double limiteChequeEspecial, double cashBackPercentual) {
        super(titular, saldo, limiteChequeEspecial);
        this.cashBackPercentual = cashBackPercentual;
    }

    @Override
    public boolean sacar(double qtd) {
        if(super.sacar(qtd)) {
            saldo += (qtd * cashBackPercentual/100);
            return true;
            
        } else {
            return false;
        }
    }

    public String exibeBeneficioPremium(){
        return "Conta Corrente Premium com cashBack de " +
        String.format("%.2f%%", cashBackPercentual) +
        " em cada saque.";
    }
}
