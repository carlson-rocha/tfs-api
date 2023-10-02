package com.rocha.repository;

import com.rocha.model.SituacaoTarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoTarefaRepository extends JpaRepository<SituacaoTarefa, Long> {

    public SituacaoTarefa findByCodigo(Long codigo);

    public Page<SituacaoTarefa> findByNomeLikeIgnoreCase(String nome, Pageable pageable);

}
