package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPedidoDto {
    private Integer produtoId;
    private String produtoNome;
    private BigDecimal precoVenda;
    private BigDecimal quantidade;
    private BigDecimal valorTotalProduto;
}
