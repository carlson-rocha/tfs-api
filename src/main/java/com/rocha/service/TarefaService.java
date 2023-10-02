package com.rocha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocha.model.Tarefa;
import com.rocha.model.dto.FiltroTarefaDTO;
import com.rocha.model.dto.TarefaDTO;
import com.rocha.model.dto.UsuarioDTO;
import com.rocha.repository.TarefaRepository;
import com.rocha.repository.TarefaRepositoryCustom;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaRepositoryCustom tarefaRepositoryCustom;
	
	
	//----- Buscar Tarefa por Código de Tarefa -------------//
	public TarefaDTO buscarPorCodigo(Long codigo) {
		Tarefa tarefaAuxiliar = tarefaRepository.findByCodigo(codigo);
		
		if(tarefaAuxiliar == null) {
			throw new UsernameNotFoundException("Código de Tarefa Inexistente");
		}
		return gerarTarefaDTO(tarefaAuxiliar);
	}
	
		
	//----- Buscar Tarefa por numero Ticket -------------//
	public TarefaDTO buscarTarefaPorTicket(String numeroTicket) {
		Tarefa tarefaAuxiliar = tarefaRepository.findByNumeroTicket(numeroTicket);
		
		if(tarefaAuxiliar == null) {
			throw new UsernameNotFoundException("Numero de Ticket Inexistente");
		}
		return gerarTarefaDTO(tarefaAuxiliar);
	}

	
	//----- Buscar Tarefa por numero Issue -------------//
	public TarefaDTO buscarTarefaPorIssue(String numeroIssue) {
		Tarefa tarefaAuxiliar = tarefaRepository.findByNumeroIssue(numeroIssue);
		
		if(tarefaAuxiliar == null) {
			throw new UsernameNotFoundException("Numero de Issue Inexistente");
		}
		return gerarTarefaDTO(tarefaAuxiliar);
	}
	
	//----- Buscar Tarefa por Filtro -----------//
	public List<TarefaDTO> buscarTarefaPorFiltro(FiltroTarefaDTO filtro) {

		List<Tarefa>    tarefaAuxiliar = tarefaRepositoryCustom.retornaTarefaFiltro(filtro);
		List<TarefaDTO> tarefaDTO      = new ArrayList<TarefaDTO>();
		
		for(Tarefa lancto : tarefaAuxiliar ){
			tarefaDTO.add(gerarTarefaDTO(lancto));
		}
		return tarefaDTO;
	}
		
	
	//----- Incluir Tarefa -------------//
	public Tarefa criar(Tarefa tarefa) {
		
		if(tarefaRepository.existsByNumeroTicket(tarefa.getNumeroTicket())) {
			throw new UsernameNotFoundException("Numero de Ticket Existente");
		}
				
		if(tarefaRepository.existsByNumeroIssue(tarefa.getNumeroIssue())) {
			throw new UsernameNotFoundException("Numero de Issue Existente");
		}
		return tarefaRepository.save(tarefa);
	}

	//----- Excluir Tarefa -------------//
	public void remover(Long codigo) {

		Tarefa tarefa = tarefaRepository.findOne(codigo);
		
		if(tarefa == null) {
			throw new UsernameNotFoundException("Codigo da Tarefa Inexistente");
		}

		tarefaRepository.delete(tarefa);
	}

	//----- Atualizar Tarefa -------------//
	public Tarefa atualizar(Long codigo, Tarefa tarefa) {
		Tarefa tarefaSalva = tarefaRepository.findOne(codigo);

		if(tarefaSalva == null) {
			throw new UsernameNotFoundException("Codigo da Tarefa nao encontrada para atualizacao");
		}
		
		BeanUtils.copyProperties(tarefa, tarefaSalva, "codigo");
		tarefaRepository.save(tarefaSalva);
		return tarefaSalva;
	}

	
	
	private TarefaDTO gerarTarefaDTO(Tarefa tarefaAuxiliar) {

		UsuarioDTO analistaNegocio = new UsuarioDTO(tarefaAuxiliar.getAnalistaNegocio().getCodigo(),
													tarefaAuxiliar.getAnalistaNegocio().getNome(),
													tarefaAuxiliar.getAnalistaNegocio().getEmail());
		
		UsuarioDTO analistaTecnico = new UsuarioDTO(tarefaAuxiliar.getAnalistaTecnico().getCodigo(),
													tarefaAuxiliar.getAnalistaTecnico().getNome(),
													tarefaAuxiliar.getAnalistaTecnico().getEmail());

		TarefaDTO tarefaDTO = new TarefaDTO(tarefaAuxiliar.getCodigo(),
											tarefaAuxiliar.getDataAbertura(), 
											tarefaAuxiliar.getSistema(), 
											tarefaAuxiliar.getCliente(), 
											analistaNegocio, 
											analistaTecnico, 
											tarefaAuxiliar.getNumeroTicket(),
											tarefaAuxiliar.getNumeroIssue(), 
											tarefaAuxiliar.getDescricaoCliente(), 
											tarefaAuxiliar.getDescricaoNegocio(), 
											tarefaAuxiliar.getDescricaoTecnico(),
											tarefaAuxiliar.getDescricaoCaminhoMenu(),
											tarefaAuxiliar.getClassificacao(),
											tarefaAuxiliar.getDataPrevistaEntrega(),
											tarefaAuxiliar.getDataFechamento(),
											tarefaAuxiliar.getCategoria(),
											tarefaAuxiliar.getPrioridade(),
											tarefaAuxiliar.getSituacao());
				
		
		return tarefaDTO;
	}
	
}
