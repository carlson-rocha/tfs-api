package com.rocha.repository;

import com.rocha.model.ClassificacaoTarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoTarefaRepository extends JpaRepository<ClassificacaoTarefa, Long> {

    public ClassificacaoTarefa findByCodigo(Long codigo);

    public Page<ClassificacaoTarefa> findByNomeLikeIgnoreCase(String nome, Pageable pageable);

}
