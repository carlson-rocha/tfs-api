package com.rocha.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rocha.model.Usuario;
import com.rocha.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//------ Atualizar e Validar Classe Usuario ------------//
	public Usuario atualizar(Long codigo, Usuario usuario) {
				
			Usuario usuarioSalva 			= buscarUsuarioPeloCodigo(codigo);
			BCryptPasswordEncoder encoder 	= new BCryptPasswordEncoder();
			
				
			if (usuario.getSenha() == null) {
				usuario.setSenha(usuarioSalva.getSenha());
			}else {
				usuario.setSenha(encoder.encode(usuario.getSenha()));
			}
				
			
			BeanUtils.copyProperties(usuario, usuarioSalva, "codigo");
			
			usuarioRepository.save(usuarioSalva);
			
			return usuarioSalva;
			
	}
	
	//------ Incluir e Validar Classe Usuario ------------//
	public Usuario criar(Long codigo, Usuario usuario) {
		
		Usuario usuarioExiste 		    = usuarioRepository.findByCodigo(codigo);
		Usuario usuarioAux    			= usuario;
		BCryptPasswordEncoder encoder 	= new BCryptPasswordEncoder();
				
		if (usuarioExiste != null) {
			throw new UsernameNotFoundException("Codigo de Matricula Existente");
		}
		
		usuarioAux.setSenha(encoder.encode(usuario.getSenha()));
		
		return usuarioRepository.save(usuarioAux);
				
	}
	
	//------ Excluir Classe Usuario ------------//
	public void remover(Long codigo) {
        Usuario usuarioExiste = usuarioRepository.findByCodigo(codigo);

        if ( usuarioExiste == null) {
            throw new UsernameNotFoundException("Usuário Inexistente");
        }
				
		usuarioRepository.delete(codigo);
				
	}


	//----- Retorna Classe Usuario para um determinado Codigo ------//
	public Usuario buscarUsuarioPeloCodigo(Long codigo) {
			Usuario usuario = usuarioRepository.findByCodigo(codigo);
			
			if (usuario == null){
				throw new EmptyResultDataAccessException(1);
			}
			
			return usuario;
	}
	
	
	//----- Retorna Classe Usuario para um determinado Email ------//
	public Usuario buscarUsuarioPeloEmail(String email) {
		
		Optional<Usuario> usuarioOprional = usuarioRepository.findByEmail(email);
		
		Usuario usuario = usuarioOprional.orElseThrow(()-> new UsernameNotFoundException("Email não encontrado"));
		
		return usuario;
	}	
		
	//----- Retorna Classe Usuario por Nome------//
	public Page<Usuario> pesquisar_nome(String nome ,Pageable pageable) {
		
		Page<Usuario> usuarioPage = usuarioRepository.findByNomeIsLike(nome , pageable);
					
		return usuarioPage;
		
	}
	
	//----- Retorna Classe Usuario TODOS ------//
	public Page<Usuario> pesquisar(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
		
	}
	
	
}
