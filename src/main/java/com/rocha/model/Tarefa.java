package com.rocha.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="tarefa")
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codigo;
    
    @NotNull
    @Column(name="data_abertura")
    private LocalDate dataAbertura;		
	
    @NotNull
    @ManyToOne
	@JoinColumn(name="codigo_sistema")
	private Sistema sistema;
	
    @NotNull
    @ManyToOne
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;
	
    @ManyToOne
	@JoinColumn(name="codigo_analista_neg")
	private Usuario analistaNegocio;
	
    @ManyToOne
	@JoinColumn(name="codigo_analista_tec")
	private Usuario analistaTecnico;
	
	@Column(name="numero_ticket")
	private String numeroTicket;
	
	@Column(name="numero_issue")
	private String numeroIssue;
	
	@Column(name="descricao_cliente")
	private String descricaoCliente;
	
	@Column(name="descricao_negocio")
	private String descricaoNegocio;
	
	@Column(name="descricao_tecnico")
	private String descricaoTecnico;
	
	@Column(name="descricao_caminho_menu")
	private String descricaoCaminhoMenu;

	@Column(name="prioridade")
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;

	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_classificacao")
	private ClassificacaoTarefa classificacao;
	
	@Column(name="data_prevista_entrega")
	private LocalDate dataPrevistaEntrega;
	
	@Column(name="data_fechamento")
	private LocalDate dataFechamento;
	
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	private CategoriaTarefa categoria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_situacao")
	private SituacaoTarefa situacao;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public Usuario getAnalistaNegocio() {
		return analistaNegocio;
	}
	public void setAnalistaNegocio(Usuario analistaNegocio) {
		this.analistaNegocio = analistaNegocio;
	}

	public Usuario getAnalistaTecnico() {
		return analistaTecnico;
	}
	public void setAnalistaTecnico(Usuario analistaTecnico) {
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
	public void setClassificacao(ClassificacaoTarefa classificacao) {
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

	public SituacaoTarefa getSituacao() { return situacao;	}
	public void setSituacao(SituacaoTarefa situacao) {	this.situacao = situacao;	}

	public Prioridade getPrioridade() {	return prioridade; }
	public void setPrioridade(Prioridade prioridade) {	this.prioridade = prioridade;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Tarefa other = (Tarefa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
		
}
