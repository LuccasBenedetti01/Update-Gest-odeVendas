package Modelo.entidade;

import java.math.BigDecimal; 
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Data
public class Produto {
    private Long id;
    private String nome;
    private String Descricao;
    private Integer quantidade;
    private BigDecimal preco;
    private Long usuarioId;
    private Long categoriaId;
    private LocalDateTime dataCriacao;
}
