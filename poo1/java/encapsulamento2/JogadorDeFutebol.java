package encapsulamento2;

import java.util.Calendar;

public class JogadorDeFutebol {
    private String nome;
    private String nacionalidade;
    private int dtNasc;
    private String posic;
    private double peso;
    private double altura;

    public JogadorDeFutebol(String nome, String nacionalidade, int dtNasc, String posic, double peso, double altura) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dtNasc = dtNasc;
        this.posic = posic;
        this.peso = peso;
        this.altura = altura;
    }
    public JogadorDeFutebol() {
        
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getDtNasc() {
        return dtNasc;
    }
    public void setDtNasc(int dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getPosic() {
        return posic;
    }
    public void setPosic(String posic) {
        this.posic = posic;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int calculaIdade() {
        int idade = 0;
        
        Calendar data = Calendar.getInstance();
        int anoAtual = data.get(Calendar.YEAR);

        idade = anoAtual - (dtNasc % 10000);

        return idade;
    }
}
