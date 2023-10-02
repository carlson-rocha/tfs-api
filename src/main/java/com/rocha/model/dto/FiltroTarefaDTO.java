package com.rocha.model.dto;

import java.time.LocalDate;

public class FiltroTarefaDTO {
    
    private LocalDate dataAberturaInicio;
    private LocalDate dataAberturaFim;
    private LocalDate dataFechamentoInicio;
    private LocalDate dataFechamentoFim;
    private LocalDate dataPrevistaInicio;
    private LocalDate dataPrevistaFim;
    
    private Long codigoAnalistaFuncional;
    private Long codigoAnalistaTecnico;
    private Long codigoSistema;
    private Long codigoCliente;
    private Long codigoClassificacao;
    private Long codigoCategoria;
    private Long codigoSituacao;
    
    private String numeroTicket;
    private String numeroIssue;
    
	public LocalDate getDataAberturaInicio() {
		return dataAberturaInicio;
	}
	public void setDataAberturaInicio(LocalDate dataAberturaInicio) {
		this.dataAberturaInicio = dataAberturaInicio;
	}
	public LocalDate getDataAberturaFim() {
		return dataAberturaFim;
	}
	public void setDataAberturaFim(LocalDate dataAberturaFim) {
		this.dataAberturaFim = dataAberturaFim;
	}
	public LocalDate getDataFechamentoInicio() {
		return dataFechamentoInicio;
	}
	public void setDataFechamentoInicio(LocalDate dataFechamentoInicio) {
		this.dataFechamentoInicio = dataFechamentoInicio;
	}
	public LocalDate getDataFechamentoFim() {
		return dataFechamentoFim;
	}
	public void setDataFechamentoFim(LocalDate dataFechamentoFim) {
		this.dataFechamentoFim = dataFechamentoFim;
	}
	public LocalDate getDataPrevistaInicio() {
		return dataPrevistaInicio;
	}
	public void setDataPrevistaInicio(LocalDate dataPrevistaInicio) {
		this.dataPrevistaInicio = dataPrevistaInicio;
	}
	public LocalDate getDataPrevistaFim() {
		return dataPrevistaFim;
	}
	public void setDataPrevistaFim(LocalDate dataPrevistaFim) {
		this.dataPrevistaFim = dataPrevistaFim;
	}
	public Long getCodigoAnalistaFuncional() {
		return codigoAnalistaFuncional;
	}
	public void setCodigoAnalistaFuncional(Long codigoAnalistaFuncional) {
		this.codigoAnalistaFuncional = codigoAnalistaFuncional;
	}
	public Long getCodigoAnalistaTecnico() {
		return codigoAnalistaTecnico;
	}
	public void setCodigoAnalistaTecnico(Long codigoAnalistaTecnico) {
		this.codigoAnalistaTecnico = codigoAnalistaTecnico;
	}
	public Long getCodigoSistema() {
		return codigoSistema;
	}
	public void setCodigoSistema(Long codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public Long getCodigoClassificacao() {
		return codigoClassificacao;
	}
	public void setCodigoClassificacao(Long codigoClassificacao) {
		this.codigoClassificacao = codigoClassificacao;
	}
	public Long getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public Long getCodigoSituacao() {
		return codigoSituacao;
	}
	public void setCodigoSituacao(Long codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
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
    
}
