package com.rocha.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	
	public Usuario findByCodigo(Long codigo);
	
	public Page<Usuario> findByNomeIsLike(String nome, Pageable pageable);
		
}
