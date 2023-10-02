package com.rocha.service;

import com.rocha.model.Cliente;
import com.rocha.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> pesquisar(Pageable page) {
        return clienteRepository.findAll(page);
    }

    public Cliente buscarPorCodigo(Long codigo) {
        return clienteRepository.findByCodigo(codigo);
    }


    public Page<Cliente> buscarPorNome(String nome, Pageable pageable) {
        return clienteRepository.findByNomeLikeIgnoreCase(nome, pageable);
    }


    public Page<Cliente> buscarPorLicenca(String licenca, Pageable pageable) {
        return clienteRepository.findByLicencaLikeIgnoreCase(licenca, pageable);
    }


    public Cliente atualizar(Long codigo, Cliente cliente) {
        Cliente clienteExiste = clienteRepository.findByCodigo(codigo);

        if ( clienteExiste == null) {
            throw new UsernameNotFoundException("Cliente Inexistente"); //:todo: trocar exception
        }

        return clienteRepository.save(cliente);
    }


    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public void remover(Long codigo) {
        Cliente clienteExiste = clienteRepository.findByCodigo(codigo);

        if ( clienteExiste == null) {
            throw new UsernameNotFoundException("Cliente Inexistente"); //:todo: trocar exception
        }
        clienteRepository.delete(codigo);
    }

}
