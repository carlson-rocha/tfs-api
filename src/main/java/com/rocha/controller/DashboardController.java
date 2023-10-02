package com.rocha.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.model.dto.TarefaPorAnalistaDTO;
import com.rocha.model.dto.TarefaPorClienteDTO;
import com.rocha.model.dto.TarefaPorPrioridadeDTO;
import com.rocha.model.dto.TarefaPorSistemaDTO;
import com.rocha.model.dto.TarefaPorSituacaoDTO;
import com.rocha.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

		@Autowired
		private DashboardService dashboardService;

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas por Situação em Aberto -------------//
		@GetMapping("/tarefasporsituacaoaberta")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorSituacaoDTO>> buscarSituacaoTarefaAberto(@RequestParam(name = "codigoCategoria") Long codigoCategoria) {

			List<TarefaPorSituacaoDTO> indicadorDaTarefa = dashboardService.somaTarefasPorSituacaoAberta(codigoCategoria);
						
			return indicadorDaTarefa != null ? ResponseEntity.ok(indicadorDaTarefa) : ResponseEntity.notFound().build();
		}

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas por Situação Fechada -------------//
		@GetMapping("/tarefasporsituacaofechada")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorSituacaoDTO>> buscarSituacaoTarefaFechada(@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																					  @RequestParam(name = "dtInicio") String dataInicio,
																					  @RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);

			List<TarefaPorSituacaoDTO> indicadorDaTarefa = dashboardService.somaTarefasPorSituacaoFechada(codigoCategoria, dtInicio, dtFim);
						
			return indicadorDaTarefa != null ? ResponseEntity.ok(indicadorDaTarefa) : ResponseEntity.notFound().build();
		}

		@GetMapping("/tarefasporprioridade")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorPrioridadeDTO>> buscarTarefasPorPrioridade (@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																						@RequestParam(name = "dtInicio") String dataInicio,
																						@RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);

			List<TarefaPorPrioridadeDTO> indicadorDaTarefa = dashboardService.somaTarefasPorPrioridade(codigoCategoria, dtInicio, dtFim);

			return indicadorDaTarefa != null ? ResponseEntity.ok(indicadorDaTarefa) : ResponseEntity.notFound().build();
		}

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas Aberta e Fechada por Analista Tecnico -------------//
		@GetMapping("/tarefasporanalistatecnico")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorAnalistaDTO>> buscarTarefaAnalistaTecnico(@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																					  @RequestParam(name = "dtInicio") String dataInicio,
				 																	  @RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);
			
			List<TarefaPorAnalistaDTO> tarefaPorAnalistaDTO = dashboardService.somaTarefasPorAnalistaTecnico(codigoCategoria, dtInicio, dtFim);
						
			return tarefaPorAnalistaDTO != null ? ResponseEntity.ok(tarefaPorAnalistaDTO) : ResponseEntity.notFound().build();
		}	

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas Aberta e Fechada por Analista Negocio -------------//
		@GetMapping("/tarefasporanalistanegocio")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorAnalistaDTO>> buscarTarefaAnalistaNegocio(@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																					  @RequestParam(name = "dtInicio") String dataInicio,
				 																	  @RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);
			
			List<TarefaPorAnalistaDTO> tarefaPorAnalistaDTO = dashboardService.somaTarefasPorAnalistaNegocio(codigoCategoria, dtInicio, dtFim);
						
			return tarefaPorAnalistaDTO != null ? ResponseEntity.ok(tarefaPorAnalistaDTO) : ResponseEntity.notFound().build();
		}		

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas Aberta e Fechada por Cliente -------------//
		@GetMapping("/tarefasporcliente")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorClienteDTO>> buscarTarefaCliente(@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																			  @RequestParam(name = "dtInicio") String dataInicio,
				 															  @RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);
			
			List<TarefaPorClienteDTO> tarefaPorClienteDTO = dashboardService.somaTarefasPorCliente(codigoCategoria, dtInicio, dtFim);
						
			return tarefaPorClienteDTO != null ? ResponseEntity.ok(tarefaPorClienteDTO) : ResponseEntity.notFound().build();
		}			

		//----- Disponibiliza para o Gráfico de Quantidade de Tarefas Aberta e Fechada por Sistema -------------//
		@GetMapping("/tarefasporsistema")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
		public ResponseEntity<List<TarefaPorSistemaDTO>> buscarTarefaSistema(@RequestParam(name = "codigoCategoria") Long codigoCategoria,
																			  @RequestParam(name = "dtInicio") String dataInicio,
				 															  @RequestParam(name = "dtFim")    String dataFim) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
			LocalDate dtFim    = LocalDate.parse(dataFim   , formatter);
			
			List<TarefaPorSistemaDTO> tarefaPorSistemaDTO = dashboardService.somaTarefasPorSistema(codigoCategoria, dtInicio, dtFim);
						
			return tarefaPorSistemaDTO != null ? ResponseEntity.ok(tarefaPorSistemaDTO) : ResponseEntity.notFound().build();
		}	

}
