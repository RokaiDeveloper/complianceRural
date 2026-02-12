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
public class AtividadeRequisitoItemDTO {
    private Long id;
    private Long atividadeRequisitoId;
    private byte[] documento;
    private Date dataDocumento;
    private boolean check;
    private String status;
    private boolean aprovado;
    private Long relatorioId;
    private String observacao;
}
