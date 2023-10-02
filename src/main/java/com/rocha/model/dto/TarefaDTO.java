package com.rocha.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import com.rocha.model.Tarefa;
import com.rocha.model.*;

public class TarefaDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long codigoTarefa;
    private LocalDate dataAbertura;		
    private Sistema sistema;
    private Cliente cliente;
	private UsuarioDTO analistaNegocio;
	private UsuarioDTO analistaTecnico;
	private String numeroTicket;
	private String numeroIssue;
	private String descricaoCliente;
	private String descricaoNegocio;
	private String descricaoTecnico;
	private String descricaoCaminhoMenu;
	private ClassificacaoTarefa classificacao;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataFechamento;
	private CategoriaTarefa categoria;
	private Prioridade prioridade;
	private SituacaoTarefa  situacao;
	
	
	public TarefaDTO() {
	}
	
	public TarefaDTO( Long   codigoTarefa				, LocalDate dataAbertura		, Sistema sistema    					, 
					  Cliente cliente					, UsuarioDTO analistaNegocio	, UsuarioDTO analistaTecnico			,
					  String numeroTicket				, String numeroIssue			, String descricaoCliente				,
					  String descricaoNegocio			, String descricaoTecnico		, String descricaoCaminhoMenu			,
					  ClassificacaoTarefa classificacao	, LocalDate dataPrevistaEntrega , LocalDate dataFechamento				,
					  CategoriaTarefa categoria			, Prioridade prioridade         , SituacaoTarefa situacao 	) {
		super();
		this.codigoTarefa = codigoTarefa;
		this.dataAbertura = dataAbertura;
		this.sistema = sistema;
		this.cliente = cliente;
		this.analistaNegocio = analistaNegocio;
		this.analistaTecnico = analistaTecnico;
		this.numeroTicket = numeroTicket;
		this.numeroIssue = numeroIssue;
		this.descricaoCliente = descricaoCliente;
		this.descricaoNegocio = descricaoNegocio;
		this.descricaoTecnico = descricaoTecnico;
		this.descricaoCaminhoMenu = descricaoCaminhoMenu;
		this.classificacao = classificacao;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataFechamento = dataFechamento;
		this.categoria = categoria;
		this.situacao = situacao;
		this.prioridade = prioridade;
	}

	public TarefaDTO( Tarefa tarefa ) {
		this.codigoTarefa = tarefa.getCodigo();
		this.dataAbertura = tarefa.getDataAbertura();
		this.sistema = tarefa.getSistema();
		this.cliente = tarefa.getCliente();
		this.analistaNegocio = new UsuarioDTO(tarefa.getAnalistaNegocio());
		this.analistaTecnico = new UsuarioDTO(tarefa.getAnalistaTecnico());
		this.numeroTicket = tarefa.getNumeroTicket();
		this.numeroIssue = tarefa.getNumeroIssue();
		this.descricaoCliente = tarefa.getDescricaoCliente();
		this.descricaoNegocio = tarefa.getDescricaoNegocio();
		this.descricaoTecnico = tarefa.getDescricaoTecnico();
		this.descricaoCaminhoMenu = tarefa.getDescricaoCaminhoMenu();
		this.classificacao = tarefa.getClassificacao();
		this.dataPrevistaEntrega = tarefa.getDataPrevistaEntrega();
		this.dataFechamento = tarefa.getDataFechamento();
		this.categoria = tarefa.getCategoria();
		this.situacao = tarefa.getSituacao();
		this.prioridade = tarefa.getPrioridade();
	}

	public Long getCodigoTarefa() {
		return codigoTarefa;
	}
	public void setCodigoTarefa(Long codigoTarefa) {
		this.codigoTarefa = codigoTarefa;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}


	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public UsuarioDTO getAnalistaNegocio() {
		return analistaNegocio;
	}
	public void setAnalistaNegocio(UsuarioDTO analistaNegocio) {
		this.analistaNegocio = analistaNegocio;
	}


	public UsuarioDTO getAnalistaTecnico() {
		return analistaTecnico;
	}
	public void setAnalistaTecnico(UsuarioDTO analistaTecnico) {
		this.analistaTecnico = analistaTecnico;
	}


	public String getNumeroTicket() {
		return numeroTicket;
	}
	public void setNumeroTicket(String numeroTicket) {
		this.numeroTicket = numeroTicket;
	}


	public String getNumeroIssue() {
		return numeroIssue;
	}
	public void setNumeroIssue(String numeroIssue) {
		this.numeroIssue = numeroIssue;
	}


	public String getDescricaoCliente() {
		return descricaoCliente;
	}
	public void setDescricaoCliente(String descricaoCliente) {
		this.descricaoCliente = descricaoCliente;
	}


	public String getDescricaoNegocio() {
		return descricaoNegocio;
	}
	public void setDescricaoNegocio(String descricaoNegocio) {
		this.descricaoNegocio = descricaoNegocio;
	}


	public String getDescricaoTecnico() {
		return descricaoTecnico;
	}
	public void setDescricaoTecnico(String descricaoTecnico) {
		this.descricaoTecnico = descricaoTecnico;
	}


	public String getDescricaoCaminhoMenu() {
		return descricaoCaminhoMenu;
	}
	public void setDescricaoCaminhoMenu(String descricaoCaminhoMenu) {
		this.descricaoCaminhoMenu = descricaoCaminhoMenu;
	}


	public ClassificacaoTarefa getClassificacao() {
		return classificacao;
	}
	public void setCodigoClassificacao(ClassificacaoTarefa classificacao) {
		this.classificacao = classificacao;
	}

	
	public LocalDate getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}
	public void setDataPrevistaEntrega(LocalDate dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}


	public LocalDate getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}


	public CategoriaTarefa getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaTarefa categoria) {
		this.categoria = categoria;
	}


	public SituacaoTarefa getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoTarefa situacao) {
		this.situacao = situacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTarefa == null) ? 0 : codigoTarefa.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaDTO other = (TarefaDTO) obj;
		if (codigoTarefa == null) {
			if (other.codigoTarefa != null)
				return false;
		} else if (!codigoTarefa.equals(other.codigoTarefa))
			return false;
		return true;
	}
	
}
