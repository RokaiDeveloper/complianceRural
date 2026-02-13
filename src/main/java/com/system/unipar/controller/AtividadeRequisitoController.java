package com.system.unipar.controller;

import com.system.unipar.dto.AtividadeRequisitoDTO;
import com.system.unipar.model.Atividade;
import com.system.unipar.model.AtividadeRequisito;
import com.system.unipar.service.AtividadeRequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividade-requisitos")
public class AtividadeRequisitoController {

    @Autowired
    private AtividadeRequisitoService atividadeRequisitoService;

    @PostMapping
    public AtividadeRequisito save(@RequestBody AtividadeRequisitoDTO atividadeRequisitoDTO) {
        if (atividadeRequisitoDTO == null) {
            throw new RuntimeException("AtividadeRequisitoDTO cannot be null");
        }

        try {
            AtividadeRequisito atividadeRequisito = getAtividadeRequisito(atividadeRequisitoDTO);

            AtividadeRequisito saved = atividadeRequisitoService.save(atividadeRequisito);
            if (saved == null || saved.getId() == null) {
                throw new RuntimeException("Failed to save atividade requisito - no ID returned");
            }
            return saved;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save atividade requisito", e);
        }
    }

    @GetMapping
    public List<AtividadeRequisito> findAll() {
        try {
            return atividadeRequisitoService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find all atividade requisitos", e);
        }
    }

    @GetMapping("/{id}")
    public AtividadeRequisito findById(@PathVariable Long id) {
        try {
            return atividadeRequisitoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade requisito não encontrada com ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find atividade requisito by id: " + id, e);
        }
    }

    @GetMapping("/atividade/{atividadeId}")
    public List<AtividadeRequisito> findByAtividadeId(@PathVariable Long atividadeId) {
        try {
            return atividadeRequisitoService.findByAtividadeId(atividadeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find atividade requisitos by atividade id: " + atividadeId, e);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID inválido fornecido: " + id);
            }
            return atividadeRequisitoService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete atividade requisito with id: " + id, e);
        }
    }

    private static AtividadeRequisito getAtividadeRequisito(AtividadeRequisitoDTO atividadeRequisitoDTO) {
        AtividadeRequisito atividadeRequisito = new AtividadeRequisito();
        atividadeRequisito.setAtividadeId(atividadeRequisitoDTO.getAtividadeId());
        atividadeRequisito.setCheckable(atividadeRequisitoDTO.isCheckable());
        atividadeRequisito.setDataDocumento(atividadeRequisitoDTO.isDataDocumento());
        atividadeRequisito.setDocumentoUpload(atividadeRequisitoDTO.isDocumentoUpload());
        atividadeRequisito.setDescricaoRequisito(atividadeRequisitoDTO.getDescricaoRequisito());
        return atividadeRequisito;
    }
}
