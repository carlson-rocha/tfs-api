package com.rocha.model;

import java.util.HashMap;
import java.util.Map;

public enum Prioridade {

    NORMAL("Normal"),
    ALTA   ("Alta"),
    URGENTE("Urgente"),
    CRITICO("Crítico");
	
	private String descricao;
	
	Prioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
 
    // "dictionary" para guardar os dados
    private static Map<String, Prioridade> prioridadePeloTexto = null;

    static {
        prioridadePeloTexto = new HashMap<>();

        // guarda os dados por Long
        for(Prioridade type : Prioridade.values()){
            String d = type.getDescricao();
            prioridadePeloTexto.put(d,type);
        }
    }

    // pesquisa pelo nome
    public static Prioridade peloNome(String name){
        Prioridade result = prioridadePeloTexto.get(name);
        return result;
    }

}