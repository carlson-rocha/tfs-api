package com.rocha.service;

import com.rocha.model.Sistema;
import com.rocha.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SistemaService {

    @Autowired
    private SistemaRepository sistemaRepository;

    public Page<Sistema> pesquisar(Pageable page) {
        return sistemaRepository.findAll(page);
    }

    public Sistema buscarPorId(Long id) {
        return sistemaRepository.findById(id);
    }


    public Page<Sistema> buscarPorNome(String nome, Pageable pageable) {
        return sistemaRepository.findByNomeLikeIgnoreCase(nome, pageable);
    }


    public Sistema atualizar(Long id, Sistema sistema) {
        Sistema sistemaExiste = sistemaRepository.findById(id);

        if ( sistemaExiste == null) {
            throw new UsernameNotFoundException("Sistema Inexistente"); //:todo: trocar exception
        }

        return sistemaRepository.save(sistema);
    }


    public Sistema criar(Sistema sistema) {
        return sistemaRepository.save(sistema);
    }


    public void remover(Long id) {
        Sistema sistemaExiste = sistemaRepository.findById(id);

        if ( sistemaExiste == null) {
            throw new UsernameNotFoundException("Sistema Inexistente"); //:todo: trocar exception
        }
        sistemaRepository.delete(id);
    }

}
