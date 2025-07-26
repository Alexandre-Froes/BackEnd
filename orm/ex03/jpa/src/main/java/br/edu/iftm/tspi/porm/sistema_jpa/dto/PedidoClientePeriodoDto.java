package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoClientePeriodoDto {
    
    private String clienteId;
    private String clienteNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<PedidoResumoDto> pedidos;
    private BigDecimal valorTotalInvestido;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PedidoResumoDto {
        private Integer pedidoId;
        private LocalDate dataPedido;
        private List<ProdutoResumoDto> produtos;
        private BigDecimal valorTotalPedido;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProdutoResumoDto {
        private Integer produtoId;
        private String produtoNome;
        private Integer quantidade;
        private BigDecimal precoVenda;
        private BigDecimal desconto;
        private BigDecimal valorTotal;
    }
}
