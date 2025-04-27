package edu.iftm.tspi.pbmo.xande.projeto_cursos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class Curso {
    private String sigla;
    private String nome;
    private String desc;
}
