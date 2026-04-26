package exercicios;

import estruturas.PilhaArray;
import estruturas.PilhaLista;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Exercício 4 — Comparação empírica com System.nanoTime()
 * 
 * Escreva um programa que mede o tempo de 1.000.000 de operações push+pop
 * em PilhaArray, PilhaLista e ArrayDeque do JCF.
 * 
 * Execute o teste três vezes e calcule a média (para reduzir o efeito da
 * compilação JIT e do GC).
 * 
 * O resultado deve confirmar a análise teórica:
 * - PilhaArray: mais rápida devido à localidade de cache
 * - PilhaLista: mais lenta (alocação de Node, falta de cache locality)
 * - ArrayDeque: similar a PilhaArray
 */
public class Exercicio4_ComparacaoTempo {
    private static final int NUM_OPERACOES = 1_000_000;
    private static final int NUM_REPETICOES = 3;

    interface PilhaInterface {
        void push(int valor);
        int pop();
        boolean isEmpty();
    }

    static class PilhaArrayWrapper implements PilhaInterface {
        private PilhaArray<Integer> pilha = new PilhaArray<>();
        
        @Override public void push(int valor) { pilha.push(valor); }
        @Override public int pop() { return pilha.pop(); }
        @Override public boolean isEmpty() { return pilha.isEmpty(); }
    }

    static class PilhaListaWrapper implements PilhaInterface {
        private PilhaLista<Integer> pilha = new PilhaLista<>();
        
        @Override public void push(int valor) { pilha.push(valor); }
        @Override public int pop() { return pilha.pop(); }
        @Override public boolean isEmpty() { return pilha.isEmpty(); }
    }

    static class ArrayDequeWrapper implements PilhaInterface {
        private Deque<Integer> pilha = new ArrayDeque<>();
        
        @Override public void push(int valor) { pilha.push(valor); }
        @Override public int pop() { return pilha.pop(); }
        @Override public boolean isEmpty() { return pilha.isEmpty(); }
    }

    private static long medir(PilhaInterface pilha) {
        // Aquecimento da JVM (JIT compilation)
        for (int i = 0; i < NUM_OPERACOES / 100; i++) {
            pilha.push(i);
            if (!pilha.isEmpty()) pilha.pop();
        }
        
        long inicio = System.nanoTime();
        
        // Teste real
        for (int i = 0; i < NUM_OPERACOES; i++) {
            pilha.push(i);
        }
        for (int i = 0; i < NUM_OPERACOES; i++) {
            pilha.pop();
        }
        
        return System.nanoTime() - inicio;
    }

    public static void main(String[] args) {
        System.out.println("=== Exercício 4: Comparação de Desempenho ===\n");
        System.out.println("Medindo " + NUM_OPERACOES + " operações push+pop");
        System.out.println("Executando " + NUM_REPETICOES + " vezes para calcular média\n");

        long[] temposPilhaArray = new long[NUM_REPETICOES];
        long[] temposPilhaLista = new long[NUM_REPETICOES];
        long[] temposArrayDeque = new long[NUM_REPETICOES];

        for (int i = 0; i < NUM_REPETICOES; i++) {
            System.out.println("Iteração " + (i + 1) + ":");
            
            temposPilhaArray[i] = medir(new PilhaArrayWrapper());
            System.out.printf("  PilhaArray:  %,d ns = %.2f ms%n", 
                temposPilhaArray[i], temposPilhaArray[i] / 1e6);
            
            temposPilhaLista[i] = medir(new PilhaListaWrapper());
            System.out.printf("  PilhaLista:  %,d ns = %.2f ms%n", 
                temposPilhaLista[i], temposPilhaLista[i] / 1e6);
            
            temposArrayDeque[i] = medir(new ArrayDequeWrapper());
            System.out.printf("  ArrayDeque:  %,d ns = %.2f ms%n", 
                temposArrayDeque[i], temposArrayDeque[i] / 1e6);
            
            System.out.println();
        }

        long mediaArray = calcularMedia(temposPilhaArray);
        long mediaLista = calcularMedia(temposPilhaLista);
        long mediaDeque = calcularMedia(temposArrayDeque);

        System.out.println("=== RESULTADOS (MÉDIA) ===\n");
        System.out.printf("PilhaArray:  %.2f ms%n", mediaArray / 1e6);
        System.out.printf("PilhaLista:  %.2f ms%n", mediaLista / 1e6);
        System.out.printf("ArrayDeque:  %.2f ms%n", mediaDeque / 1e6);
        System.out.println();

        System.out.printf("PilhaLista é %.2fx mais lenta que PilhaArray%n", 
            (double) mediaLista / mediaArray);
        System.out.printf("ArrayDeque é %.2fx comparado a PilhaArray%n", 
            (double) mediaDeque / mediaArray);

        System.out.println("\n=== ANÁLISE TEÓRICA ===\n");
        System.out.println("Cache Locality:");
        System.out.println("- PilhaArray: Elementos contíguos em memória → Prefetch de cache eficiente");
        System.out.println("- PilhaLista: Nós espalhados pela heap → Cache misses frequentes");
        System.out.println();
        System.out.println("Alocação:");
        System.out.println("- PilhaArray: Realoca inteiro em blocos (amortizado)");
        System.out.println("- PilhaLista: Aloca Node a cada push (pressão no GC)");
        System.out.println();
        System.out.println("Conclusão:");
        System.out.println("PilhaArray é consistentemente 2-5x mais rápida em sistemas reais");
    }

    private static long calcularMedia(long[] valores) {
        long soma = 0;
        for (long v : valores) soma += v;
        return soma / valores.length;
    }
}
