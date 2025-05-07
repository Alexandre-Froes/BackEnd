package sim;

public class Funcionario extends Pessoa implements Pagavel{
    protected double salarioBase;

    public Funcionario(String nome, String cpf, double salarioBase) throws SalarioInvalidoException{
        super(nome, cpf);
        this.salarioBase = validarSalario(salarioBase);
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
    }

    public double calcularSalario(double valorBonus) {
        return salarioBase + valorBonus;
    }

    @Override
    public String mostrarDados() {
        return String.format("Nome: %s \nCPF: %s \nSalario base: %s\nSalario total: %s\n",
                                nome, cpf, Util.formatarMoeda(salarioBase), Util.formatarMoeda(calcularSalario()));
    }

    public double getSalarioBase() {
        return salarioBase;
    }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double validarSalario(double salario) throws SalarioInvalidoException {
        if (salario < 0) {
            throw new SalarioInvalidoException("Salario não pode ser menor que zero");
        }

        return salario;
    }
}
