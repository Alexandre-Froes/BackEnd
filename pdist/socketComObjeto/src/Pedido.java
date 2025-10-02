import java.io.Serializable;

public class Pedido implements Serializable {
    private String cpf;
    private Double valor;

    public Pedido(String cpf, Double valor) {
        this.cpf = cpf;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pedido: [cpf=" + cpf + ", valor=" + valor + "]";
    }

    public String getCpf() {
        return cpf;
    }

    public Double getValor() {
        return valor;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
