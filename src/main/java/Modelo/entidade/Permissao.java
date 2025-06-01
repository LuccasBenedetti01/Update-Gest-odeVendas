package Modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.annotation.AliasFor;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {
        private Long id; 
    private String nome;
    private String descricao;
}
