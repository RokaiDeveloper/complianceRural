package com.system.unipar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeRequisitoDTO {
    
    private Long id;
    
    private Long atividadeId;
    
    private boolean checkable;
    
    private boolean dataDocumento;
    
    private boolean documentoUpload;

    private String descricaoRequisito;
}
