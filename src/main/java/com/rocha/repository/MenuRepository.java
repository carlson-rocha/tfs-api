package com.rocha.repository;

import com.rocha.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    public Menu findByCodigo(Long codigo);

    public Page<Menu> findByCaminhoLikeIgnoreCase(String caminho, Pageable pageable);

    public Page<Menu> findBySistemaLikeIgnoreCase(String sistema, Pageable pageable);

}
