package ex03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();
        
        Integer num = 0;

        do {
            num = ler.nextInt();
            
            if (num < 0) {
                break;
            }

            numeros.add(num);
        } while (num > 0);
        
        HashSet<Integer> numerosSet = new HashSet<>(numeros);
        for (Integer integer : numerosSet) {
            System.out.printf("%d ", integer);
        }

        ler.close();
    }
}
