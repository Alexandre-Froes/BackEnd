package sim;

public class Gerente extends Funcionario {
    private double bonus;

    public Gerente(String nome, String cpf, double salarioBase, double bonus) throws SalarioInvalidoException{
        super(nome, cpf, salarioBase);
        if (bonus < 0) {
            throw new SalarioInvalidoException("Bonus invalido. Tem que ser maior que 0");
        }
        this.bonus = bonus;
    }

    @Override
    public double calcularSalario() {
        return  salarioBase + bonus;
    }

    @Override
    public double calcularSalario(double bonus) {
        return  super.calcularSalario(bonus) + bonus;
    }

    @Override
    public String mostrarDados() {
        return String.format("%s\n", super.mostrarDados());
    }
}
