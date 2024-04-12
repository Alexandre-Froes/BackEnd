package b1013_o_maior;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int A = s.nextInt();
        int B = s.nextInt();
        int C = s.nextInt();

        int maiorAB = ((A + B + Math.abs(A - B)))/2;
        int total = ((C + maiorAB + Math.abs(C - maiorAB )))/2;

        System.out.println(total + " eh o maior");
    }
}