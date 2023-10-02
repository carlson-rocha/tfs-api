package com.rocha.service;

import com.rocha.model.SituacaoTarefa;
import com.rocha.repository.SituacaoTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SituacaoTarefaService {

    @Autowired
    private SituacaoTarefaRepository situcacaoTarefaRepository;

    public Page<SituacaoTarefa> pesquisar(Pageable page) {
        return situcacaoTarefaRepository.findAll(page);
    }

    public SituacaoTarefa buscarPorCodigo(Long codigo) {
        return situcacaoTarefaRepository.findByCodigo(codigo);
    }


    public Page<SituacaoTarefa> buscarPorNome(String nome, Pageable pageable) {
        return situcacaoTarefaRepository.findByNomeLikeIgnoreCase(nome, pageable);
    }


    public SituacaoTarefa atualizar(Long codigo, SituacaoTarefa situcacaoTarefa) {
        SituacaoTarefa situcacaoTarefaExiste = situcacaoTarefaRepository.findByCodigo(codigo);

        if ( situcacaoTarefaExiste == null) {
            throw new UsernameNotFoundException("Situação da Tarefa Inexistente"); //:todo: trocar exception
        }

        return situcacaoTarefaRepository.save(situcacaoTarefa);
    }


    public SituacaoTarefa criar(SituacaoTarefa situcacaoTarefa) {
        return situcacaoTarefaRepository.save(situcacaoTarefa);
    }


    public void remover(Long codigo) {
        SituacaoTarefa situcacaoTarefaExiste = situcacaoTarefaRepository.findByCodigo(codigo);

        if ( situcacaoTarefaExiste == null) {
            throw new UsernameNotFoundException("Situação da Tarefa Inexistente"); //:todo: trocar exception
        }
        situcacaoTarefaRepository.delete(codigo);
    }

}
