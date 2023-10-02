package com.rocha.repository;

import com.rocha.model.CategoriaTarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaTarefaRepository extends JpaRepository<CategoriaTarefa, Long> {

    public CategoriaTarefa findByCodigo(Long codigo);

    public Page<CategoriaTarefa> findByNomeLikeIgnoreCase(String nome, Pageable pageable);

}
