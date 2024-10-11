import java.util.Scanner;

public class somaDeImpares {
    static int somandoImpares(int n, int valorImpar, int total) {
        if(valorImpar > n){
            return total;
        } else {
            total += valorImpar;
            System.out.printf(" + %d", valorImpar);
            return somandoImpares(n, valorImpar + 2, total);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int valorImpar = 1;
        int total = 0;

        System.out.printf("1");

        int totalMain = somandoImpares(n, valorImpar + 2, total);

        if(totalMain == 1) {
            System.out.printf(" = %d%n", totalMain); 
        } else {
            System.out.printf(" = %d%n", totalMain + 1);
        }

        ler.close();
    }
}