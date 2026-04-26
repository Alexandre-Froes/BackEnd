package exercicios;

import estruturas.Pilha;
import estruturas.PilhaArray;

/**
 * Exercício 2 — Avaliador de expressões pós-fixas (Notação Polonesa Reversa)
 * 
 * Expressões pós-fixas eliminam a necessidade de parênteses: os operandos
 * precedem o operador. Por exemplo, "3 4 +" equivale a 3 + 4 = 7.
 * 
 * Algoritmo: usa uma única Pilha. Ao encontrar um número, empilha;
 * ao encontrar um operador, desempilha dois operandos, aplica o operador
 * e empilha o resultado.
 * 
 * Complexidade: O(n) onde n é o número de tokens
 */
public class Exercicio2_AvaliadosPosFixa {

    public static double avaliarPosFixa(String expressao) {
        Pilha<Double> pilha = new PilhaArray<>();
        
        for (String token : expressao.split(" ")) {
            switch (token) {
                case "+" -> {
                    double b = pilha.pop();
                    double a = pilha.pop();
                    pilha.push(a + b);
                }
                case "-" -> {
                    double b = pilha.pop();
                    double a = pilha.pop();
                    pilha.push(a - b);
                }
                case "*" -> {
                    double b = pilha.pop();
                    double a = pilha.pop();
                    pilha.push(a * b);
                }
                case "/" -> {
                    double b = pilha.pop();
                    double a = pilha.pop();
                    if (b == 0) throw new ArithmeticException("Divisão por zero");
                    pilha.push(a / b);
                }
                case "%" -> {
                    double b = pilha.pop();
                    double a = pilha.pop();
                    if (b == 0) throw new ArithmeticException("Divisão por zero");
                    pilha.push(a % b);
                }
                default -> pilha.push(Double.parseDouble(token));
            }
        }
        
        return pilha.pop();
    }

    public static void main(String[] args) {
        // Casos de teste
        System.out.println("Teste 1: \"3 4 +\" = " + avaliarPosFixa("3 4 +"));
        System.out.println("Esperado: 7.0");
        System.out.println();
        
        System.out.println("Teste 2: \"5 1 2 + 4 * + 3 -\" = " + avaliarPosFixa("5 1 2 + 4 * + 3 -"));
        System.out.println("Esperado: 14.0 (= 5 + (1+2)*4 - 3)");
        System.out.println();
        
        System.out.println("Teste 3: \"15 7 1 1 + - / 3 * 2 1 1 + + -\" = "
                + avaliarPosFixa("15 7 1 1 + - / 3 * 2 1 1 + + -"));
        System.out.println("Esperado: 5.0");
        System.out.println();
        
        System.out.println("Teste 4: \"10 2 %\" = " + avaliarPosFixa("10 2 %"));
        System.out.println("Esperado: 0.0 (módulo)");
        System.out.println();
        
        System.out.println("Teste 5: \"17 5 %\" = " + avaliarPosFixa("17 5 %"));
        System.out.println("Esperado: 2.0 (módulo)");
    }
}
