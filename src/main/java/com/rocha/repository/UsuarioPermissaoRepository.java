package com.rocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.model.UsuarioPermissao;
import com.rocha.model.UsuarioPermissaoId;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, UsuarioPermissaoId >{

		
}
