package com.system.unipar.dto;

import com.system.unipar.model.AtividadeRequisitoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioCompiladoDTO {
    private Long id;
    private Long usuarioId;
    private Long atividadeId;
    private List<AtividadeRequisitoItem> itens;
    private boolean compilado;
    private List<String> pendencias;
}
