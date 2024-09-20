package b1012_area;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double A = s.nextDouble();
        double B = s.nextDouble();
        double C = s.nextDouble();

        double pi = 3.14159;

        double triangulo = (A * C)/2;
        double circulo = C * C * pi;
        double trapezio = ((A + B) * C)/2;
        double quadrado = B * B;
        double retangulo = A * B;

        System.out.println("TRIANGULO: " + String.format("%.3f" , triangulo));
        System.out.println("CIRCULO: " + String.format("%.3f" , circulo));
        System.out.println("TRAPEZIO: " + String.format("%.3f" , trapezio));
        System.out.println("QUADRADO: " + String.format("%.3f" , quadrado));
        System.out.println("RETANGULO: " + String.format("%.3f" , retangulo));
    }
}
