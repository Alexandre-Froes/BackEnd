package edu.iftm.tspi.pbmo.xande.projeto_cursos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErroDTO {
    private String msg;
    private LocalDateTime data;
}
