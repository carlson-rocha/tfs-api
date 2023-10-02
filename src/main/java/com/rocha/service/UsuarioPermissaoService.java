package com.rocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocha.model.Permissao;
import com.rocha.model.Usuario;
import com.rocha.model.UsuarioPermissao;
import com.rocha.repository.PermissaoRepository;
import com.rocha.repository.UsuarioPermissaoRepository;
import com.rocha.repository.UsuarioRepository;

@Service
public class UsuarioPermissaoService {

	@Autowired
	private UsuarioPermissaoRepository usuarioPermissaoRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private UsuarioRepository  usuarioRepository;
	
	//------ Incluir Permissao------------//
	public UsuarioPermissao criar(UsuarioPermissao usuarioPermissao) {
		
		UsuarioPermissao	usuarioPermissaoExistente  	= usuarioPermissaoRepository.findOne(usuarioPermissao.getId());
				
		if (usuarioPermissaoExistente == null) {
			
			validar(usuarioPermissao.getId().getCodigoPermissao() , usuarioPermissao.getId().getCodigoUsuario());
			
			usuarioPermissaoRepository.save(usuarioPermissao);
		}
		
		
		return usuarioPermissao;
	}
	
	
	//---- Excluir Permissao ------//
	public void remover(UsuarioPermissao usuarioPermissao ) {
		
		UsuarioPermissao	usuarioPermissaoExistente  	= usuarioPermissaoRepository.findOne(usuarioPermissao.getId());
		
		if (usuarioPermissaoExistente != null) {
			usuarioPermissaoRepository.delete(usuarioPermissaoExistente);
		}	
	}
	
	
	//---- Validar Usuario e Permissao Existente  ------//
	public void validar(Long codigoPermissao, Long  codigoUsuario) {

		Permissao 			permissaoExistente 			= permissaoRepository.findByCodigo(codigoPermissao);
		Usuario   			usuarioExistente   			= usuarioRepository.findByCodigo(codigoUsuario) ;
	
		
		if (permissaoExistente == null) {
			throw new UsernameNotFoundException("Codigo de Permissao Invalido");
		}
		
		if (usuarioExistente == null) {
			throw new UsernameNotFoundException("Codigo de Usuario Invalido");
		}
		
	}
	
}
