CREATE TABLE cliente (
	codigo 		BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome 		VARCHAR(100) NOT NULL,
	licenca		VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome, licenca) VALUES ('ARGUMENTO', null);
INSERT INTO cliente (nome, licenca) VALUES ('B3', null);
INSERT INTO cliente (nome, licenca) VALUES ('BANCO DO BRASIL', null);
INSERT INTO cliente (nome, licenca) VALUES ('BANCOOB', null);
INSERT INTO cliente (nome, licenca) VALUES ('BANESPREV', null);
INSERT INTO cliente (nome, licenca) VALUES ('BANRISUL ASSET', null);
INSERT INTO cliente (nome, licenca) VALUES ('BANRISUL CP', null);
INSERT INTO cliente (nome, licenca) VALUES ('BNP', null);
INSERT INTO cliente (nome, licenca) VALUES ('BRADESCO', null);
INSERT INTO cliente (nome, licenca) VALUES ('BRADESCO SEGUROS', null);
INSERT INTO cliente (nome, licenca) VALUES ('BRASILPREV', null);
INSERT INTO cliente (nome, licenca) VALUES ('FUNCESP', null);
INSERT INTO cliente (nome, licenca) VALUES ('IDL TRUST', null);
INSERT INTO cliente (nome, licenca) VALUES ('INDIGO', null);
INSERT INTO cliente (nome, licenca) VALUES ('INTRADER', null);
INSERT INTO cliente (nome, licenca) VALUES ('ITAÚ', null);
INSERT INTO cliente (nome, licenca) VALUES ('JP MORGAN', null);
INSERT INTO cliente (nome, licenca) VALUES ('MAXIMA', null);
INSERT INTO cliente (nome, licenca) VALUES ('MELLON', null);
INSERT INTO cliente (nome, licenca) VALUES ('SANTANDER ASSET', null);
INSERT INTO cliente (nome, licenca) VALUES ('SANTANDER CUSTÓDIA', null);
INSERT INTO cliente (nome, licenca) VALUES ('SANTANDER PRIVATE', null);
INSERT INTO cliente (nome, licenca) VALUES ('SICREDI', null);
INSERT INTO cliente (nome, licenca) VALUES ('SINGULARE', null);
INSERT INTO cliente (nome, licenca) VALUES ('SOLIDUS', null);
INSERT INTO cliente (nome, licenca) VALUES ('TOKIO MARINE', null);
INSERT INTO cliente (nome, licenca) VALUES ('UBS', null);
INSERT INTO cliente (nome, licenca) VALUES ('ÚNICA', null);