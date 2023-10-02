package com.rocha.service;


import com.rocha.model.CategoriaTarefa;
import com.rocha.repository.CategoriaTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaTarefaService {

    @Autowired
    private CategoriaTarefaRepository categoriaTarefaRepository;

    public Page<CategoriaTarefa> pesquisar(Pageable page) {
        return categoriaTarefaRepository.findAll(page);
    }

    public CategoriaTarefa buscarPorCodigo(Long codigo) {
        return categoriaTarefaRepository.findByCodigo(codigo);
    }


    public Page<CategoriaTarefa> buscarPorNome(String nome, Pageable pageable) {
        return categoriaTarefaRepository.findByNomeLikeIgnoreCase(nome, pageable);
    }


    public CategoriaTarefa atualizar(Long codigo, CategoriaTarefa categoriaTarefa) {
    	CategoriaTarefa categoriaTarefaExiste = categoriaTarefaRepository.findByCodigo(codigo);

        if ( categoriaTarefaExiste == null) {
            throw new UsernameNotFoundException("Categoria da Tarefa Inexistente"); //:todo: trocar exception
        }

        return categoriaTarefaRepository.save(categoriaTarefa);
    }


    public CategoriaTarefa criar(CategoriaTarefa categoriaTarefa) {
        return categoriaTarefaRepository.save(categoriaTarefa);
    }


    public void remover(Long codigo) {
    	CategoriaTarefa categoriaTarefaExiste = categoriaTarefaRepository.findByCodigo(codigo);

        if ( categoriaTarefaExiste == null) {
            throw new UsernameNotFoundException("Categoria da Tarefa Inexistente"); //:todo: trocar exception
        }
        categoriaTarefaRepository.delete(codigo);
    }

}
