package com.rocha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.model.Permissao;
import com.rocha.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	//----- Trazer Todas os Usuarios ------------//
	@GetMapping
	public Page<Permissao> pesquisar(Pageable pageable) {
		return permissaoRepository.findAll(pageable);
	}
		
	
}
