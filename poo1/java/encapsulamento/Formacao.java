package encapsulamento;

public class Formacao {
    private String nivel;
    private boolean concluido;
    private String instituto;
    private int ano;

    public void Formacao(String nivel, boolean concluido, String instituto, int ano) {
        this.nivel = nivel;
        this.concluido = concluido;
        this.instituto = instituto;
        this.ano = ano;
    }

    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public boolean isConcluido() {
        return concluido;
    }
    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public String getInstituto() {
        return instituto;
    }
    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}
