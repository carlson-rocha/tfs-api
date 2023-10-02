CREATE TABLE sistema (
	codigo 		BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_sistema_nome  ON sistema (nome) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

INSERT INTO sistema (nome) VALUES ('AMPLIS');
INSERT INTO sistema (nome) VALUES ('AUTOMATO');
INSERT INTO sistema (nome) VALUES ('COT');
INSERT INTO sistema (nome) VALUES ('FIDC');
INSERT INTO sistema (nome) VALUES ('FRONT');
INSERT INTO sistema (nome) VALUES ('JCOT');
INSERT INTO sistema (nome) VALUES ('MAC');
INSERT INTO sistema (nome) VALUES ('MCA');
INSERT INTO sistema (nome) VALUES ('MCI');
INSERT INTO sistema (nome) VALUES ('MESSENGER');
INSERT INTO sistema (nome) VALUES ('MSG');
INSERT INTO sistema (nome) VALUES ('NETBOL');
INSERT INTO sistema (nome) VALUES ('NETCOT');
INSERT INTO sistema (nome) VALUES ('NETREPORT');
INSERT INTO sistema (nome) VALUES ('SAC');
INSERT INTO sistema (nome) VALUES ('SACAUTO');
INSERT INTO sistema (nome) VALUES ('SAT');
INSERT INTO sistema (nome) VALUES ('SCD');
INSERT INTO sistema (nome) VALUES ('SEC');
INSERT INTO sistema (nome) VALUES ('SECURITY');
INSERT INTO sistema (nome) VALUES ('SLM');
INSERT INTO sistema (nome) VALUES ('SPB');
INSERT INTO sistema (nome) VALUES ('SRC');
INSERT INTO sistema (nome) VALUES ('SWIFT');
INSERT INTO sistema (nome) VALUES ('WEB PLUS');
INSERT INTO sistema (nome) VALUES ('XML ANBID');
