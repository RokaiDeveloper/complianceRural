package com.system.unipar.controller;

import com.system.unipar.model.Atividade;
import com.system.unipar.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/atividades")
@CrossOrigin(origins = "*")
public class AtividadeController {

    private static final Logger logger = Logger.getLogger(AtividadeController.class.getName());

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> create(@RequestBody Atividade atividade) {
        try {
            if (atividade == null) {
                return ResponseEntity.badRequest().build();
            }
            Atividade savedAtividade = atividadeService.save(atividade);
            if (savedAtividade != null) {
                return ResponseEntity.ok(savedAtividade);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao criar atividade", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> findAll() {
        try {
            List<Atividade> atividades = atividadeService.findAll();
            return ResponseEntity.ok(atividades);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar todas as atividades", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> findById(@PathVariable Long id) {
        try {
            Optional<Atividade> atividade = atividadeService.findById(id);
            return atividade.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar atividade por ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> update(@PathVariable Long id, @RequestBody Atividade atividade) {
        try {
            if (atividade == null) {
                return ResponseEntity.badRequest().build();
            }
            Atividade updatedAtividade = atividadeService.update(id, atividade);
            if (updatedAtividade != null) {
                return ResponseEntity.ok(updatedAtividade);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao atualizar atividade com ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            boolean deleted = atividadeService.deleteById(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao excluir atividade com ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
