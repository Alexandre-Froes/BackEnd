package iftm.edu.br.tspi.pmvc.xande.menefreda.domain;

public class Plano {
    private int codigo;
    private double valor;
    private String validade;
    private String tipo;

    public Plano() {}

    public Plano(int codigo) {
        this.codigo = codigo;
    }

    public Plano(int codigo, double valor, String validade, String tipo) {
        this.codigo = codigo;
        this.valor = valor;
        this.validade = validade;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
