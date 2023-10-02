package com.rocha.service;

import com.rocha.model.ClassificacaoTarefa;
import com.rocha.repository.ClassificacaoTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClassificacaoTarefaService {

    @Autowired
    private ClassificacaoTarefaRepository classificacaoTarefaRepository;

    public Page<ClassificacaoTarefa> pesquisar(Pageable page) {
        return classificacaoTarefaRepository.findAll(page);
    }

    public ClassificacaoTarefa buscarPorCodigo(Long codigo) {
        return classificacaoTarefaRepository.findByCodigo(codigo);
    }


    public Page<ClassificacaoTarefa> buscarPorNome(String nome, Pageable pageable) {
        return classificacaoTarefaRepository.findByNomeLikeIgnoreCase(nome, pageable);
    }


    public ClassificacaoTarefa atualizar(Long codigo, ClassificacaoTarefa classificacaoTarefa) {
        ClassificacaoTarefa classificacaoTarefaExiste = classificacaoTarefaRepository.findByCodigo(codigo);

        if ( classificacaoTarefaExiste == null) {
            throw new UsernameNotFoundException("Classificação da Tarefa Inexistente"); //:todo: trocar exception
        }

        return classificacaoTarefaRepository.save(classificacaoTarefa);
    }


    public ClassificacaoTarefa criar(ClassificacaoTarefa classificacaoTarefa) {
        return classificacaoTarefaRepository.save(classificacaoTarefa);
    }


    public void remover(Long codigo) {
        ClassificacaoTarefa classificacaoTarefaExiste = classificacaoTarefaRepository.findByCodigo(codigo);

        if ( classificacaoTarefaExiste == null) {
            throw new UsernameNotFoundException("Classificação da Tarefa Inexistente"); //:todo: trocar exception
        }
        classificacaoTarefaRepository.delete(codigo);
    }

}
