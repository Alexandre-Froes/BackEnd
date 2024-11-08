package BackEnd.algoritmos.beecrowd.b1024_criptografia;

import java.util.Scanner;

public class Main {
    static String deslocarString(int desloca, String encript, int index) {
        String deslocada = "";
        
        if(index == 1) {
            for(char c : encript.toCharArray()) {
                if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
                    char charDeslocado = (char) (c + desloca);
                    deslocada += charDeslocado;

                } else {
                    deslocada += c;
                }
            }

        } else {
            for(char c : encript.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    deslocada += c;
                } else {
                    char charDeslocado = (char) (c + desloca);
                    deslocada += charDeslocado;
                }
            }
        }
    
        return deslocada;
    }
    
    static String inverterString(String encript) {
        char[] chars = encript.toCharArray();
        char[] charsInvertido = new char[chars.length];
    
        for (int i = 0; i < chars.length; i++) {
            charsInvertido[i] = chars[chars.length - 1 - i];
        }
    
        return new String(charsInvertido);
    }
    
    static String inverterSegundaParte(String encript) {
        int metade = encript.length() / 2;
        String primeiraParte = encript.substring(0, metade);
        String segundaParte = encript.substring(metade);
    
        segundaParte = deslocarString(-1, segundaParte, 2);

        encript = primeiraParte + segundaParte;

        return encript;
    }
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n = ler.nextInt();
        ler.nextLine();

        for(int i = 0; i < n; i++) {
            String encript = ler.nextLine();

            encript = deslocarString(3, encript, 1);

            encript = inverterString(encript);

            encript = inverterSegundaParte(encript);

            System.out.println(encript);
        }
    }
}

// Texto #3
// abcABC1
// vxpdylY .ph
// vv.xwfxo.fd

// 3# rvzgV
// 1FECedc
// ks. \n{frzx
// gi.r{hyz-xx