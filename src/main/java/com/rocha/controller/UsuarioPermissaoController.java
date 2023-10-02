package com.rocha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.model.UsuarioPermissao;
import com.rocha.repository.UsuarioPermissaoRepository;
import com.rocha.service.UsuarioPermissaoService;

@RestController
@RequestMapping("/usuariopermissao")
public class UsuarioPermissaoController {

	@Autowired
	UsuarioPermissaoRepository usuarioPermissaoRepository;
	
	@Autowired
	UsuarioPermissaoService  usuarioPermissaoService;
	
	//----- Trazer Todas os Usuarios ------------//
	@GetMapping
	public Page<UsuarioPermissao> pesquisar(Pageable pageable) {
		return usuarioPermissaoRepository.findAll(pageable);
	}
	
	
	//---  Incluir Usuario e Permissao ------------//
	@PostMapping
	public ResponseEntity<UsuarioPermissao> atualizar( @Valid @RequestBody 	UsuarioPermissao 	usuarioPermissao){
			UsuarioPermissao usuarioPermissaoSalva = usuarioPermissaoService.criar(usuarioPermissao);
					
			return ResponseEntity.ok(usuarioPermissaoSalva);

	}
	
	//---  Excluir Usuario e Permissao ------------//
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover( @Valid @RequestBody 	UsuarioPermissao 	usuarioPermissao){
			usuarioPermissaoService.remover(usuarioPermissao);
			
	}
	
}
