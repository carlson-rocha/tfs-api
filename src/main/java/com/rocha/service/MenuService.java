package com.rocha.service;

import com.rocha.model.Menu;
import com.rocha.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Page<Menu> pesquisar(Pageable page) {
        return menuRepository.findAll(page);
    }

    public Menu buscarPorCodigo(Long codigo) {
        return menuRepository.findByCodigo(codigo);
    }


    public Page<Menu> buscarPorSistema(String sistema, Pageable pageable) {
        return menuRepository.findBySistemaLikeIgnoreCase(sistema, pageable);
    }


    public Page<Menu> buscarPorCaminho(String caminho, Pageable pageable) {
        return menuRepository.findByCaminhoLikeIgnoreCase(caminho, pageable);
    }


    public Menu atualizar(Long codigo, Menu menu) {
        Menu menuExiste = menuRepository.findByCodigo(codigo);

        if ( menuExiste == null) {
            throw new UsernameNotFoundException("Menu Inexistente"); //:todo: trocar exception
        }

        return menuRepository.save(menu);
    }


    public Menu criar(Menu menu) {
        return menuRepository.save(menu);
    }


    public void remover(Long codigo) {
        Menu menuExiste = menuRepository.findByCodigo(codigo);

        if ( menuExiste == null) {
            throw new UsernameNotFoundException("Menu Inexistente"); //:todo: trocar exception
        }
        menuRepository.delete(codigo);
    }

}
