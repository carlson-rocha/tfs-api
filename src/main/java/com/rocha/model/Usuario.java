package com.rocha.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable  {
	 private static final long serialVersionUID = 1L;
	 
	 
	@Id
	private Long 	codigo;
	
	private String	nome;
	private String	email;
	
	
	private String	senha;
	private String  telefone;
	
	@Column(name = "ic_sms")
	private String  utilizasms;
	
	private String  whatsapp;
	
	@Column(name = "ic_whatsapp")
	private String  utilizawhatsapp;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_funcao")
	private TipoFuncao funcao;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_permissao" , joinColumns= @JoinColumn(name="codigo_usuario") , inverseJoinColumns= @JoinColumn(name="codigo_permissao"))
	private List<Permissao> permissoes;

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

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUtilizasms() {
		return utilizasms;
	}

	public void setUtilizasms(String utilizasms) {
		this.utilizasms = utilizasms;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getUtilizawhatsapp() {
		return utilizawhatsapp;
	}
	
	
	public TipoFuncao getFuncao() {
		return funcao;
	}

	public void setUtilizawhatsapp(String utilizawhatsapp) {
		this.utilizawhatsapp = utilizawhatsapp;
	}

	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	
	public void setFuncao(TipoFuncao funcao) {
		this.funcao = funcao;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
