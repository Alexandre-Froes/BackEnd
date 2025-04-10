package iftm.tspi.pbackorm.hello_world.dta;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroDTO {
    private String msg;
    private Integer idConflito;
    private LocalDateTime data;
}
