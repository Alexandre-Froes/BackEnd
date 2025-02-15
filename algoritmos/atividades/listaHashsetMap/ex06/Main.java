package ex06;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        HashMap<Integer, Integer> numeros = new HashMap<>();
        Integer num = 0;

        do {
            num = ler.nextInt();
            if (num < 0) {
                break;
            }

            if (numeros.containsKey(num)) {
                numeros.put(num, numeros.get(num) + 1);
            } else {
                numeros.put(num, 1);
            }
        } while (num > 0);
        
        for (Integer key : numeros.keySet()) {
            System.out.printf("%d: %d\n", key, numeros.get(key));
        }

        ler.close();
    }
}