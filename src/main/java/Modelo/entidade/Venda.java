package Modelo.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venda {
    private Long id;
    private BigDecimal totalVenda;
    private BigDecimal valorPago;
    private BigDecimal desconto;
    private BigDecimal troco;
    private Long clienyeId;
    private Long usuarioId;
    private LocalDateTime dataCriacao; 
    private LocalDateTime dataAlteracao;
}
