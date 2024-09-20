package b1002_area_do_circulo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double pi = 3.14159;
        double raio = s.nextDouble();
        double area = raio * raio * pi;

        System.out.println("A=" + String.format("%.4f" , area));
    }
}

