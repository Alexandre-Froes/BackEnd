package Main; 

import java.util.Scanner;

public class Main {
    static double le(int i) {
        Scanner ler = new Scanner(System.in);
        double x = 0;

        if(i == 1) {
            System.out.println("Para 'a' digite um valor diferente de 0");
            x = ler.nextDouble(); 
        } else if (i == 2) {
            System.out.println("Digite o valor de 'b'");
            x = ler.nextDouble();
        } else {
            System.out.println("Digite o valor de 'c'");
            x = ler.nextDouble();
        }

        return x;
    }

    static void exibe(double x1, double x2) {
        System.out.printf("O valor de x1 é %.2f%nO valor de x2 é %.2f", x1, x2);
    }
    public static void main(String[] args) {
        double a = 0;
        double b = 0;
        double c = 0;
        int i = 0;

        do {
            a = le(1);
        } while(a == 0);

        b = le(2);

        c = le(3);

        EGrau2 Bhask = new EGrau2(a, b, c);
        double d = Bhask.delta();

        if(d < 0) {
            System.out.println("Não existem raízes reais");
        } else {
            double x1 = Bhask.calculaX1(d);
            double x2 = Bhask.calculaX2(d);

            exibe(x1, x2);
        }
    }
}
