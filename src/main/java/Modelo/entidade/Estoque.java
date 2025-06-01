package Modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor 
@Builder
@Data
public class Estoque {
    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private Boolean estado; 
    private long usuariooId;
    private LocalDateTime DataCriacao;
}
