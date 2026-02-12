package com.system.unipar.repository;

import com.system.unipar.model.AtividadeRequisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRequisitoRepository extends JpaRepository<AtividadeRequisito, Long> {
    
    List<AtividadeRequisito> findByAtividadeId(Long atividadeId);
    
    @Query("SELECT ar FROM AtividadeRequisito ar WHERE ar.atividadeId = :atividadeId AND ar.checkable = true")
    List<AtividadeRequisito> findByAtividadeIdAndCheckableTrue(@Param("atividadeId") Long atividadeId);
    
    @Query("SELECT ar FROM AtividadeRequisito ar WHERE ar.atividadeId = :atividadeId AND ar.dataDocumento = true")
    List<AtividadeRequisito> findByAtividadeIdAndDataDocumentoTrue(@Param("atividadeId") Long atividadeId);
    
    @Query("SELECT ar FROM AtividadeRequisito ar WHERE ar.atividadeId = :atividadeId AND ar.documentoUpload = true")
    List<AtividadeRequisito> findByAtividadeIdAndDocumentoUploadTrue(@Param("atividadeId") Long atividadeId);
}
