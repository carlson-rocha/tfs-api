package com.rocha.repository;

import com.rocha.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByCodigo(Long codigo);

    public Page<Cliente> findByNomeLikeIgnoreCase(String nome, Pageable pageable);

    public Page<Cliente> findByLicencaLikeIgnoreCase(String licenca, Pageable pageable);

}
