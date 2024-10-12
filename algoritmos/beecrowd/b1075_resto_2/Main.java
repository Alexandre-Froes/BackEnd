import java.util.Scanner;

public class Main {
    static void restoDois(int n, int restoDois) {
        if(restoDois > 10000) {
            return;
        } else if (restoDois % n == 2) {
            System.out.println(restoDois);
            restoDois(n, restoDois + 1);
        } else {
            restoDois(n, restoDois + 1);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();
        int restoDois = 0;

        restoDois(n, restoDois);
        
        ler.close();
    }
}