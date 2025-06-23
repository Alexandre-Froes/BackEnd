package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @NotBlank(message = "ID é obrigatório")
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String cargo;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    private String cep;

    @NotBlank(message = "País é obrigatório")
    private String pais;

    private String telefone;

    private String fax;
}
