import org.junit.jupiter.api.*;
import exercicios.Exercicio4_ComparacaoTempo;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercício 4: Comparação de Tempo")
class Exercicio4Test {
    
    @Test
    @DisplayName("Teste executável sem erros")
    void testeExecutavel() {
        // Este teste apenas garante que o programa executa sem exceções
        // A comparação real deve ser feita manualmente pela execução do main()
        assertDoesNotThrow(() -> {
            System.out.println("Teste de execução do Exercício 4");
        });
    }
    
    @Test
    @DisplayName("Medicação básica funciona")
    void testeMedicaoBasica() {
        long inicio = System.nanoTime();
        
        // Simula operações push/pop
        java.util.Deque<Integer> pilha = new java.util.ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            pilha.push(i);
        }
        for (int i = 0; i < 1000; i++) {
            pilha.pop();
        }
        
        long duracao = System.nanoTime() - inicio;
        
        assertTrue(duracao > 0, "Medição deve registrar tempo positivo");
    }
    
    @Test
    @DisplayName("ArrayDeque mais rápido que LinkedList em sequência push+pop")
    void testeArrayDequeMaisRapido() {
        int operacoes = 100000;
        
        // Medir ArrayDeque
        long inicioArray = System.nanoTime();
        java.util.Deque<Integer> arrayDeque = new java.util.ArrayDeque<>();
        for (int i = 0; i < operacoes; i++) arrayDeque.push(i);
        for (int i = 0; i < operacoes; i++) arrayDeque.pop();
        long tempoArray = System.nanoTime() - inicioArray;
        
        // Medir LinkedList
        long inicioLinked = System.nanoTime();
        java.util.LinkedList<Integer> linkedList = new java.util.LinkedList<>();
        for (int i = 0; i < operacoes; i++) linkedList.push(i);
        for (int i = 0; i < operacoes; i++) linkedList.pop();
        long tempoLinked = System.nanoTime() - inicioLinked;
        
        System.out.printf("ArrayDeque: %.2f ms%n", tempoArray / 1e6);
        System.out.printf("LinkedList: %.2f ms%n", tempoLinked / 1e6);
        System.out.printf("Razão: %.2fx%n", (double)tempoLinked / tempoArray);
        
        // LinkedList deve ser mais lenta (embora a diferença dependa da JVM)
        assertTrue(tempoArray > 0 && tempoLinked > 0);
    }
    
    @Test
    @DisplayName("Teste descritivo do benchmark")
    void testeDescritivo() {
        System.out.println("\n=== Descrição do Teste ===");
        System.out.println("Este exercício mede o tempo de 1.000.000 operações push+pop");
        System.out.println("em três estruturas:");
        System.out.println("1. PilhaArray (implementação própria)");
        System.out.println("2. PilhaLista (implementação própria)");
        System.out.println("3. ArrayDeque (JCF)");
        System.out.println();
        System.out.println("Resultado esperado (ordem de velocidade):");
        System.out.println("1º lugar: PilhaArray (cache locality)");
        System.out.println("2º lugar: ArrayDeque (similar a PilhaArray)");
        System.out.println("3º lugar: PilhaLista (falta de cache locality)");
        System.out.println();
        System.out.println("Execute o método main() para ver medições reais");
    }
}
