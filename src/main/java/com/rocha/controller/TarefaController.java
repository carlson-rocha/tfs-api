package com.rocha.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.Tarefa;
import com.rocha.model.dto.FiltroTarefaDTO;
import com.rocha.model.dto.TarefaDTO;
import com.rocha.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Buscar Tarefa por Numero do Ticket -------------//
	@GetMapping("/ticket/{numeroTicket}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
	public ResponseEntity<TarefaDTO> buscarPorNumeroTicket(@PathVariable String numeroTicket) {
		TarefaDTO tarefaDTO = tarefaService.buscarTarefaPorTicket(numeroTicket);
		
		return tarefaDTO != null ? ResponseEntity.ok(tarefaDTO) : ResponseEntity.notFound().build();
			
	}
	
	//----- Buscar Tarefa por Numero de Issue -------------//
	@GetMapping("/issue/{numeroIssue}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
	public ResponseEntity<TarefaDTO> buscarPorNumeroIssue(@PathVariable String numeroIssue) {
		TarefaDTO tarefaDTO = tarefaService.buscarTarefaPorIssue(numeroIssue);
		
		return tarefaDTO != null ? ResponseEntity.ok(tarefaDTO) : ResponseEntity.notFound().build();
			
	}

	//----- Buscar Tarefa por Código da Tarefa-------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
	public ResponseEntity<TarefaDTO> buscarPorCodigo(@PathVariable Long codigo) {
		TarefaDTO tarefaDTO = tarefaService.buscarPorCodigo(codigo);
		
		return tarefaDTO != null ? ResponseEntity.ok(tarefaDTO) : ResponseEntity.notFound().build();
			
	}

	
	//----- Buscar Tarefa por Filtro -------------//
	@PostMapping("/filtro")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFAS_HD') and #oauth2.hasScope('read')")
	public ResponseEntity<List<TarefaDTO>> buscarPorfiltro(@Valid @RequestBody FiltroTarefaDTO filtro) {
		
		List<TarefaDTO> tarefaDTO = tarefaService.buscarTarefaPorFiltro(filtro);
			
		return tarefaDTO != null ? ResponseEntity.ok(tarefaDTO) : ResponseEntity.notFound().build();
	}

	
	//----- Incluir Tarefa -------------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TAREFAS_HD') and #oauth2.hasScope('write')")
	public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
											
			Tarefa tarefaSalva = tarefaService.criar(tarefa);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, tarefaSalva.getCodigo()));
			 
			return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
	}
		

	//----- Excluir Tarefa -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TAREFAS_HD') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		tarefaService.remover(codigo);
	}
	
	//----- Atualizar Tarefa -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TAREFAS_HD') and #oauth2.hasScope('write')")
	public ResponseEntity<Tarefa> atualizar(@PathVariable 		    Long 	   codigo, 
										     @Valid @RequestBody 	Tarefa 	   tarefa){
		Tarefa tarefaSalva = tarefaService.atualizar(codigo, tarefa);
				
		return ResponseEntity.ok(tarefaSalva);
		
	}
	
	
}
