package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {
    
    private Integer id;

    @NotNull(message = "Data do pedido é obrigatória")
    private LocalDateTime dataPedido;

    @NotBlank(message = "ClienteID é obrigatório")
    private String clienteId;

    private List<DetalhePedidoDto> detalhesPedido;
}