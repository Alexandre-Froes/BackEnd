package b1004_produto_simples;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        int num1 = s.nextInt();
        int num2 = s.nextInt();
        int PROD = num1 * num2;

        System.out.println("PROD = " + PROD);
    }
}

