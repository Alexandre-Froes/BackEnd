package ex07;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        HashMap<String, Integer> stringInt = new HashMap<>();
        HashMap<Integer, String> intString = new HashMap<>();
        String letra = "";

        do {
            letra = ler.next();
            if (letra.equals("fim")) {
                break;
            }

            Integer num = ler.nextInt();

            stringInt.put(letra, num);
        } while (!letra.equals("fim"));

        for(String chave : stringInt.keySet()){
            intString.put(stringInt.get(chave), chave);
        }
        
        System.out.println(intString.toString());

        ler.close();
    }
}