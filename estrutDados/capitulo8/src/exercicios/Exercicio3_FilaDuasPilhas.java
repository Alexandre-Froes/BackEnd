package exercicios;

import estruturas.Fila;
import estruturas.PilhaArray;

/**
 * Exercício 3 — Fila usando duas Pilhas
 * 
 * Questão clássica de entrevistas técnicas (Amazon, Google, Meta):
 * implemente uma Fila usando apenas duas instâncias de PilhaArray<T> como
 * estrutura interna.
 * 
 * Ideia central: usar uma pilha para receber os elementos (entrada) e outra
 * para servir as remoções (saída). Quando saida está vazia e dequeue é
 * chamado, todos os elementos de entrada são transferidos para saida —
 * invertendo a ordem e restaurando a sequência FIFO.
 * 
 * Complexidade:
 * - enqueue: Θ(1)
 * - dequeue: Θ(1) amortizado (ocasionalmente Θ(n) quando inverte as pilhas)
 */
public class Exercicio3_FilaDuasPilhas<T> implements Fila<T> {
    private final PilhaArray<T> entrada = new PilhaArray<>();
    private final PilhaArray<T> saida = new PilhaArray<>();

    @Override
    public void enqueue(T elemento) {
        entrada.push(elemento); // sempre empilha em entrada
    }

    @Override
    public T dequeue() {
        transferirSeNecessario();
        if (saida.isEmpty()) throw new java.util.NoSuchElementException();
        return saida.pop();
    }

    @Override
    public T peek() {
        transferirSeNecessario();
        if (saida.isEmpty()) throw new java.util.NoSuchElementException();
        return saida.peek();
    }

    @Override
    public boolean isEmpty() {
        return entrada.isEmpty() && saida.isEmpty();
    }

    @Override
    public int size() {
        return entrada.size() + saida.size();
    }

    /**
     * Transferência: move todos de entrada para saida (inverte a ordem).
     * Apenas executada quando saida está vazia e há tentativa de dequeue.
     */
    private void transferirSeNecessario() {
        if (saida.isEmpty()) {
            while (!entrada.isEmpty()) {
                saida.push(entrada.pop());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Exercício 3: Fila com Duas Pilhas ===\n");
        
        // Teste 1: Operações básicas FIFO
        System.out.println("Teste 1: Operações básicas FIFO");
        Exercicio3_FilaDuasPilhas<Integer> fila = new Exercicio3_FilaDuasPilhas<>();
        
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        
        System.out.println("Enfileirou: 1, 2, 3");
        System.out.println("Desenfileirado 1: " + fila.dequeue());
        System.out.println("Desenfileirado 2: " + fila.dequeue());
        System.out.println("Desenfileirado 3: " + fila.dequeue());
        System.out.println("Esperado: 1, 2, 3 (em ordem FIFO)");
        System.out.println("Fila vazia? " + fila.isEmpty());
        System.out.println();
        
        // Teste 2: Interleaving (mistura de operações)
        System.out.println("Teste 2: Interleaving de enqueue/dequeue");
        Exercicio3_FilaDuasPilhas<String> fila2 = new Exercicio3_FilaDuasPilhas<>();
        
        fila2.enqueue("A");
        fila2.enqueue("B");
        System.out.println("Desenfileirado: " + fila2.dequeue()); // A
        
        fila2.enqueue("C");
        fila2.enqueue("D");
        System.out.println("Desenfileirado: " + fila2.dequeue()); // B
        System.out.println("Desenfileirado: " + fila2.dequeue()); // C
        System.out.println("Desenfileirado: " + fila2.dequeue()); // D
        System.out.println("Esperado: A, B, C, D");
        System.out.println();
        
        // Análise de complexidade
        System.out.println("Análise de Complexidade:");
        System.out.println("- enqueue(): Θ(1) - apenas push na entrada");
        System.out.println("- dequeue():");
        System.out.println("  * Caso normal (saida não vazia): Θ(1)");
        System.out.println("  * Pior caso (saida vazia): Θ(n) - transfere todos");
        System.out.println("  * Amortizado: Θ(1) - cada elemento é transferido uma única vez");
        System.out.println();
        System.out.println("Padrões de uso ineficiente:");
        System.out.println("- Alternar constantemente entre enqueue/dequeue com fila vazia");
        System.out.println("  causa transferências repetidas (O(n) a cada dequeue)");
    }
}
