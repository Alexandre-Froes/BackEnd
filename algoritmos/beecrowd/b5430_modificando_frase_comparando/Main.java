package BackEnd.algoritmos.beecrowd.b5430_modificando_frase_comparando;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String s = ler.nextLine();

        String semA = s.replaceAll("A", "@");
        semA = semA.replaceAll("a", "@");

        String[] sSeparado = semA.split(" ");

        if (sSeparado.length < 3) {
            System.out.println("Frase invalida.");
            return;
        }

        int diferenca = sSeparado[0].compareTo(sSeparado[sSeparado.length - 1]);

		if(diferenca < 0){
            System.out.println(semA);
            System.out.println("palavra1 < palavra2");
		}else if(diferenca > 0){
            System.out.println(semA);
            System.out.println("palavra1 > palavra2");
		}else{
            System.out.println(semA);
            System.out.println("palavra1 == palavra2");
		}
        ler.close();
    }
}
