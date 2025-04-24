package edu.iftm.tspi.pbmo.xande.projeto_cursos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class Aluno {
    private String ra;
    private String nome;
    private String email;
    private Curso curso;
}
