package com.rocha.repository;

import java.time.LocalDate;
import java.util.List;
import com.rocha.model.dto.TarefaPorAnalistaDTO;
import com.rocha.model.dto.TarefaPorPrioridadeDTO;
import com.rocha.model.dto.TarefaPorSituacaoDTO;


public interface DashboardRepositoryCustom {

	List<TarefaPorSituacaoDTO>  somaTarefasPorSituacaoAberta(Long codigoCategoria);
	
	List<TarefaPorSituacaoDTO>  somaTarefasPorSituacaoFechada(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim);

	List<TarefaPorPrioridadeDTO>  somaTarefasPorPrioridade(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim);

	List<TarefaPorAnalistaDTO>	somaTarefasPorAnalistaTecnico(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim);
	
	List<TarefaPorAnalistaDTO>	somaTarefasPorAnalistaNegocio(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim);

}
