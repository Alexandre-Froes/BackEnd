package edu.iftm.tspi.pbmo.xande.projeto_cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class CursoDTO {
    private String sigla;
    private String nome;
    private String desc;
}
