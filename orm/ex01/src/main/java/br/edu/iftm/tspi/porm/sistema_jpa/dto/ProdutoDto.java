package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import br.edu.iftm.tspi.porm.sistema_jpa.annotation.CategoriaExists;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDto {

    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior do que 0")
    private Double preco;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Não existe estoque negativo")
    private Integer estoque;

    private String caminhoImagem;

    @NotNull(message = "Categoria é obrigatória")
    @CategoriaExists(message = "Não existe categoria com o ID informado")
    private Integer categoriaId;
}
