package b1072_intervalo_2;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int in = 0; 
        int out = 0;

        for(int i = 0; i < n; i++){
            double num = s.nextInt();

            if (num >= 10 && num <= 20) {

                in++;

            } else {

                out++;

            }
        }

        System.out.println(in + " in");
        System.out.println(out + " out");
    }
}
