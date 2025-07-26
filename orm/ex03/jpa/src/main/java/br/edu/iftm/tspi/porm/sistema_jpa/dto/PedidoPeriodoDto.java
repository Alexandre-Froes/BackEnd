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
public class PedidoPeriodoDto {
    private Integer pedidoId;
    private String clienteId;
    private String clienteNome;
    private LocalDate dataPedido;
    private BigDecimal valorTotalPedido;
    private List<ProdutoPedidoDto> produtos;
}
