package com.rocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.model.Permissao;


public interface PermissaoRepository  extends JpaRepository<Permissao, Long>{

	public Permissao findByCodigo(Long codigo);
	
}
