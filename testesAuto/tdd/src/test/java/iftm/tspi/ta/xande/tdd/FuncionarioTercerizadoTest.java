package iftm.tspi.ta.xande.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuncionarioTercerizadoTest {
    @Test
    public void testarFuncionarioTercerizadoConstrutorDespesasInvalido() {
        Double despensasAdicionaisInvalido = 10101010.10;
        String msgEsperada = "O valor para a despesas adicionais foi inválido. O valor tem que ser entre 0 e 1000.";

        FuncionarioTercerizado t1 = new FuncionarioTercerizado(despensasAdicionaisInvalido);
        Double saidaObtida = t1.getDespensasAdicionais();

        assertEquals(saidaObtida, msgEsperada);
    }
    
    @Test
    public void testarFuncionarioTercerizadoConstrutorDespesasMaiorValido() {
        Double despensasAdicionaisValido = 1000.00;
        Double saidaEsperada = despensasAdicionaisValido;

        FuncionarioTercerizado t1 = new FuncionarioTercerizado(despensasAdicionaisValido);
        Double saidaObtida = t1.getDespensasAdicionais();

        assertEquals(saidaObtida, saidaEsperada);
    }

    @Test
    public void testarFuncionarioTercerizadoConstrutorDespesasMenorValido() {
        Double despensasAdicionaisValido = 0.01;
        Double saidaEsperada = despensasAdicionaisValido;

        FuncionarioTercerizado t1 = new FuncionarioTercerizado(despensasAdicionaisValido);
        Double saidaObtida = t1.getDespensasAdicionais();

        assertEquals(saidaObtida, saidaEsperada);
    }

    @Test
    public void testarConstrutorEntradasValidas() {
        String nomeEsperado = "John Doe";
        Integer horasTrabalhadasEsperado = 20;    
        Double valorHoraEsperado = 14.5;    
        Double despesasAdicionaisEsperado = 999.99;

        FuncionarioTercerizado saidaObtida = new FuncionarioTercerizado(nomeEsperado, horasTrabalhadasEsperado, valorHoraEsperado, despesasAdicionaisEsperado);

        assertEquals(nomeEsperado, saidaObtida.getNome(), "Nome não corresponde");
        assertEquals(horasTrabalhadasEsperado, saidaObtida.getHorasTrabalhadas(), "Horas trabalhadas não corresponde");
        assertEquals(valorHoraEsperado, saidaObtida.getValorHora(), "Valor hora não corresponde");
        assertEquals(despesasAdicionaisEsperado, saidaObtida.getDespensasAdicionais(), "Despesas adicionais não corresponde");
    }

    @Test
    public void testarFuncionarioTercerizadoModificarDespensasAdicionaisEntradaValida() {
        Double despensasAdicionaisValido = 1.00;
        Double despensasAdicionaisOriginal = 10.00;
        Double saidaEsperada = despensasAdicionaisValido;

        FuncionarioTercerizado saidaObtida = new FuncionarioTercerizado(despensasAdicionaisOriginal);
        saidaObtida.setDespensasAdicionais(despensasAdicionaisValido);

        assertEquals(saidaObtida.getDespensasAdicionais(), saidaEsperada);
    }
    @Test
    public void testarFuncionarioTercerizadoModificarDespensasAdicionaisEntradaInvalida() {
        Double despensasAdicionaisInvalido = 1000.01;
        Double despensasAdicionaisOriginal = 10.00;
        String msgEsperada = "O valor para a despesas adicionais foi inválido. O valor tem que ser entre 0 e 1000.";

        FuncionarioTercerizado saidaObtida = new FuncionarioTercerizado(despensasAdicionaisOriginal);
        saidaObtida.setDespensasAdicionais(despensasAdicionaisInvalido);

        assertEquals(saidaObtida.getDespensasAdicionais(), msgEsperada);
    }
}