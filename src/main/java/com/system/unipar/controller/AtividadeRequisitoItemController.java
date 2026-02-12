package com.system.unipar.controller;

import com.system.unipar.model.AtividadeRequisitoItem;
import com.system.unipar.service.AtividadeRequisitoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividade-requisito-itens")
@CrossOrigin(origins = "*")
public class AtividadeRequisitoItemController {

    @Autowired
    private AtividadeRequisitoItemService atividadeRequisitoItemService;

    @GetMapping
    public ResponseEntity<List<AtividadeRequisitoItem>> findAll() {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findAll();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeRequisitoItem> findById(@PathVariable Long id) {
        Optional<AtividadeRequisitoItem> item = atividadeRequisitoItemService.findById(id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeRequisitoItem> create(@RequestBody AtividadeRequisitoItem item) {
        try {
            AtividadeRequisitoItem savedItem = atividadeRequisitoItemService.save(item);
            return ResponseEntity.ok(savedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeRequisitoItem> update(@PathVariable Long id, @RequestBody AtividadeRequisitoItem item) {
        try {
            AtividadeRequisitoItem updatedItem = atividadeRequisitoItemService.update(id, item);
            if (updatedItem != null) {
                return ResponseEntity.ok(updatedItem);
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (atividadeRequisitoItemService.existsById(id)) {
            atividadeRequisitoItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/atividade-requisito/{atividadeRequisitoId}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByAtividadeRequisitoId(@PathVariable Long atividadeRequisitoId) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByAtividadeRequisitoId(atividadeRequisitoId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/relatorio/{relatorioId}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByRelatorioId(@PathVariable Long relatorioId) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByRelatorioId(relatorioId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByStatus(@PathVariable String status) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByStatus(status);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/aprovado/{aprovado}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByAprovado(@PathVariable boolean aprovado) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByAprovado(aprovado);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/atividade-requisito/{atividadeRequisitoId}/relatorio/{relatorioId}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByAtividadeRequisitoIdAndRelatorioId(
            @PathVariable Long atividadeRequisitoId, 
            @PathVariable Long relatorioId) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByAtividadeRequisitoIdAndRelatorioId(atividadeRequisitoId, relatorioId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/relatorio/{relatorioId}/aprovado/{aprovado}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByRelatorioIdAndAprovado(
            @PathVariable Long relatorioId, 
            @PathVariable boolean aprovado) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByRelatorioIdAndAprovado(relatorioId, aprovado);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/atividade/{atividadeId}")
    public ResponseEntity<List<AtividadeRequisitoItem>> findByAtividadeId(@PathVariable Long atividadeId) {
        List<AtividadeRequisitoItem> itens = atividadeRequisitoItemService.findByAtividadeId(atividadeId);
        return ResponseEntity.ok(itens);
    }
}
