package com.rocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocha.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	public Tarefa findByNumeroIssue(String numeroIssue);
	
	public Tarefa findByNumeroTicket(String numeroTicket);
	
	public Tarefa findByCodigo(Long codigo);

	public boolean existsByNumeroIssue(String numeroIssue);
	
	public boolean existsByNumeroTicket(String numeroTicket);
	
	
	

	
}
