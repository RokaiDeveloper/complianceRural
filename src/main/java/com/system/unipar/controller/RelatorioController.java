package com.system.unipar.controller;

import com.system.unipar.model.Relatorio;
import com.system.unipar.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<Relatorio> cadastrar(@RequestBody Relatorio relatorio) {
        Relatorio salvo = relatorioService.cadastrar(relatorio);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarPorId(@PathVariable Long id) {
        Optional<Relatorio> relatorio = relatorioService.buscarPorId(id);
        return relatorio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Relatorio>> buscarTodos() {
        List<Relatorio> relatorios = relatorioService.buscarTodos();
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Relatorio>> buscarPorUsuarioId(@PathVariable Long usuarioId) {
        List<Relatorio> relatorios = relatorioService.buscarPorUsuarioId(usuarioId);
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/atividade/{atividadeId}")
    public ResponseEntity<List<Relatorio>> buscarPorAtividadeId(@PathVariable Long atividadeId) {
        List<Relatorio> relatorios = relatorioService.buscarPorAtividadeId(atividadeId);
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/usuario/{usuarioId}/atividade/{atividadeId}")
    public ResponseEntity<List<Relatorio>> buscarPorUsuarioIdEAtividadeId(
            @PathVariable Long usuarioId, 
            @PathVariable Long atividadeId) {
        List<Relatorio> relatorios = relatorioService.buscarPorUsuarioIdEAtividadeId(usuarioId, atividadeId);
        return ResponseEntity.ok(relatorios);
    }
}
