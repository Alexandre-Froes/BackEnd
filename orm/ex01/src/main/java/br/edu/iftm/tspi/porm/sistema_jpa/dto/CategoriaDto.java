package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import java.util.List;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Integer id;

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;

    private String descricao;
    
    private List<Produto> produtos;
}
