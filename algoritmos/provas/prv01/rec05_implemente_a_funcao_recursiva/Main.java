import java.util.Scanner;

public class Main {
    static int funcaoRecusiva(int m, int n) {
        if (n == 1) {
            return m + 1;
        } else if (m == 1) {
            return n + 1;
        } else if (n > 1 && m > 1) {
            return funcaoRecusiva(m, n - 1) + funcaoRecusiva(m - 1, n);
        }

        return 1;
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int m = ler.nextInt();
        int n = ler.nextInt();

        int resultado = funcaoRecusiva(m, n);

        System.out.printf("h(%d,%d) = %d%n", m, n, resultado);
        ler.close();
    }
}
