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

import com.rocha.service.SistemaService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.Sistema;

@RestController
@RequestMapping("/sistema")

public class SistemaController {

		
	@Autowired
	private SistemaService sistemaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Trazer Todas os Sistemas ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SISTEMA') and #oauth2.hasScope('read')")
	public Page<Sistema> pesquisar(Pageable pageable) {
		return sistemaService.pesquisar(pageable);
		
	}
	
	//----- Trazer Todos os Sistema por Nome------------//
	@GetMapping(params="nome")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SISTEMA') and #oauth2.hasScope('read')")
	public Page<Sistema> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome,Pageable pageable) {
		return sistemaService.buscarPorNome('%' + nome + '%', pageable);
	}
	
	
	//----- Trazer Sistema por Código ------------//
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SISTEMA') and #oauth2.hasScope('read')")
	public ResponseEntity<Sistema> buscarPorId(@PathVariable Long id) {
		 Sistema sistema = sistemaService.buscarPorId(id);
		
		 return sistema != null ? ResponseEntity.ok(sistema) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Novo Sistema ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SISTEMA') and #oauth2.hasScope('write')")
	public ResponseEntity<Sistema> criar(@Valid @RequestBody Sistema sistema, HttpServletResponse response) {
										
		Sistema sistemaSalva = sistemaService.criar(sistema);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, sistemaSalva.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(sistemaSalva);
	}
	
	//----- Excluir Sistema -------------//
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_SISTEMA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		sistemaService.remover(id);
	}
	
	//----- Atualizar Sistema -------------//
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SISTEMA') and #oauth2.hasScope('write')")
	public ResponseEntity<Sistema> atualizar(@PathVariable 		    Long 		id, 
										     @Valid @RequestBody 	Sistema 	sistema){
		Sistema sistemaSalva = sistemaService.atualizar(id, sistema);
				
		return ResponseEntity.ok(sistemaSalva);
		
	}
			
	
}


