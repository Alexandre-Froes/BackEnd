package b1241_encaixa_ou_nao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();

        for(int i = 0; i < n; i++) {
            String a = ler.next();
            String b = ler.next();

            for(int j = (a.length() - b.length()); j < a.length(); j++) {
                if(a.length() < b.length() || 
                   a.charAt(j) != b.charAt(j - (a.length() - b.length()))) {
                    System.out.println("nao encaixa");
                    break;
                } else if(j == a.length() - 1) {
                    System.out.println("encaixa");
                }
            }
        }
        ler.close();
    }
}
