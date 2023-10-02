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

import com.rocha.service.MenuService;
import com.rocha.event.RecursoCriadoEvent;
import com.rocha.model.Menu;

@RestController
@RequestMapping("/menu")

public class MenuController {

		
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//----- Pesquisar Todos os Menus ------------//
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MENU') and #oauth2.hasScope('read')")
	public Page<Menu> pesquisar(Pageable pageable) {
		return menuService.pesquisar(pageable);
		
	}
	
	//----- Pesquisar Todos os Menus por Sistema------------//
	@GetMapping(params="sistema")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MENU') and #oauth2.hasScope('read')")
	public Page<Menu> buscarPorSistema(@RequestParam(required = false, defaultValue = "%") String sistema,Pageable pageable) {
		return menuService.buscarPorSistema('%' + sistema + '%', pageable);
	}
	
	//----- Pesquisar Todos os Menus por Caminho------------//
	@GetMapping(params="caminho")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MENU') and #oauth2.hasScope('read')")
	public Page<Menu> buscarPorCaminho(@RequestParam(required = false, defaultValue = "%") String caminho,Pageable pageable) {
		return menuService.buscarPorCaminho('%' + caminho + '%', pageable);
	}
	
	//----- Pesquisar Menu por Código ------------//
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MENU') and #oauth2.hasScope('read')")
	public ResponseEntity<Menu> buscarPorCodigo(@PathVariable Long codigo) {
		 Menu menu = menuService.buscarPorCodigo(codigo);
		
		 return menu != null ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
	}
	
	//----- Incluir Novo Menu ----------//
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MENU') and #oauth2.hasScope('write')")
	public ResponseEntity<Menu> criar(@Valid @RequestBody Menu menu, HttpServletResponse response) {
										
		Menu menuSalva = menuService.criar(menu);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, menuSalva.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(menuSalva);
	}
	
	//----- Excluir Menu -------------//
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MENU') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		menuService.remover(codigo);
	}
	
	//----- Atualizar Menu -------------//
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MENU') and #oauth2.hasScope('write')")
	public ResponseEntity<Menu> atualizar(@PathVariable 		Long 	codigo,
										  @Valid @RequestBody 	Menu 	menu){
		Menu menuSalva = menuService.atualizar(codigo, menu);
				
		return ResponseEntity.ok(menuSalva);
		
	}
			
	
}


