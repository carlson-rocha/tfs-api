package com.rocha.model;

public enum TipoFuncao {

	NEGOCIO("Negocio"),
	TECNICO("Tecnico") ;
			
	
	private String descricao;
	
	TipoFuncao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
	
}
