ALTER TABLE tarefa ADD COLUMN prioridade varchar(30) null;
UPDATE tarefa SET prioridade = 'NORMAL';
