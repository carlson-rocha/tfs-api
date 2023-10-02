CREATE TABLE categoria_tarefa (
	codigo 		BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_categoria_tarefa_nome  ON categoria_tarefa (nome) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

INSERT INTO categoria_tarefa (nome) VALUES ("Atendimento do HD");
INSERT INTO categoria_tarefa (nome) VALUES ("Equipe de SLA");
INSERT INTO categoria_tarefa (nome) VALUES ("Equipe de Sustentação");
INSERT INTO categoria_tarefa (nome) VALUES ("Equipe de Produtos");
INSERT INTO categoria_tarefa (nome) VALUES ("Equipe de Desenvolvimento");
