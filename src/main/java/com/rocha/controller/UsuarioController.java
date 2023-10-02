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

import com.rocha.service.UsuarioService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

		
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Trazer Todas os Usuarios ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar(Pageable pageable) {
		return usuarioService.pesquisar(pageable);
		
	}
	
	//----- Trazer Todas as Usuario por Nome------------//
	@GetMapping(params="descricao")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar_nome(@RequestParam(required = false, defaultValue = "%") String descricao,Pageable pageable) {
		
		
		return usuarioService.pesquisar_nome('%' + descricao + '%', pageable);
		
	}
	
	
	//----- Trazer Usuario por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		 Usuario usuario = usuarioService.buscarUsuarioPeloCodigo(codigo);
		
		 return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Nova Usuario ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
										
		Usuario usuarioSalva = usuarioService.criar(usuario.getCodigo() , usuario);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}
	
	//----- Excluir Usuario -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		usuarioService.remover(codigo);
	}
	
	//----- Atualizar Usuario -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable 		    Long 		codigo, 
										     @Valid @RequestBody 	Usuario 	usuario){
		Usuario usuarioSalva = usuarioService.atualizar(codigo, usuario);
				
		return ResponseEntity.ok(usuarioSalva);
		
	}
			
	
}


