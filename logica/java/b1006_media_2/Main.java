package b1006_media_2;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        double A = s.nextDouble();
        double B = s.nextDouble();
        double C = s.nextDouble();

        double media = (A * 2 + B * 3 + C * 5)/10;

        System.out.println("MEDIA = " + String.format("%.1f" , media));
    }
}