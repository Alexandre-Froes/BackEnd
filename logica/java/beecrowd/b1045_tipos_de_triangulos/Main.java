package b1045_tipos_de_triangulos;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double A, B, C;
        double maior;

        A = s.nextDouble();
        B = s.nextDouble();
        C = s.nextDouble();

        double[] arrayMaior = {A, B, C};

        Arrays.sort(arrayMaior);

        A = (arrayMaior[2]);
        B = (arrayMaior[1]);
        C = (arrayMaior[0]);


        if ( A >= (B + C)) {
            System.out.println("NAO FORMA TRIANGULO");

        } else if (Math.pow(A , 2) > Math.pow(B , 2) + Math.pow(C , 2)) {
            System.out.println("TRIANGULO OBTUSANGULO");

            if (A == B && B == C && A == C) {
                System.out.println("TRIANGULO EQUILATERO");
            } else if (A == B || B == C || C == A) {
                System.out.println("TRIANGULO ISOSCELES");
            }

        } else if (Math.pow(A , 2) < Math.pow(B , 2) + Math.pow(C , 2)) {
            System.out.println("TRIANGULO ACUTANGULO");

            if (A == B && B == C && A == C) {
                System.out.println("TRIANGULO EQUILATERO");
            } else if (A == B || B == C || C == A) {
                System.out.println("TRIANGULO ISOSCELES");
            }

        } else if (Math.pow(A , 2) == Math.pow(B , 2) + Math.pow(C , 2)) {
            System.out.println("TRIANGULO RETANGULO");

        }
    }
}
