package edu.iftm.tspi.pbmo.xande.projeto_cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class AlunoDTO {
    private String ra;
    private String nome;
    private String email;
    private String cursoSigla;
}
