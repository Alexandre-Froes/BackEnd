package edu.iftm.tspi.pbmo.xande.projeto_cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private String ra;
    private String nome;
    private String email;
    private String cursoSigla;
}
