package iftm.tspi.orm.xande.jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErroDTO {
    private String msg;
    private LocalDateTime data;
}
