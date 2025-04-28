package org.iftm.atividadea2.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.iftm.atividadea2.Calculadora;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    private static Calculadora calc;

    @BeforeAll
    public static void iniciarCalc() {
        System.out.println("Inicio Teste.");
    }

    @AfterAll
    public static void finalizarTestesGeral() {
        System.out.println("Fim do Teste.");
    }

    @BeforeEach
    public void configurarTeste() {
        System.out.println("Comecou!!!");
    }
    
    @AfterEach
    public void finalizarTeste() {
        System.out.println("Terminou!!!");
    }

    @Test
    public void testarConstrutorSemParametro() {
        calc = new Calculadora();
        int resultadoEsperado = 0;

        int resultadoObtido = calc.getMemoria();

        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarConstrutorComParametroDeTresPositivo() {
        calc = new Calculadora(3);
        int resultadoEsperado = 3;

        int resultadoObtido = calc.getMemoria();

        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarConstrutorComParametroDeTresNegativo() {
        calc = new Calculadora(-3);
        int resultadoEsperado = -3;

        int resultadoObtido = calc.getMemoria();

        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @BeforeEach
    public void constructorComTresPositivo() {
        calc = new Calculadora(3);
    }

    @Test
    public void testarSomarDoisNumerosPositivos() {
        // arange
        int valor = 3;
        int resultadoEsperado = 6;

        // act
        calc.somar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarSomarDoisNumerosPositivoENegativo() {
        // arange
        int valor = -3;
        int resultadoEsperado = 0;

        // act
        calc.somar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarSubtrairDoisNumerosPositivos() {
        // arange
        int valor = 3;
        int resultadoEsperado = 0;

        // act
        calc.subtrair(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarSubtrairDoisNumerosPositivoENegativo() {
        // arange
        int valor = -3;
        int resultadoEsperado = 6;

        // act
        calc.subtrair(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarMultiplicarDoisNumerosPositivos() {
        // arange
        int valor = 3;
        int resultadoEsperado = 9;

        // act
        calc.multiplicar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarMultiplicarDoisNumerosPositivoENegativo() {
        // arange
        int valor = -3;
        int resultadoEsperado = -9;

        // act
        calc.multiplicar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarDividirDoisNumerosPositivos() throws Exception{
        // arange
        int valor = 3;
        int resultadoEsperado = 1;

        // act
        calc.dividir(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarDividirDoisNumerosPositivoENegativo() throws Exception {
        // arange
        int valor = -3;
        int resultadoEsperado = -1;

        // act
        calc.dividir(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarDivisaoPorZeroGeraErro() {
        int valor = 0;
        String resultadoEsperado = "Divisão por zero!!!";

        Exception resultadoObtido = assertThrows(Exception.class, 
        () -> {
                calc.dividir(valor);
            });

        assertEquals(resultadoEsperado, resultadoObtido.getMessage());
    }

    @Test
    public void testarExponenciarNumeroTresPorUm() throws Exception {
        // arange
        int valor = 1;
        int resultadoEsperado = 3;

        // act
        calc.exponenciar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarExponenciarNumeroTresPorDez() throws Exception {
        // arange
        int valor = 10;
        int resultadoEsperado = 59049;

        // act
        calc.exponenciar(valor);
        int resultadoObtido = calc.getMemoria();

        // assign
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testarExponenciarNumeroTresPorVinte() {
        // arange
        int valor = 20;
        String resultadoEsperado = "Expoente incorreto, valor máximo é 10.";


        // act
        Exception resultadoObtido = assertThrows(Exception.class, 
        () -> {
                calc.exponenciar(valor);
            });

        // assign
        assertEquals(resultadoEsperado, resultadoObtido.getMessage());
    }

    @Test
    public void testarZerarMemoria() {
        // arange
        int resultadoEsperado = 0;

        // act
        calc.zerarMemoria();
        int resultadoObtido = calc.getMemoria();

        // assing
        assertEquals(resultadoEsperado, resultadoObtido);
    }
}
