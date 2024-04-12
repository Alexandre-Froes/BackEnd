package b1043_triangulo;

import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double a, b, c;

        a = s.nextDouble();
        b = s.nextDouble();
        c = s.nextDouble();

        if (a < (b + c) && a > (b - c)) {
            double perimetro = a + b + c;
            System.out.println("Perimetro = " + String.format("%.1f" , perimetro));
        } else {
            double area = ((a + b) * c) / 2;
            System.out.println("Area = " + String.format("%.1f" , area));
        }
    }
}