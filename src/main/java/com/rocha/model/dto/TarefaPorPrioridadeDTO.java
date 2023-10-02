package com.rocha.model.dto;

import java.io.Serializable;

public class TarefaPorPrioridadeDTO implements Serializable {
    private static final long serialVersionUID = 8408218847072200023L;

    private String nome;
    private Long quantidadeAberta;
    private Long quantidadeFechada;

    public TarefaPorPrioridadeDTO() {
    }

    public TarefaPorPrioridadeDTO(String nome, Long quantidadeAberta, Long quantidadeFechada) {
        this.nome 				= nome;
        this.quantidadeAberta  = quantidadeAberta;
        this.quantidadeFechada = quantidadeFechada;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidadeAberta() {
		return quantidadeAberta;
	}

	public void setQuantidadeAberta(Long quantidadeAberta) {
		this.quantidadeAberta = quantidadeAberta;
	}

	public Long getQuantidadeFechada() {
		return quantidadeFechada;
	}

	public void setQuantidadeFechada(Long quantidadeFechada) {
		this.quantidadeFechada = quantidadeFechada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		TarefaPorPrioridadeDTO other = (TarefaPorPrioridadeDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

  
  
}
