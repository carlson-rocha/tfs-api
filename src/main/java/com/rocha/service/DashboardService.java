package com.rocha.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocha.model.dto.TarefaPorAnalistaDTO;
import com.rocha.model.dto.TarefaPorClienteDTO;
import com.rocha.model.dto.TarefaPorPrioridadeDTO;
import com.rocha.model.dto.TarefaPorSistemaDTO;
import com.rocha.model.dto.TarefaPorSituacaoDTO;
import com.rocha.repository.DashboardRepositoryCustomImpl;

@Service
public class DashboardService {

	@Autowired
	private DashboardRepositoryCustomImpl dashboardRepositoryCustomImpl;
	
	//--- Buscar Tarefa por Situação Aberta ----//
	public List<TarefaPorSituacaoDTO> somaTarefasPorSituacaoAberta(Long codigoCategoria) {
		return dashboardRepositoryCustomImpl.somaTarefasPorSituacaoAberta(codigoCategoria);
	}

	//--- Buscar Tarefa por Situação Aberta ----//
	public List<TarefaPorSituacaoDTO> somaTarefasPorSituacaoFechada(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim) {
		return dashboardRepositoryCustomImpl.somaTarefasPorSituacaoFechada(codigoCategoria, dtInicio, dtFim);
	}

	public List<TarefaPorPrioridadeDTO> somaTarefasPorPrioridade(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim) {
		return dashboardRepositoryCustomImpl.somaTarefasPorPrioridade(codigoCategoria, dtInicio, dtFim);
	}

	//--- Buscar Tarefa por Analista Tecnico no Periodo ----//
	public List<TarefaPorAnalistaDTO> somaTarefasPorAnalistaTecnico(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim){
		return dashboardRepositoryCustomImpl.somaTarefasPorAnalistaTecnico(codigoCategoria, dtInicio, dtFim);
	}

	//--- Buscar Tarefa por Analista Tecnico no Periodo ----//
	public List<TarefaPorAnalistaDTO> somaTarefasPorAnalistaNegocio(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim){
		return dashboardRepositoryCustomImpl.somaTarefasPorAnalistaNegocio( codigoCategoria, dtInicio, dtFim);
	}

	//--- Buscar Tarefa por Cliente no Periodo ----//
	public List<TarefaPorClienteDTO> somaTarefasPorCliente(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim){
		return dashboardRepositoryCustomImpl.somaTarefasPorCliente( codigoCategoria, dtInicio, dtFim);
	}

	//--- Buscar Tarefa por Sistema no Periodo ----//
	public List<TarefaPorSistemaDTO> somaTarefasPorSistema(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim){
		return dashboardRepositoryCustomImpl.somaTarefasPorSistema( codigoCategoria, dtInicio, dtFim);
	}
	
}
