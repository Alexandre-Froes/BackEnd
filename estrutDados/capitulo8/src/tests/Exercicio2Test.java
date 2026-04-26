import org.junit.jupiter.api.*;
import exercicios.Exercicio2_AvaliadosPosFixa;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercício 2: Avaliador de Expressões Pós-Fixas")
class Exercicio2Test {
    
    @Test
    @DisplayName("Adição simples: 3 4 +")
    void testeAdicaoSimples() {
        assertEquals(7.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("3 4 +"));
    }
    
    @Test
    @DisplayName("Subtração simples: 10 3 -")
    void testeSubtracaoSimples() {
        assertEquals(7.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("10 3 -"));
    }
    
    @Test
    @DisplayName("Multiplicação simples: 5 4 *")
    void testeMultiplicacaoSimples() {
        assertEquals(20.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("5 4 *"));
    }
    
    @Test
    @DisplayName("Divisão simples: 20 4 /")
    void testeDivisaoSimples() {
        assertEquals(5.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("20 4 /"));
    }
    
    @Test
    @DisplayName("Módulo simples: 10 3 %")
    void testeModuloSimples() {
        assertEquals(1.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("10 3 %"));
    }
    
    @Test
    @DisplayName("Expressão complexa: 5 1 2 + 4 * + 3 -")
    void testeExpressionComplexo() {
        // 5 + (1+2)*4 - 3 = 5 + 12 - 3 = 14
        assertEquals(14.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("5 1 2 + 4 * + 3 -"));
    }
    
    @Test
    @DisplayName("Expressão muito complexa: 15 7 1 1 + - / 3 * 2 1 1 + + -")
    void testeExpressionMuitoComplexo() {
        // (15 / (7-(1+1))) * 3 - (2+(1+1)) = (15 / 5) * 3 - 4 = 9 - 4 = 5
        assertEquals(5.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("15 7 1 1 + - / 3 * 2 1 1 + + -"));
    }
    
    @Test
    @DisplayName("Apenas um número")
    void testeUmNumero() {
        assertEquals(42.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("42"));
    }
    
    @Test
    @DisplayName("Número negativo")
    void testeNumeroNegativo() {
        assertEquals(-5.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("-5 0 +"));
    }
    
    @Test
    @DisplayName("Divisão por zero lança exceção")
    void testeDivisaoPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> 
            Exercicio2_AvaliadosPosFixa.avaliarPosFixa("5 0 /"));
    }
    
    @Test
    @DisplayName("Módulo por zero lança exceção")
    void testeModuloPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> 
            Exercicio2_AvaliadosPosFixa.avaliarPosFixa("5 0 %"));
    }
    
    @Test
    @DisplayName("Números decimais: 3.5 2.5 +")
    void testeDecimais() {
        assertEquals(6.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("3.5 2.5 +"));
    }
    
    @Test
    @DisplayName("Ordem de operações: 2 3 4 + *")
    void testeOrdenOperacoes() {
        // 2 * (3 + 4) = 2 * 7 = 14
        assertEquals(14.0, Exercicio2_AvaliadosPosFixa.avaliarPosFixa("2 3 4 + *"));
    }
}
