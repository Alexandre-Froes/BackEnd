import org.junit.jupiter.api.*;
import exercicios.Exercicio3_FilaDuasPilhas;
import estruturas.Fila;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercício 3: Fila com Duas Pilhas")
class Exercicio3Test {
    
    private Fila<Integer> fila;
    
    @BeforeEach
    void setUp() {
        fila = new Exercicio3_FilaDuasPilhas<>();
    }
    
    @Test
    @DisplayName("Fila recém-criada deve estar vazia")
    void testeFilaVazia() {
        assertTrue(fila.isEmpty());
        assertEquals(0, fila.size());
    }
    
    @Test
    @DisplayName("Enqueue e dequeue devem seguir FIFO")
    void testeEnqueueDequeuefifo() {
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        
        assertEquals(1, fila.dequeue());
        assertEquals(2, fila.dequeue());
        assertEquals(3, fila.dequeue());
        assertTrue(fila.isEmpty());
    }
    
    @Test
    @DisplayName("Peek não deve remover o elemento")
    void testePeekNaoRemove() {
        fila.enqueue(99);
        assertEquals(99, fila.peek());
        assertEquals(1, fila.size());
        assertEquals(99, fila.dequeue());
        assertTrue(fila.isEmpty());
    }
    
    @Test
    @DisplayName("Size deve retornar quantidade correta")
    void testeSize() {
        assertEquals(0, fila.size());
        fila.enqueue(1);
        assertEquals(1, fila.size());
        fila.enqueue(2);
        assertEquals(2, fila.size());
        fila.dequeue();
        assertEquals(1, fila.size());
    }
    
    @Test
    @DisplayName("Dequeue em fila vazia deve lançar exceção")
    void testeDequeueEmVazia() {
        assertThrows(NoSuchElementException.class, () -> fila.dequeue());
    }
    
    @Test
    @DisplayName("Peek em fila vazia deve lançar exceção")
    void testePeekEmVazia() {
        assertThrows(NoSuchElementException.class, () -> fila.peek());
    }
    
    @Test
    @DisplayName("Fila com um único elemento")
    void testeUnicoElemento() {
        fila.enqueue(42);
        assertFalse(fila.isEmpty());
        assertEquals(42, fila.peek());
        assertEquals(42, fila.dequeue());
        assertTrue(fila.isEmpty());
    }
    
    @Test
    @DisplayName("Interleaving de enqueue e dequeue")
    void testeInterleaving() {
        fila.enqueue("A");
        fila.enqueue("B");
        assertEquals("A", fila.dequeue());
        
        fila.enqueue("C");
        fila.enqueue("D");
        assertEquals("B", fila.dequeue());
        assertEquals("C", fila.dequeue());
        assertEquals("D", fila.dequeue());
    }
    
    @Test
    @DisplayName("Grande volume de operações")
    void testeGrandeVolume() {
        int n = 10000;
        for (int i = 0; i < n; i++) {
            fila.enqueue(i);
        }
        
        assertEquals(n, fila.size());
        
        for (int i = 0; i < n; i++) {
            assertEquals(i, fila.dequeue());
        }
        
        assertTrue(fila.isEmpty());
    }
    
    @Test
    @DisplayName("Tipo genérico com String")
    void testeString() {
        Fila<String> filaString = new Exercicio3_FilaDuasPilhas<>();
        
        filaString.enqueue("primeiro");
        filaString.enqueue("segundo");
        filaString.enqueue("terceiro");
        
        assertEquals("primeiro", filaString.dequeue());
        assertEquals("segundo", filaString.dequeue());
        assertEquals("terceiro", filaString.dequeue());
    }
    
    @Test
    @DisplayName("Múltiplos ciclos de enqueue/dequeue")
    void testeMultiplosCiclos() {
        for (int ciclo = 0; ciclo < 5; ciclo++) {
            fila.enqueue(ciclo * 10);
            fila.enqueue(ciclo * 10 + 1);
            
            assertEquals(ciclo * 10, fila.dequeue());
            assertEquals(ciclo * 10 + 1, fila.dequeue());
            assertTrue(fila.isEmpty());
        }
    }
}
