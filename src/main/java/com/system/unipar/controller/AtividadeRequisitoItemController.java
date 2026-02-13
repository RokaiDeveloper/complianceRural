package com.system.unipar.controller;

import com.system.unipar.dto.AtividadeRequisitoItemDTO;
import com.system.unipar.model.AtividadeRequisitoItem;
import com.system.unipar.service.AtividadeRequisitoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/atividade-requisito-item")
public class AtividadeRequisitoItemController {

    @Autowired
    private AtividadeRequisitoItemService atividadeRequisitoItemService;

    @PostMapping
    public AtividadeRequisitoItem save(@RequestBody AtividadeRequisitoItemDTO atividadeRequisitoItemDTO) {
        if (atividadeRequisitoItemDTO == null) {
            throw new IllegalArgumentException("AtividadeRequisitoItemDTO cannot be null");
        }

        try {
            AtividadeRequisitoItem atividadeRequisitoItem = getAtividadeRequisitoItem(atividadeRequisitoItemDTO);
            AtividadeRequisitoItem saved = atividadeRequisitoItemService.save(atividadeRequisitoItem);

            if (saved == null || saved.getId() == null) {
                throw new RuntimeException("Failed to save atividade requisito item - no ID returned");
            }

            return saved;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save atividade requisito item", e);
        }
    }

    @GetMapping("/relatorio/{relatorioId}")
    public List<AtividadeRequisitoItem> findByRelatorioId(@PathVariable Long relatorioId) {
        try {
            return atividadeRequisitoItemService.findByRelatorioId(relatorioId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find atividade requisito items by relatorio id: " + relatorioId, e);
        }
    }

    @GetMapping("/{id}")
    public AtividadeRequisitoItem findById(@PathVariable Long id) {
        try {
            return atividadeRequisitoItemService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Atividade requisito item não encontrado com ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find atividade requisito item by id: " + id, e);
        }
    }

    @PutMapping("/{id}")
    public AtividadeRequisitoItem update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null");
        }
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido fornecido: " + id);
        }

        try {
            AtividadeRequisitoItem existingItem = atividadeRequisitoItemService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Atividade requisito item não encontrado com ID: " + id));

            // Atualiza apenas os campos presentes no payload
            if (payload.containsKey("status")) {
                existingItem.setStatus((String) payload.get("status"));
            }
            if (payload.containsKey("checked")) {
                existingItem.setChecked((Boolean) payload.get("checked"));
            }
            if (payload.containsKey("dataDocumento")) {
                existingItem.setDataDocumento((Date) payload.get("dataDocumento"));
            }
            if (payload.containsKey("observacao")) {
                existingItem.setObservacao((String) payload.get("observacao"));
            }
            if (payload.containsKey("documento")) {
                existingItem.setDocumento((byte[]) payload.get("documento"));
            }

            AtividadeRequisitoItem updated = atividadeRequisitoItemService.save(existingItem);

            return updated;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update atividade requisito item with id: " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID inválido fornecido: " + id);
            }
            return atividadeRequisitoItemService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete atividade requisito item with id: " + id, e);
        }
    }

    private static AtividadeRequisitoItem getAtividadeRequisitoItem(AtividadeRequisitoItemDTO dto) {
        AtividadeRequisitoItem item = new AtividadeRequisitoItem();
        item.setAtividadeRequisitoId(dto.getAtividadeRequisitoId());
        item.setDocumento(dto.getDocumento());
        item.setDataDocumento(dto.getDataDocumento());
        item.setChecked(dto.isChecked());
        item.setStatus(dto.getStatus());
        item.setRelatorioId(dto.getRelatorioId());
        item.setObservacao(dto.getObservacao());
        return item;
    }
}
