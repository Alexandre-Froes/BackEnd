package b1040_media_3;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        float N1 , N2 , N3 , N4;
        float media , mediaf, exame;

        N1 = s.nextFloat();
        N2 = s.nextFloat();
        N3 = s.nextFloat();
        N4 = s.nextFloat();

        media = (N1 * 2 + N2 * 3 + N3 * 4 + N4) / 10;

        System.out.println("Media: " + String.format("%.1f" , media));

        if (media >= 7) {

            System.out.println("Aluno aprovado.");

        }else if (media >= 5) {

            System.out.println("Aluno em exame.");
            exame = s.nextFloat();
            System.out.println("Nota do exame: " + exame);
            mediaf = (media + exame) / 2;

            if (mediaf >= 5) {
                System.out.println("Aluno aprovado.");
            } else {
                System.out.println("Aluno reprovado.");
            }

            System.out.println("Media final: " + String.format("%.1f" , mediaf));

        }else {

            System.out.println("Aluno reprovado.");
        }
    }
}
