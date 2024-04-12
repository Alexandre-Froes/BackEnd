package b1016_distancia;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int x = s.nextInt();
        int y = (x * 2);

        System.out.println(y + " minutos");
    }
}
