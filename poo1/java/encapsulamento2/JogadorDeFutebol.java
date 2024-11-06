package encapsulamento2;

import java.util.Calendar;

public class JogadorDeFutebol {
    private String nome;
    public String nacionalidade;
    public int dtNasc;
    public String posic;
    public double peso;
    public double altura;

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

    public int calculaIdade() {
        int idade = 0;
        
        Calendar data = Calendar.getInstance();
        int anoAtual = data.get(Calendar.YEAR);

        idade = anoAtual - (dtNasc % 10000);

        return idade;
    }
}
