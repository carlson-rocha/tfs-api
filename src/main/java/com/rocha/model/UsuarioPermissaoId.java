package com.rocha.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UsuarioPermissaoId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo_usuario")
	private Long codigoUsuario;
	
	@Column(name="codigo_permissao")
	private Long codigoPermissao;

	public UsuarioPermissaoId() { 
		
	}
	
	public UsuarioPermissaoId(Long codigoUsuario, Long codigoPermissao) {
		this.codigoUsuario 		= codigoUsuario;
		this.codigoPermissao 	= codigoPermissao;
	}
	
	
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Long getCodigoPermissao() {
		return codigoPermissao;
	}

	public void setCodigoPermissao(Long codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPermissao == null) ? 0 : codigoPermissao.hashCode());
		result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPermissaoId other = (UsuarioPermissaoId) obj;
		if (codigoPermissao == null) {
			if (other.codigoPermissao != null)
				return false;
		} else if (!codigoPermissao.equals(other.codigoPermissao))
			return false;
		if (codigoUsuario == null) {
			if (other.codigoUsuario != null)
				return false;
		} else if (!codigoUsuario.equals(other.codigoUsuario))
			return false;
		return true;
	}

	
}
