package com.rocha.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario_permissao")
public class UsuarioPermissao {
	
	@EmbeddedId
	private UsuarioPermissaoId  id;

	public UsuarioPermissaoId getId() {
		return id;
	}

	public void setId(UsuarioPermissaoId id) {
		this.id = id;
	}
	
	
	
	
}
