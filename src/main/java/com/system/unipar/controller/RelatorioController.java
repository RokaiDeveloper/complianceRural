package com.system.unipar.controller;

import com.system.unipar.model.Relatorio;
import com.system.unipar.dto.RelatorioCompiladoDTO;
import com.system.unipar.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {

    private static final Logger logger = Logger.getLogger(RelatorioController.class.getName());

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<Relatorio>> findAll() {
        try {
            List<Relatorio> relatorios = relatorioService.findAll();
            return ResponseEntity.ok(relatorios);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar todos os relatórios", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> findById(@PathVariable Long id) {
        try {
            Optional<Relatorio> relatorio = relatorioService.findById(id);
            return relatorio.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar relatório por ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/compilar")
    public ResponseEntity<RelatorioCompiladoDTO> compilar(@PathVariable Long id) {
        try {
            RelatorioCompiladoDTO compilado = relatorioService.compilarRelatorio(id);
            if (compilado != null) {
                return ResponseEntity.ok(compilado);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao compilar relatório ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Relatorio> create(@RequestBody Relatorio relatorio) {
        try {
            if (relatorio == null) {
                return ResponseEntity.badRequest().build();
            }
            Relatorio savedRelatorio = relatorioService.createWithItems(relatorio);
            if (savedRelatorio != null) {
                return ResponseEntity.ok(savedRelatorio);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao criar relatório", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> update(@PathVariable Long id, @RequestBody Relatorio relatorio) {
        try {
            if (relatorio == null) {
                return ResponseEntity.badRequest().build();
            }
            Relatorio updatedRelatorio = relatorioService.update(id, relatorio);
            if (updatedRelatorio != null) {
                return ResponseEntity.ok(updatedRelatorio);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao atualizar relatório com ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            boolean deleted = relatorioService.deleteById(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao excluir relatório com ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Relatorio>> findByUsuarioId(@PathVariable Long usuarioId) {
        try {
            List<Relatorio> relatorios = relatorioService.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(relatorios);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar relatórios por usuário ID: " + usuarioId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/atividade/{atividadeId}")
    public ResponseEntity<List<Relatorio>> findByAtividadeId(@PathVariable Long atividadeId) {
        try {
            List<Relatorio> relatorios = relatorioService.findByAtividadeId(atividadeId);
            return ResponseEntity.ok(relatorios);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar relatórios por atividade ID: " + atividadeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/atividade/{atividadeId}")
    public ResponseEntity<List<Relatorio>> findByUsuarioIdAndAtividadeId(
            @PathVariable Long usuarioId, 
            @PathVariable Long atividadeId) {
        try {
            List<Relatorio> relatorios = relatorioService.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId);
            return ResponseEntity.ok(relatorios);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar relatórios por usuário ID e atividade ID: " + usuarioId + ", " + atividadeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
