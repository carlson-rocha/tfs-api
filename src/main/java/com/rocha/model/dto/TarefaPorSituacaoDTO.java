package com.rocha.model.dto;

import java.io.Serializable;

public class TarefaPorSituacaoDTO implements Serializable {
	private static final long serialVersionUID = 467280517085780812L;
	
    private Long codigo;
    private String nome;
    private Long quantidade;

    public TarefaPorSituacaoDTO() {
	} 
    
    
    public TarefaPorSituacaoDTO(Long codigo, String nome, Long quantidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
	}
    
    
	public Long getCodigo() {
        return this.codigo;
    }
    public void setCodigo(Long codigo) { this.codigo = codigo; }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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
		TarefaPorSituacaoDTO other = (TarefaPorSituacaoDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
    
}