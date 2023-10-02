package com.rocha.model.dto;

import com.rocha.model.Usuario;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long 	codigo;
	private String	nome;
	private String	email;
	
		
	public UsuarioDTO(Long codigo, String nome, String email) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
	}

	public UsuarioDTO(Usuario usuario) {
		super();
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		UsuarioDTO other = (UsuarioDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
