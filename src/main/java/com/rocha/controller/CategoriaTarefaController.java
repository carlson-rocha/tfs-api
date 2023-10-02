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

import com.rocha.service.CategoriaTarefaService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.CategoriaTarefa;

@RestController
@RequestMapping("/categoriatarefa")
public class CategoriaTarefaController {
	
	@Autowired
	private CategoriaTarefaService categoriaTarefaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Pesquisar Todas as Classificações das Tarefas ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_TAREFA') and #oauth2.hasScope('read')")
	public Page<CategoriaTarefa> pesquisar(Pageable pageable) {
		return categoriaTarefaService.pesquisar(pageable);
		
	}
	
	//----- Pesquisar Todas as Classificações das Tarefas por Nome------------//
	@GetMapping(params="nome")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_TAREFA') and #oauth2.hasScope('read')")
	public Page<CategoriaTarefa> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome,Pageable pageable) {
		return categoriaTarefaService.buscarPorNome('%' + nome + '%', pageable);
	}
	
	//----- Pesquisar CategoriaTarefa por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA_TAREFA') and #oauth2.hasScope('read')")
	public ResponseEntity<CategoriaTarefa> buscarPorCodigo(@PathVariable Long codigo) {
		 CategoriaTarefa categoriaTarefa = categoriaTarefaService.buscarPorCodigo(codigo);
		
		 return categoriaTarefa != null ? ResponseEntity.ok(categoriaTarefa) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Nova Categoria da Tarefa ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<CategoriaTarefa> criar(@Valid @RequestBody CategoriaTarefa categoriaTarefa, HttpServletResponse response) {
										
		CategoriaTarefa categoriaTarefaSalva = categoriaTarefaService.criar(categoriaTarefa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaTarefaSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaTarefaSalva);
	}
	
	//----- Excluir Categoria da Tarefa -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CATEGORIA_TAREFA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		categoriaTarefaService.remover(codigo);
	}
	
	//----- Atualizar Categoria da Tarefa -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA_TAREFA') and #oauth2.hasScope('write')")
	public ResponseEntity<CategoriaTarefa> atualizar(@PathVariable 		    Long 	            codigo,
										             @Valid @RequestBody 	CategoriaTarefa 	categoriaTarefa){
		CategoriaTarefa categoriaTarefaSalva = categoriaTarefaService.atualizar(codigo, categoriaTarefa);
				
		return ResponseEntity.ok(categoriaTarefaSalva);
		
	}
			
	
}
