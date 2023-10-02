package com.rocha.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rocha.model.dto.TarefaPorAnalistaDTO;
import com.rocha.model.dto.TarefaPorClienteDTO;
import com.rocha.model.dto.TarefaPorPrioridadeDTO;
import com.rocha.model.dto.TarefaPorSistemaDTO;
import com.rocha.model.dto.TarefaPorSituacaoDTO;

@Repository
public class DashboardRepositoryCustomImpl implements DashboardRepositoryCustom  {

	@PersistenceContext
    private EntityManager entityManager;
	
	//----- Recuperar Tarefas Por Situação Aberta -------------//
	@Override
	public List<TarefaPorSituacaoDTO> somaTarefasPorSituacaoAberta(Long codigoCategoria) {
		
		String ls_queryAberta = "SELECT a.codigo 			   as codigo, "
				+ "           a.nome 			   as nome, "
				+ "           COUNT(*)     		   as quantidade "
				+ "     FROM  SituacaoTarefa a ,"
				+ "           Tarefa         b  "
				+ "     WHERE  a.aberto           = 1 "
				+ "     and    a.codigo           = b.situacao.codigo"
				+ "     and    b.categoria.codigo = :codigoCategoria"
				+ "     GROUP BY a.codigo, "
				+ "              a.nome" ;

		return BuscaTarefaPorSituacao(ls_queryAberta, codigoCategoria);
	}

	//----- Recuperar Tarefas Por Situação Fechada -------------//
	@Override
	public List<TarefaPorSituacaoDTO> somaTarefasPorSituacaoFechada(Long codigoCategoria, LocalDate dtInicio, LocalDate dtFim) {

		String ls_queryFechada = "SELECT a.codigo 			   as codigo, "
					+ "           a.nome 			   as nome, "
					+ "           COUNT(*)     		   as quantidade "
					+ "     FROM  SituacaoTarefa a ,"
					+ "           Tarefa         b  "
					+ "     WHERE  a.aberto           = 0 "
				    + "     and    a.codigo           = b.situacao.codigo "
				    + "     and    b.categoria.codigo = :codigoCategoria "
				    + "     and    b.dataAbertura     between  :adtInicio and :adtFim"
					+ "     GROUP BY a.codigo, "
					+ "              a.nome" ;
		
		return BuscaTarefaPorSituacaoeData(ls_queryFechada, dtInicio , dtFim, codigoCategoria );
	}

	//----- Recuperar Tarefas Por Prioridade -------------//
	@Override
	public List<TarefaPorPrioridadeDTO> somaTarefasPorPrioridade(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim) {

		String ls_query  = "SELECT b.prioridade 	     as nome, "
				+ "                sum(case when b.situacao.aberto = 1 then 1L else 0L end)	as quantidade_aberto, "
				+ "		           sum(case when b.situacao.aberto = 0 then 1L else 0L end)	as quantidade_fechado "
				+ "FROM  Tarefa         b "
				+ "WHERE  b.dataAbertura   between  :adtInicio and :adtFim "
				+ "and    b.categoria.codigo       = :codigoCategoria "
				+ "GROUP BY b.prioridade";
				

		return BuscaTarefasPorPrioridade( ls_query, dtInicio, dtFim, codigoCategoria );
	}

	//----- Recuperar Tarefas Abertas/Fechadas Por Analista Tecnico-------------//
	public List<TarefaPorAnalistaDTO> somaTarefasPorAnalistaTecnico(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim) {

		String ls_query  = "SELECT c.codigo 		   as codigo, "
						 + "       c.nome 			   as nome, "
				         + "       b.situacao.aberto  as aberto,"
						 + "       COUNT(*)     	   as quantidade "
						 + "FROM  Tarefa         b ,"
						 + "      Usuario        c  "
						 + "WHERE  b.dataAbertura   between  :adtInicio and :adtFim "
				         + "and    b.categoria.codigo       = :codigoCategoria "
				         + "and    b.analistaTecnico.codigo = c.codigo  "
						 + "GROUP BY c.codigo, "
						 + "         c.nome,"
						 + "         b.situacao.aberto";
		
		return BuscaTarefaPorAnalista(ls_query , dtInicio , dtFim, codigoCategoria );
	}
	
	//----- Recuperar Tarefas Abertas/Fechadas Por Analista Negocio-------------//
	public List<TarefaPorAnalistaDTO> somaTarefasPorAnalistaNegocio(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim) {

			String ls_query  = "SELECT c.codigo 		   as codigo, "
							 + "       c.nome 			   as nome, "
					         + "       b.situacao.aberto  as aberto,"
							 + "       COUNT(*)     	   as quantidade "
							 + "FROM  Tarefa         b ,"
							 + "      Usuario        c  "
							 + "WHERE  b.dataAbertura   between  :adtInicio and :adtFim "
					         + "and    b.categoria.codigo       = :codigoCategoria "
					         + "and    b.analistaNegocio.codigo = c.codigo  "
							 + "GROUP BY c.codigo, "
							 + "         c.nome,"
							 + "         b.situacao.aberto";

			return BuscaTarefaPorAnalista(ls_query , dtInicio , dtFim, codigoCategoria );
	}	
	
	//---------------------------------------------------------------------//
	//         Montar Query e Transformar para Objeto por Situação         //
	//---------------------------------------------------------------------//
	private List<TarefaPorSituacaoDTO> BuscaTarefaPorSituacao(String ls_query,
															  Long codigoCategoria) {
		List<TarefaPorSituacaoDTO> tarefaPorSituacaoDTO = new ArrayList<>();
		
		
	    Query query = entityManager.createQuery(ls_query);
		query.setParameter("codigoCategoria"   , codigoCategoria);

	    List<Object[]>  tarefaAux  = query.getResultList();
	       
	    tarefaAux.stream().forEach((record) -> {
	    											TarefaPorSituacaoDTO objAux = new TarefaPorSituacaoDTO();										
	    											
	    											objAux.setCodigo(((Long) record[0]).longValue());
	    											objAux.setNome((String) record[1]);
	    											objAux.setQuantidade(((Long) record[2]).longValue());
	    											
	    											tarefaPorSituacaoDTO.add(objAux);
	    											
	    										});
		entityManager.close();

		return tarefaPorSituacaoDTO;
	}

	private List<TarefaPorSituacaoDTO> BuscaTarefaPorSituacaoeData(String ls_query ,
																   LocalDate dtInicio,
																   LocalDate dtFim,
																   Long codigoCategoria) {
		List<TarefaPorSituacaoDTO> tarefaPorSituacaoDTO = new ArrayList<>();

		Query query = entityManager.createQuery(ls_query);
		query.setParameter("adtInicio", dtInicio);
		query.setParameter("adtFim"   , dtFim);
		query.setParameter("codigoCategoria", codigoCategoria);

		List<Object[]>  tarefaAux  = query.getResultList();

		tarefaAux.stream().forEach((record) -> {
													TarefaPorSituacaoDTO objAux = new TarefaPorSituacaoDTO();

													objAux.setCodigo(((Long) record[0]).longValue());
													objAux.setNome((String) record[1]);
													objAux.setQuantidade(((Long) record[2]).longValue());

													tarefaPorSituacaoDTO.add(objAux);

												});
		entityManager.close();
		return tarefaPorSituacaoDTO;
	}
	
	//----- Recuperar Tarefas Abertas/Fechadas Por Cliente-------------//
	public List<TarefaPorClienteDTO> somaTarefasPorCliente(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim) {

		String ls_query  = "SELECT c.codigo 		   								as codigo, "
				 + "       c.nome 			   										as nome, "
				 + "       sum(case when b.situacao.aberto = 1 then 1L else 0L end)	as quantidade_aberto, "
				 + "       sum(case when b.situacao.aberto = 0 then 1L else 0L end)	as quantidade_fechado "
				 + "FROM   Tarefa              b ,"
				 + "       Cliente             c  "
				 + "WHERE  b.dataAbertura   between  :adtInicio and :adtFim "
		         + "and    b.categoria.codigo       = :codigoCategoria "
		         + "and    b.cliente.codigo         = c.codigo  "
				 + "GROUP  BY c.codigo, "
				 + "       c.nome";
		
			return BuscaTarefaPorCliente(ls_query , dtInicio , dtFim, codigoCategoria );
	}	
	
	//----- Recuperar Tarefas Abertas/Fechadas Por Sistema-------------//
	public List<TarefaPorSistemaDTO> somaTarefasPorSistema(Long codigoCategoria, LocalDate dtInicio , LocalDate dtFim) {

		String ls_query  = "SELECT c.id 		   								as codigo, "
				 + "       c.nome 			   										as nome, "
				 + "       sum(case when b.situacao.aberto = 1 then 1L else 0L end)	as quantidade_aberto, "
				 + "       sum(case when b.situacao.aberto = 0 then 1L else 0L end)	as quantidade_fechado "
				 + "FROM   Tarefa              b ,"
				 + "       Sistema             c  "
				 + "WHERE  b.dataAbertura   between  :adtInicio and :adtFim "
		         + "and    b.categoria.codigo       = :codigoCategoria "
		         + "and    b.sistema.id		        = c.id  "
				 + "GROUP  BY c.id, "
				 + "       c.nome";
		
			return BuscaTarefaPorSistema(ls_query , dtInicio , dtFim, codigoCategoria );
	}	

	//---------------------------------------------------------------------//
	//       Montar Query e Transformar para Objeto por Analista           //
	//---------------------------------------------------------------------//
	private List<TarefaPorAnalistaDTO> BuscaTarefaPorAnalista(String ls_query ,
			                                                  LocalDate dtInicio,
			                                                  LocalDate dtFim,
															  Long codigoCategoria) {
			
			List<TarefaPorAnalistaDTO> tarefaPorAnalistaDTO = new ArrayList<>();

		    Query query = entityManager.createQuery(ls_query);	
		    query.setParameter("adtInicio", dtInicio);
		    query.setParameter("adtFim"   , dtFim);
		    query.setParameter("codigoCategoria"   , codigoCategoria);

		    List<Object[]>  tarefaAux  = query.getResultList();
		       
		    tarefaAux.stream().forEach((record) -> {
														TarefaPorAnalistaDTO objAux = new TarefaPorAnalistaDTO(
														((Long)    record[0]).longValue(),
														(String)  record[1],
														(boolean) record[2],
														((Long)   record[3]).longValue()) ;

														tarefaPorAnalistaDTO.add(objAux);
													});
			entityManager.close();
			return tarefaPorAnalistaDTO;
	}

	private List<TarefaPorPrioridadeDTO> BuscaTarefasPorPrioridade(String ls_query ,
																  LocalDate dtInicio,
																  LocalDate dtFim,
																  Long codigoCategoria) {

		List<TarefaPorPrioridadeDTO> tarefaPorPrioridadeDTO = new ArrayList<>();

		Query query = entityManager.createQuery(ls_query);
		query.setParameter("adtInicio"      , dtInicio);
		query.setParameter("adtFim"         , dtFim);
		query.setParameter("codigoCategoria", codigoCategoria);

		List<Object[]>  tarefaAux  = query.getResultList();

		tarefaAux.stream().forEach((record) -> {
													TarefaPorPrioridadeDTO objAux = new TarefaPorPrioridadeDTO(
															(String)  record[0].toString(),
															((Long)   record[1]).longValue(),
															((Long)   record[2]).longValue()) ;
													
													tarefaPorPrioridadeDTO.add(objAux);
												});
		entityManager.close();
		return tarefaPorPrioridadeDTO;
	}

	//---------------------------------------------------------------------//
	//       Montar Query e Transformar para Objeto por Cliente           //
	//---------------------------------------------------------------------//
	private List<TarefaPorClienteDTO> BuscaTarefaPorCliente(String ls_query ,
			                                                  LocalDate dtInicio,
			                                                  LocalDate dtFim,
															  Long codigoCategoria) {
			
			List<TarefaPorClienteDTO> tarefaPorClienteDTO = new ArrayList<>();

		    Query query = entityManager.createQuery(ls_query);	
		    query.setParameter("adtInicio"			, dtInicio);
		    query.setParameter("adtFim"   			, dtFim);
		    query.setParameter("codigoCategoria"	, codigoCategoria);

		    List<Object[]>  tarefaAux  = query.getResultList();
		       
		    tarefaAux.stream().forEach((record) -> {
														TarefaPorClienteDTO objAux = new TarefaPorClienteDTO(
														((Long)   record[0]).longValue(),
														(String)  record[1],
														((Long)   record[2]).longValue(),
														((Long)   record[3]).longValue()) ;

														tarefaPorClienteDTO.add(objAux);
													});
			entityManager.close();
			return tarefaPorClienteDTO;
	}

	//---------------------------------------------------------------------//
	//       Montar Query e Transformar para Objeto por Sistema           //
	//---------------------------------------------------------------------//
	private List<TarefaPorSistemaDTO> BuscaTarefaPorSistema(String ls_query ,
			                                                  LocalDate dtInicio,
			                                                  LocalDate dtFim,
															  Long codigoCategoria) {
			
			List<TarefaPorSistemaDTO> tarefaPorSistemaDTO = new ArrayList<>();

		    Query query = entityManager.createQuery(ls_query);	
		    query.setParameter("adtInicio"			, dtInicio);
		    query.setParameter("adtFim"   			, dtFim);
		    query.setParameter("codigoCategoria"	, codigoCategoria);

		    List<Object[]>  tarefaAux  = query.getResultList();
		       
		    tarefaAux.stream().forEach((record) -> {
														TarefaPorSistemaDTO objAux = new TarefaPorSistemaDTO(
														((Long)   record[0]).longValue(),
														(String)  record[1],
														((Long)   record[2]).longValue(),
														((Long)   record[3]).longValue()) ;

														tarefaPorSistemaDTO.add(objAux);
													});
			entityManager.close();
			return tarefaPorSistemaDTO;
	}

}
