package com.system.unipar.service;

import com.system.unipar.model.Relatorio;
import com.system.unipar.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public Relatorio cadastrar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public void deletar(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatório não encontrado com ID: " + id);
        }
        relatorioRepository.deleteById(id);
    }

    public Optional<Relatorio> buscarPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    public List<Relatorio> buscarTodos() {
        return relatorioRepository.findAll();
    }

    public List<Relatorio> buscarPorUsuarioId(Long usuarioId) {
        return relatorioRepository.findByUsuarioId(usuarioId);
    }

    public List<Relatorio> buscarPorAtividadeId(Long atividadeId) {
        return relatorioRepository.findByAtividadeId(atividadeId);
    }

    public List<Relatorio> buscarPorUsuarioIdEAtividadeId(Long usuarioId, Long atividadeId) {
        return relatorioRepository.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId);
    }

    public Relatorio atualizarStatus(Long id, String status) {
        Optional<Relatorio> relatorioOpt = relatorioRepository.findById(id);
        if (relatorioOpt.isEmpty()) {
            throw new RuntimeException("Relatório não encontrado com ID: " + id);
        }
        Relatorio relatorio = relatorioOpt.get();
        // Apenas atualiza o status, mantendo os outros campos intactos
        relatorio.setStatus(status);
        return relatorioRepository.save(relatorio);
    }
}
