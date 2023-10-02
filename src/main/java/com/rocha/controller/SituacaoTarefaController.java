package com.rocha.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.service.SituacaoTarefaService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.SituacaoTarefa;

@RestController
@RequestMapping("/situacaotarefa")

public class SituacaoTarefaController {

		
	@Autowired
	private SituacaoTarefaService situacaoTarefaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Pesquisar Todas as Situações das Tarefas ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SITUACAO_TAREFA') and #oauth2.hasScope('read')")
	public Page<SituacaoTarefa> pesquisar(Pageable pageable) {
		return situacaoTarefaService.pesquisar(pageable);
		
	}
	
	//----- Pesquisar Todas as Situações das Tarefas por Nome------------//
	@GetMapping(params="nome")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SITUACAO_TAREFA') and #oauth2.hasScope('read')")
	public Page<SituacaoTarefa> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome,Pageable pageable) {
		return situacaoTarefaService.buscarPorNome('%' + nome + '%', pageable);
	}
	
	//----- Pesquisar SituacaoTarefa por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SITUACAO_TAREFA') and #oauth2.hasScope('read')")
	public ResponseEntity<SituacaoTarefa> buscarPorCodigo(@PathVariable Long codigo) {
		 SituacaoTarefa situacaoTarefa = situacaoTarefaService.buscarPorCodigo(codigo);
		
		 return situacaoTarefa != null ? ResponseEntity.ok(situacaoTarefa) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Nova Situação da Tarefa ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SITUACAO_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<SituacaoTarefa> criar(@Valid @RequestBody SituacaoTarefa situacaoTarefa, HttpServletResponse response) {
										
		SituacaoTarefa situacaoTarefaSalva = situacaoTarefaService.criar(situacaoTarefa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, situacaoTarefaSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(situacaoTarefaSalva);
	}
	
	//----- Excluir Situação da Tarefa -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_SITUACAO_TAREFA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		situacaoTarefaService.remover(codigo);
	}
	
	//----- Atualizar Situação da Tarefa -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SITUACAO_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<SituacaoTarefa> atualizar(@PathVariable 		Long 	codigo,
										  @Valid @RequestBody 	SituacaoTarefa 	situacaoTarefa){
		SituacaoTarefa situacaoTarefaSalva = situacaoTarefaService.atualizar(codigo, situacaoTarefa);
				
		return ResponseEntity.ok(situacaoTarefaSalva);
		
	}
			
	
}


