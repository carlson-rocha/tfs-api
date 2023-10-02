ALTER TABLE situacao_tarefa ADD COLUMN aberto BIT null;

UPDATE situacao_tarefa SET aberto = 1 WHERE nome <> "Encerrado";
UPDATE situacao_tarefa SET aberto = 0 WHERE nome = "Encerrado";

