package com.system.unipar.repository;

import com.system.unipar.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    
    List<Relatorio> findByUsuarioId(Long usuarioId);
    
    List<Relatorio> findByAtividadeId(Long atividadeId);
    
    @Query("SELECT r FROM Relatorio r WHERE r.usuarioId = :usuarioId AND r.atividadeId = :atividadeId")
    List<Relatorio> findByUsuarioIdAndAtividadeId(@Param("usuarioId") Long usuarioId,
                                                  @Param("atividadeId") Long atividadeId);
}
