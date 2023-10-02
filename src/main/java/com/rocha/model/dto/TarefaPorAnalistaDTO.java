package com.rocha.model.dto;

import java.io.Serializable;

public class TarefaPorAnalistaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Long codigoAnalista;
    private String nomeAnalista;
    private boolean situacaoTarefaAberta;
    private Long quantidade;
    
    
    public TarefaPorAnalistaDTO() {
    	
    }
    
	public TarefaPorAnalistaDTO(Long codigoAnalista,
								String nomeAnalista, 
								boolean situacaoTarefaAberta,
								Long quantidade) {
		super();
		this.codigoAnalista 		= codigoAnalista;
		this.nomeAnalista 			= nomeAnalista;
		this.situacaoTarefaAberta 	= situacaoTarefaAberta;
		this.quantidade 			= quantidade;
				
	}

	public Long getCodigoAnalista() {
		return codigoAnalista;
	}

	public void setCodigoAnalista(Long codigoAnalista) {
		this.codigoAnalista = codigoAnalista;
	}

	public String getNomeAnalista() {
		return nomeAnalista;
	}

	public void setNomeAnalista(String nomeAnalista) {
		this.nomeAnalista = nomeAnalista;
	}

	public boolean isSituacaoTarefaAberta() {
		return situacaoTarefaAberta;
	}

	public void setSituacaoTarefaAberta(boolean situacaoTarefaAberta) {
		this.situacaoTarefaAberta = situacaoTarefaAberta;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAnalista == null) ? 0 : codigoAnalista.hashCode());
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
		TarefaPorAnalistaDTO other = (TarefaPorAnalistaDTO) obj;
		if (codigoAnalista == null) {
			if (other.codigoAnalista != null)
				return false;
		} else if (!codigoAnalista.equals(other.codigoAnalista))
			return false;
		return true;
	}
    
}
