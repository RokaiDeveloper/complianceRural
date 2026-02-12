package com.system.unipar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioDTO {
    private Long id;
    private Long usuarioId;
    private Long atividadeId;
    private String usuarioNome;
}
