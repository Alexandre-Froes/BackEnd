package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaConsumoDto {

    private Integer categoriaId;
    private String nomeCategoria;
    private Double valorTotalConsumido;
}
