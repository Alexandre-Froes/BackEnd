package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalhePedidoDto {

    @NotNull(message = "ID do pedido é obrigatório")
    private Integer pedidoId;

    @NotNull(message = "ID do produto é obrigatório")
    private Integer produtoId;

    @NotNull(message = "Preço de venda é obrigatório")
    @Positive(message = "Preço de venda deve ser maior que 0")
    private Double precoVenda;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Short quantidade;

    @NotNull(message = "Desconto é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "Desconto não pode ser negativo")
    @DecimalMax(value = "1.0", inclusive = true, message = "Desconto não pode ser maior que 1 (100%)")
    private Double desconto;
}

