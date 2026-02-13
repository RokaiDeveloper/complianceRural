package com.system.unipar.controller;

import com.system.unipar.dto.AtividadeRequisitoItemDTO;
import com.system.unipar.model.AtividadeRequisitoItem;
import com.system.unipar.service.AtividadeRequisitoItemService;
import com.system.unipar.service.AtividadeRequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/atividade-requisito-item")
public class AtividadeRequisitoItemController {

    @Autowired
    private AtividadeRequisitoItemService atividadeRequisitoItemService;

    @Autowired
    private AtividadeRequisitoService atividadeRequisitoService;

    @PostMapping
    public AtividadeRequisitoItem save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("atividadeRequisitoId") Long atividadeRequisitoId,
            @RequestParam("relatorioId") Long relatorioId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "observacao", required = false) String observacao) {
        
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Arquivo não pode ser vazio");
            }

            // Validar se AtividadeRequisito existe
            if (!atividadeRequisitoService.existsById(atividadeRequisitoId)) {
                throw new IllegalArgumentException("Atividade Requisito não encontrado com ID: " + atividadeRequisitoId);
            }

            AtividadeRequisitoItem item = new AtividadeRequisitoItem();
            item.setAtividadeRequisitoId(atividadeRequisitoId);
            item.setRelatorioId(relatorioId);
            item.setDocumento(file.getBytes()); // Converte MultipartFile para byte[]
            item.setDataDocumento(new Date());
            item.setStatus(status != null ? status : "PENDENTE");
            item.setObservacao(observacao);
            item.setChecked(true);

            AtividadeRequisitoItem saved = atividadeRequisitoItemService.save(item);

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
            System.out.println("=== DEBUG: Buscando itens para relatorioId = " + relatorioId);
            List<AtividadeRequisitoItem> items = atividadeRequisitoItemService.findByRelatorioId(relatorioId);
            System.out.println("=== DEBUG: Encontrados " + items.size() + " itens");
            for (AtividadeRequisitoItem item : items) {
                System.out.println("=== DEBUG: Item ID=" + item.getId() + ", relatorioId=" + item.getRelatorioId());
            }
            return items;
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

    @GetMapping("/{id}/documento")
    public Map<String, Object> getDocumento(@PathVariable Long id) {
        try {
            AtividadeRequisitoItem item = atividadeRequisitoItemService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Atividade requisito item não encontrado com ID: " + id));
            
            byte[] documento = item.getDocumento();
            if (documento == null) {
                return Map.of("documento", (String) null);
            }
            
            // Converte byte[] para Base64
            String base64 = Base64.getEncoder().encodeToString(documento);
            return Map.of("documento", base64);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get documento for id: " + id, e);
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
                String documentoStr = (String) payload.get("documento");
                if (documentoStr != null && !documentoStr.isEmpty()) {
                    try {
                        // Remove prefixo Base64 se existir
                        String base64Clean = documentoStr.replaceFirst("^data:[^;]+;base64,", "");
                        existingItem.setDocumento(Base64.getDecoder().decode(base64Clean));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Formato de documento inválido. Use Base64 válido.");
                    }
                }
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
