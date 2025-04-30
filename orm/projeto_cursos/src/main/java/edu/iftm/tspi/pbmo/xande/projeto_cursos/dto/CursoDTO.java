package edu.iftm.tspi.pbmo.xande.projeto_cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private String sigla;
    private String nome;
    private String desc;
}
