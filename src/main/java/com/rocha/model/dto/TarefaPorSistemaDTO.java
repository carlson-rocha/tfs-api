package com.rocha.model.dto;

import java.io.Serializable;

public class TarefaPorSistemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Long codigoSistema;
    private String nomeSistema;
    private Long quantidadeAberta;
    private Long quantidadeFechada;
    
    
    public TarefaPorSistemaDTO() {
    	
    }
    
	public TarefaPorSistemaDTO(Long codigoSistema,
								String nomeSistema, 
								Long quantidadeAberta, 
								Long quantidadeFechada) {
		super();
		this.codigoSistema 			= codigoSistema;
		this.nomeSistema 			= nomeSistema;
		this.quantidadeAberta 		= quantidadeAberta;
		this.quantidadeFechada 		= quantidadeFechada;
				
	}

	public Long getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(Long codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
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
		result = prime * result + ((codigoSistema == null) ? 0 : codigoSistema.hashCode());
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
		TarefaPorSistemaDTO other = (TarefaPorSistemaDTO) obj;
		if (codigoSistema == null) {
			if (other.codigoSistema != null)
				return false;
		} else if (!codigoSistema.equals(other.codigoSistema))
			return false;
		return true;
	}
    
}
