package com.rocha.repository;

import com.rocha.model.Sistema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {

    public Sistema findById(Long id);

    public Page<Sistema> findByNomeLikeIgnoreCase(String nome, Pageable pageable);

    public Page<Sistema> findByNomeIgnoreCase(String nome, Pageable pageable);

}
