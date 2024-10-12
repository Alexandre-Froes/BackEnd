package b1153_fatorial_simples;

import java.util.Scanner;

public class Main {
    public static int fatorial(int n) {
        if(n == 0 || n == 1) {
            return 1;
        } else {
            return n * fatorial(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();

        int tmp = fatorial(n);

        System.out.println(tmp);

        ler.close();
    }
}
