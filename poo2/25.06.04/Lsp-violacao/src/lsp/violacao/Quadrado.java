package lsp.violacao;


public class Quadrado extends Retangulo {
    
    public Quadrado(double lado) {
        super(lado, lado);
    }
    
    public double getAltura() {
        return super.getAltura();
    }

    public void setAltura(double altura) {
        super.setAltura(altura);
    }

    public double getLargura() {
        return super.getAltura();
    }

    public void setLargura(double largura) {
        super.setLargura(largura);
    }

    @Override
    public double calculaArea() {
        return getAltura()*getAltura();
    }
}

