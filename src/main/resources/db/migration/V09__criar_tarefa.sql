CREATE TABLE tarefa (
	codigo 						BIGINT(20) 	PRIMARY KEY AUTO_INCREMENT	,
	data_abertura				DATE				NOT NULL			,
	codigo_sistema 				BIGINT(20) 			NOT NULL			,
	codigo_cliente				BIGINT(20)			NOT NULL			,
	codigo_analista_neg			BIGINT(20)				NULL			,
	codigo_analista_tec			BIGINT(20)				NULL			,
	numero_ticket				VARCHAR(50)				NULL			,
	numero_issue				VARCHAR(50)				NULL			,
	descricao_cliente			VARCHAR(2500)			NULL			,
	descricao_negocio			VARCHAR(2500)			NULL			,
	descricao_tecnico			VARCHAR(2500)			NULL			,
	descricao_caminho_menu		VARCHAR(2500)			NULL			,
	codigo_classificacao		BIGINT(20)			NOT NULL			,
	data_prevista_entrega		DATE					NULL			,
	data_fechamento				DATE					NULL			,
	codigo_categoria			BIGINT(20)				NULL			,
	codigo_situacao				BIGINT(20)			NOT NULL			
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_sistema  		FOREIGN KEY (codigo_sistema)  REFERENCES sistema (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_cliente	 		FOREIGN KEY (codigo_cliente)  	REFERENCES cliente (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_usuario_neg  	FOREIGN KEY (codigo_analista_neg)  REFERENCES usuario (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_usuario_tec  	FOREIGN KEY (codigo_analista_tec)  REFERENCES usuario (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_classificacao 	FOREIGN KEY (codigo_classificacao) REFERENCES classificacao_tarefa (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_categoria	 	FOREIGN KEY (codigo_categoria)  REFERENCES categoria_tarefa (codigo);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_situacao	 	FOREIGN KEY (codigo_situacao)  REFERENCES situacao_tarefa (codigo);

ALTER TABLE tarefa ADD INDEX idx_nr_ticket (numero_ticket) ;

ALTER TABLE tarefa ADD INDEX idx_nr_issue (numero_issue) ;


