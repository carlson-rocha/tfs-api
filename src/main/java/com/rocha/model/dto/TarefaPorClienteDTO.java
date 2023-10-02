package com.rocha.model.dto;

import java.io.Serializable;

public class TarefaPorClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Long codigoCliente;
    private String nomeCliente;
    private Long quantidadeAberta;
    private Long quantidadeFechada;
    
    
    public TarefaPorClienteDTO() {
    	
    }
    
	public TarefaPorClienteDTO(Long codigoCliente,
								String nomeCliente, 
								Long quantidadeAberta, 
								Long quantidadeFechada) {
		super();
		this.codigoCliente 			= codigoCliente;
		this.nomeCliente 			= nomeCliente;
		this.quantidadeAberta 		= quantidadeAberta;
		this.quantidadeFechada 		= quantidadeFechada;
				
	}

	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
		result = prime * result + ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
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
		TarefaPorClienteDTO other = (TarefaPorClienteDTO) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		return true;
	}
    
}
