package b1042_sort_simples;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        int A, B, C, maior;

        A = s.nextInt();
        B = s.nextInt();
        C = s.nextInt();

        int[] arrayMaior = {A, B, C};

        Arrays.sort(arrayMaior);

        System.out.println(arrayMaior[0]);
        System.out.println(arrayMaior[1]);
        System.out.println(arrayMaior[2]);
        System.out.println();
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
    }
}