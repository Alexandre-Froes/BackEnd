package exercicios;

import estruturas.Pilha;
import estruturas.PilhaArray;

/**
 * Exercício 1 — Inversão de sequência com Pilha
 * 
 * Implemente o método inverter(String texto) que inverte a string recebida
 * usando exclusivamente as operações da interface Pilha<Character>.
 * Não use StringBuilder.reverse() nem arrays auxiliares.
 * 
 * Complexidade: Temporal O(n), Espacial O(n)
 */
public class Exercicio1_InversaoPilha {

    public static String inverter(String texto) {
        Pilha<Character> pilha = new PilhaArray<>();
        
        // Passo 1: empilhe cada caractere de texto
        for (char c : texto.toCharArray()) {
            pilha.push(c);
        }
        
        // Passo 2: desempilhe e concatene em uma nova String
        StringBuilder resultado = new StringBuilder();
        while (!pilha.isEmpty()) {
            resultado.append(pilha.pop());
        }
        
        return resultado.toString();
    }

    public static void main(String[] args) {
        // Casos de teste
        System.out.println("Teste 1: inverter(\"IFTM\") = \"" + inverter("IFTM") + "\"");
        System.out.println("Esperado: \"MTFI\"");
        System.out.println();
        
        System.out.println("Teste 2: inverter(\"\") = \"" + inverter("") + "\"");
        System.out.println("Esperado: \"\"");
        System.out.println();
        
        System.out.println("Teste 3: inverter(\"A\") = \"" + inverter("A") + "\"");
        System.out.println("Esperado: \"A\"");
        System.out.println();
        
        System.out.println("Teste 4: inverter(\"Estrutura de Dados\") = \"" + inverter("Estrutura de Dados") + "\"");
        System.out.println("Esperado: \"sodaD ed aruturtE\"");
    }
}
