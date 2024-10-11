import java.util.Scanner;

public class Main {
    static int somarAlgarismos(int n) {
        if(n == 0) {
            return 0;
        } else {
            return n % 10 + somarAlgarismos(n / 10);   
        }
    }

    static int reduzirAlgarismo(int n) {
        if (n < 10) {
            return n;
        }
        return reduzirAlgarismo(somarAlgarismos(n));
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();
        
        int resultado = reduzirAlgarismo(n);

        System.out.println(resultado);
        
        ler.close();
    }
}
