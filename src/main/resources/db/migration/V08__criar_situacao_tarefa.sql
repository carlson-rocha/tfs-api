CREATE TABLE situacao_tarefa (
	codigo 		BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_situacao_tarefa_nome  ON situacao_tarefa (nome) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

INSERT INTO situacao_tarefa (nome) VALUES ("Aberto");
INSERT INTO situacao_tarefa (nome) VALUES ("Em Atendimento");
INSERT INTO situacao_tarefa (nome) VALUES ("Encerrado");

INSERT INTO permissao (codigo, descricao) values (25, 'ROLE_CADASTRAR_SITUACAO_TAREFA');
INSERT INTO permissao (codigo, descricao) values (26, 'ROLE_REMOVER_SITUACAO_TAREFA');
INSERT INTO permissao (codigo, descricao) values (27, 'ROLE_PESQUISAR_SITUACAO_TAREFA');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 26);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 27);