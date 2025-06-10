package lsp.violacao;

public class LspViolacao {
    
    public static void main(String[] args) {

       System.out.println("Calculando a área do retângulo usando a superclasse Retângulo\n");
       Retangulo ret1 = new Retangulo(4,5);
       System.out.println("A área do retângulo é: "+ret1.calculaArea());

       System.out.println("Calculando a área do retângulo usando a subclasse Quadrado\n");
       Retangulo ret2 = new Quadrado(4);
       System.out.println("A área do quadrado é: "+ret2.calculaArea());
    }
}




