package divisao;

public class DivisaoNaoExata extends Exception {
    private int num;
    private int den;

    public DivisaoNaoExata(int num, int den) {
        this.num = num;
        this.den = den;
    }

    @Override
    public String toString() {
        return "Divisão não exata: " + num + "/" + den;
    }
}