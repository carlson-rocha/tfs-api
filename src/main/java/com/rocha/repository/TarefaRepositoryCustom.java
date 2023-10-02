package com.rocha.repository;

import java.util.List;

import com.rocha.model.Tarefa;
import com.rocha.model.dto.FiltroTarefaDTO;


public interface TarefaRepositoryCustom {

	List<Tarefa> retornaTarefaFiltro(FiltroTarefaDTO filtro);
}
