package com.system.unipar.controller;

import com.system.unipar.dto.AtividadeDTO;
import com.system.unipar.model.Atividade;
import com.system.unipar.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public AtividadeDTO save(@RequestBody AtividadeDTO atividadeDTO) {
        if (atividadeDTO == null) {
            throw new IllegalArgumentException("AtividadeDTO cannot be null");
        }
        try {
            Atividade atividade = new Atividade();
            atividade.setNome(atividadeDTO.getNome());
            atividade.setDescricao(atividadeDTO.getDescricao());
            
            Atividade savedAtividade = atividadeService.save(atividade);
            
            return AtividadeDTO.builder()
                    .id(savedAtividade.getId())
                    .nome(savedAtividade.getNome())
                    .descricao(savedAtividade.getDescricao())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save atividade", e);
        }
    }

    @GetMapping
    public List<Atividade> findAll() {
        try {
            return atividadeService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find all atividades", e);
        }
    }

    @GetMapping("/{id}")
    public Atividade findById(@PathVariable Long id) {
        try {
            return atividadeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find atividade by id: " + id, e);
        }
    }

    @PutMapping("/{id}")
    public AtividadeDTO update(@PathVariable Long id, @RequestBody AtividadeDTO atividadeDTO) {
        if (atividadeDTO == null) {
            throw new IllegalArgumentException("AtividadeDTO cannot be null");
        }
        try {
            Atividade atividade = new Atividade();
            atividade.setNome(atividadeDTO.getNome());
            atividade.setDescricao(atividadeDTO.getDescricao());
            
            Atividade updatedAtividade = atividadeService.update(id, atividade);
            
            if (updatedAtividade == null) {
                throw new RuntimeException("Atividade não encontrada para atualização com ID: " + id);
            }
            
            return AtividadeDTO.builder()
                    .id(updatedAtividade.getId())
                    .nome(updatedAtividade.getNome())
                    .descricao(updatedAtividade.getDescricao())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update atividade with id: " + id, e);
        }
    }
}

