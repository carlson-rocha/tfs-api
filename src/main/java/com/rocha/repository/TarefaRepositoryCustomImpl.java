package com.rocha.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rocha.model.Tarefa;
import com.rocha.model.dto.FiltroTarefaDTO;

@Repository
public class TarefaRepositoryCustomImpl implements TarefaRepositoryCustom  {

	@PersistenceContext
    private EntityManager entityManager;

	/*-------------------------------------------------------------
	--          Montar a Clausa WHERE da Tarefa                  -- 
	-- ------------------------------------------------------------*/
	public List<Tarefa> retornaTarefaFiltro(FiltroTarefaDTO filtro) {

		CriteriaBuilder cb 		    = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tarefa> query = cb.createQuery(Tarefa.class);
        Root<Tarefa> tarefaLista    = query.from(Tarefa.class);

        query.select(tarefaLista);
        
        List<Predicate> predicates = montarfiltro(filtro, cb, tarefaLista);
        
        if(!predicates.isEmpty()) {
        	query.where(cb.and(predicates.toArray(new Predicate[] {})));
        } 
        
        return entityManager.createQuery(query).getResultList();
				
	}

	/*-------------------------------------------------------------
	--          Montar O Filtro Selecionado no Aplicativo        -- 
	-- ------------------------------------------------------------*/
	private List<Predicate>  montarfiltro(	FiltroTarefaDTO filtro, 
											CriteriaBuilder cb, 
											Root<Tarefa> 	tarefaLista) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
        if(filtro.getDataAberturaInicio() != null ) {
          	if( filtro.getDataAberturaFim() == null) {
        		filtro.setDataAberturaFim(filtro.getDataAberturaInicio());
        	}
           	predicates.add(cb.between(tarefaLista.get("dataAbertura"), filtro.getDataAberturaInicio() , filtro.getDataAberturaFim()));
        }
                
        if(filtro.getCodigoSistema() != null) {
        	predicates.add(cb.equal(tarefaLista.get("sistema"), filtro.getCodigoSistema()));
        }
        
        if(filtro.getCodigoCliente() != null) {
        	predicates.add(cb.equal(tarefaLista.get("cliente"), filtro.getCodigoCliente()));
        }
        
        if(filtro.getCodigoAnalistaFuncional() != null) {
        	predicates.add(cb.equal(tarefaLista.get("analistaNegocio"), filtro.getCodigoAnalistaFuncional()));
        }
        
        if(filtro.getCodigoAnalistaTecnico() != null) {
        	predicates.add(cb.equal(tarefaLista.get("analistaTecnico"), filtro.getCodigoAnalistaTecnico()));
        }
        
        if (filtro.getNumeroTicket() != null && !filtro.getNumeroTicket().isEmpty()){
        	predicates.add(cb.equal(tarefaLista.get("numeroTicket"), filtro.getNumeroTicket()));
        };
       
        if(filtro.getNumeroIssue() != null && !filtro.getNumeroIssue().isEmpty()) {
        	predicates.add(cb.equal(tarefaLista.get("numeroIssue"), filtro.getNumeroIssue()));
        };
        
        if(filtro.getCodigoClassificacao() != null) {
        	predicates.add(cb.equal(tarefaLista.get("classificacao"), filtro.getCodigoClassificacao()));
        }
        
        if(filtro.getCodigoCategoria() != null) {
        	predicates.add(cb.equal(tarefaLista.get("categoria"), filtro.getCodigoCategoria()));
        }
        
        if(filtro.getCodigoSituacao() != null) {
        	predicates.add(cb.equal(tarefaLista.get("situacao"), filtro.getCodigoSituacao()));
        }
        
        if(filtro.getDataFechamentoInicio() != null ) {
          	if( filtro.getDataFechamentoFim() == null) {
        		filtro.setDataFechamentoFim(filtro.getDataFechamentoInicio());
        	}
           	predicates.add(cb.between(tarefaLista.get("dataFechamento"), filtro.getDataFechamentoInicio() , filtro.getDataFechamentoFim()));
        }
        
        if(filtro.getDataPrevistaInicio() != null ) {
          	if( filtro.getDataPrevistaFim() == null) {
        		filtro.setDataPrevistaFim(filtro.getDataPrevistaInicio());
        	}
           	predicates.add(cb.between(tarefaLista.get("dataPrevistaEntrega"), filtro.getDataPrevistaInicio() , filtro.getDataPrevistaFim()));
        }
        
        
        return predicates;
    }
}
