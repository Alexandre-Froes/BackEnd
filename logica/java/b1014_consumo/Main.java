package b1014_consumo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        int x = s.nextInt();
        double y = s.nextDouble();
        double total = x/y;

        System.out.println(String.format("%.3f" , total) + " km/l");
    }
}
