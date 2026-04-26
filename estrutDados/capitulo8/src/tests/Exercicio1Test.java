import org.junit.jupiter.api.*;
import exercicios.Exercicio1_InversaoPilha;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercício 1: Inversão de Sequência com Pilha")
class Exercicio1Test {
    
    @Test
    @DisplayName("Inverter string normal")
    void testeInverterNormal() {
        assertEquals("MTFI", Exercicio1_InversaoPilha.inverter("IFTM"));
    }
    
    @Test
    @DisplayName("Inverter string vazia")
    void testeInverterVazia() {
        assertEquals("", Exercicio1_InversaoPilha.inverter(""));
    }
    
    @Test
    @DisplayName("Inverter string com um caractere")
    void testeInverterUmCaractere() {
        assertEquals("A", Exercicio1_InversaoPilha.inverter("A"));
    }
    
    @Test
    @DisplayName("Inverter frase completa")
    void testeInverterFrase() {
        String original = "Estrutura de Dados";
        String invertido = Exercicio1_InversaoPilha.inverter(original);
        assertEquals("sodaD ed aruturtE", invertido);
    }
    
    @Test
    @DisplayName("Inverter string com números")
    void testeInverterComNumeros() {
        assertEquals("4321", Exercicio1_InversaoPilha.inverter("1234"));
    }
    
    @Test
    @DisplayName("Inverter string com caracteres especiais")
    void testeInverterEspeciais() {
        assertEquals("!321@", Exercicio1_InversaoPilha.inverter("@123!"));
    }
    
    @Test
    @DisplayName("Dupla inversão retorna original")
    void testeDuplaInversao() {
        String original = "Pilha";
        String duplamenteInvertido = Exercicio1_InversaoPilha
            .inverter(Exercicio1_InversaoPilha.inverter(original));
        assertEquals(original, duplamenteInvertido);
    }
}
