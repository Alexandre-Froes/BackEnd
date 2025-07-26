package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalhePedidoProdutoDto {

    private Integer numeroPedido;
    private Short quantidadeVendida;
    private Double valorTotalProduto;
    private Double valorTotalDesconto;
}
