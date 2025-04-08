public class Vendedor extends Funcionario {
    private double totalVendas;

    public Vendedor(String nome, String clt, double salario, double totalVendas) {
        super(nome, clt, salario);
        this.totalVendas = totalVendas;
    }

    @Override
    public double calculaSalario() {
        return salario + (totalVendas / 100);
    }

    public double getTotalVendas() {
        return totalVendas;
    }
}