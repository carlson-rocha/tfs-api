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

import com.rocha.service.ClassificacaoTarefaService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.ClassificacaoTarefa;

@RestController
@RequestMapping("/classificacaotarefa")

public class ClassificacaoTarefaController {

		
	@Autowired
	private ClassificacaoTarefaService classificacaoTarefaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Pesquisar Todas as Classificações das Tarefas ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('read')")
	public Page<ClassificacaoTarefa> pesquisar(Pageable pageable) {
		return classificacaoTarefaService.pesquisar(pageable);
		
	}
	
	//----- Pesquisar Todas as Classificações das Tarefas por Nome------------//
	@GetMapping(params="nome")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('read')")
	public Page<ClassificacaoTarefa> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome,Pageable pageable) {
		return classificacaoTarefaService.buscarPorNome('%' + nome + '%', pageable);
	}
	
	//----- Pesquisar ClassificacaoTarefa por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('read')")
	public ResponseEntity<ClassificacaoTarefa> buscarPorCodigo(@PathVariable Long codigo) {
		 ClassificacaoTarefa classificacaoTarefa = classificacaoTarefaService.buscarPorCodigo(codigo);
		
		 return classificacaoTarefa != null ? ResponseEntity.ok(classificacaoTarefa) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Nova Classificação da Tarefa ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<ClassificacaoTarefa> criar(@Valid @RequestBody ClassificacaoTarefa classificacaoTarefa, HttpServletResponse response) {
										
		ClassificacaoTarefa classificacaoTarefaSalva = classificacaoTarefaService.criar(classificacaoTarefa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, classificacaoTarefaSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(classificacaoTarefaSalva);
	}
	
	//----- Excluir Classificação da Tarefa -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		classificacaoTarefaService.remover(codigo);
	}
	
	//----- Atualizar Classificação da Tarefa -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLASSIFICACAO_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<ClassificacaoTarefa> atualizar(@PathVariable 		Long 	codigo,
										  @Valid @RequestBody 	ClassificacaoTarefa 	classificacaoTarefa){
		ClassificacaoTarefa classificacaoTarefaSalva = classificacaoTarefaService.atualizar(codigo, classificacaoTarefa);
				
		return ResponseEntity.ok(classificacaoTarefaSalva);
		
	}
			
	
}


