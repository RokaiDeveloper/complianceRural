package com.system.unipar.controller;

import com.system.unipar.model.AtividadeRequisito;
import com.system.unipar.service.AtividadeRequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividade-requisitos")
@CrossOrigin(origins = "*")
public class AtividadeRequisitoController {

    @Autowired
    private AtividadeRequisitoService atividadeRequisitoService;

    @GetMapping
    public ResponseEntity<List<AtividadeRequisito>> findAll() {
        List<AtividadeRequisito> requisitos = atividadeRequisitoService.findAll();
        return ResponseEntity.ok(requisitos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeRequisito> findById(@PathVariable Long id) {
        Optional<AtividadeRequisito> requisito = atividadeRequisitoService.findById(id);
        return requisito.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeRequisito> create(@RequestBody AtividadeRequisito atividadeRequisito) {
        AtividadeRequisito savedRequisito = atividadeRequisitoService.save(atividadeRequisito);
        return ResponseEntity.ok(savedRequisito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeRequisito> update(@PathVariable Long id, @RequestBody AtividadeRequisito atividadeRequisito) {
        AtividadeRequisito updatedRequisito = atividadeRequisitoService.update(id, atividadeRequisito);
        if (updatedRequisito != null) {
            return ResponseEntity.ok(updatedRequisito);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            if (atividadeRequisitoService.existsById(id)) {
                atividadeRequisitoService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/atividade/{atividadeId}")
    public ResponseEntity<List<AtividadeRequisito>> findByAtividadeId(@PathVariable Long atividadeId) {
        List<AtividadeRequisito> requisitos = atividadeRequisitoService.findByAtividadeId(atividadeId);
        return ResponseEntity.ok(requisitos);
    }

    @GetMapping("/atividade/{atividadeId}/checkable")
    public ResponseEntity<List<AtividadeRequisito>> findByAtividadeIdAndCheckableTrue(@PathVariable Long atividadeId) {
        List<AtividadeRequisito> requisitos = atividadeRequisitoService.findByAtividadeIdAndCheckableTrue(atividadeId);
        return ResponseEntity.ok(requisitos);
    }

    @GetMapping("/atividade/{atividadeId}/data-documento")
    public ResponseEntity<List<AtividadeRequisito>> findByAtividadeIdAndDataDocumentoTrue(@PathVariable Long atividadeId) {
        List<AtividadeRequisito> requisitos = atividadeRequisitoService.findByAtividadeIdAndDataDocumentoTrue(atividadeId);
        return ResponseEntity.ok(requisitos);
    }

    @GetMapping("/atividade/{atividadeId}/documento-upload")
    public ResponseEntity<List<AtividadeRequisito>> findByAtividadeIdAndDocumentoUploadTrue(@PathVariable Long atividadeId) {
        List<AtividadeRequisito> requisitos = atividadeRequisitoService.findByAtividadeIdAndDocumentoUploadTrue(atividadeId);
        return ResponseEntity.ok(requisitos);
    }
}
