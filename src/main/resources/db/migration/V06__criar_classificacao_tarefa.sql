CREATE TABLE classificacao_tarefa (
	codigo 		BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_classificacao_tarefa_nome  ON classificacao_tarefa (nome) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

INSERT INTO classificacao_tarefa (nome) VALUES ("Aceite Atendimento");
INSERT INTO classificacao_tarefa (nome) VALUES ("Aguardando Correção");
INSERT INTO classificacao_tarefa (nome) VALUES ("Bug - Correção");
INSERT INTO classificacao_tarefa (nome) VALUES ("Desenvolvimento");
INSERT INTO classificacao_tarefa (nome) VALUES ("Em Correção");
INSERT INTO classificacao_tarefa (nome) VALUES ("Encerrado");
INSERT INTO classificacao_tarefa (nome) VALUES ("Infra - Totvs");
INSERT INTO classificacao_tarefa (nome) VALUES ("Não Bug - Orientação");
INSERT INTO classificacao_tarefa (nome) VALUES ("Pendente Atendimento");
INSERT INTO classificacao_tarefa (nome) VALUES ("Pendente Produtos");
INSERT INTO classificacao_tarefa (nome) VALUES ("Script Facilitador");
INSERT INTO classificacao_tarefa (nome) VALUES ("Sincronismo");
INSERT INTO classificacao_tarefa (nome) VALUES ("Visita - Totvs");