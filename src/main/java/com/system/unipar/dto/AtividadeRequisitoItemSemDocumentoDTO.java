package com.system.unipar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeRequisitoItemSemDocumentoDTO {
    
    private Long id;
    
    private Long atividadeRequisitoId;
    
    private Date dataDocumento;
    
    private boolean checked;
    
    private String status;
    
    private Long relatorioId;
    
    private String observacao;
}
