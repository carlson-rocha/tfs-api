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

import com.rocha.service.ClienteService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.Cliente;

@RestController
@RequestMapping("/cliente")

public class ClienteController {

		
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Pesquisar Todos os Clientes ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTES') and #oauth2.hasScope('read')")
	public Page<Cliente> pesquisar(Pageable pageable) {
		return clienteService.pesquisar(pageable);
		
	}
	
	//----- Pesquisar Todos os Clientes por Nome------------//
	@GetMapping(params="nome")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTES') and #oauth2.hasScope('read')")
	public Page<Cliente> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome,Pageable pageable) {
		return clienteService.buscarPorNome('%' + nome + '%', pageable);
	}
	
	//----- Pesquisar Todos os Clientes por Licenca------------//
	@GetMapping(params="licenca")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTES') and #oauth2.hasScope('read')")
	public Page<Cliente> buscarPorLicenca(@RequestParam(required = false, defaultValue = "%") String licenca,Pageable pageable) {
		return clienteService.buscarPorLicenca('%' + licenca + '%', pageable);
	}
	
	//----- Pesquisar Cliente por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTES') and #oauth2.hasScope('read')")
	public ResponseEntity<Cliente> buscarPorCodigo(@PathVariable Long codigo) {
		 Cliente cliente = clienteService.buscarPorCodigo(codigo);
		
		 return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Novo Cliente ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTES') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
										
		Cliente clienteSalva = clienteService.criar(cliente);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);
	}
	
	//----- Excluir Cliente -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTES') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		clienteService.remover(codigo);
	}
	
	//----- Atualizar Cliente -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTES') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> atualizar(@PathVariable 		Long 	codigo,
										  @Valid @RequestBody 	Cliente 	cliente){
		Cliente clienteSalva = clienteService.atualizar(codigo, cliente);
				
		return ResponseEntity.ok(clienteSalva);
		
	}
			
	
}


