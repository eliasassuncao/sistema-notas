CREATE DATABASE `sistema-alunos`;

USE `sistema-alunos`;

CREATE TABLE aluno (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  sobrenome VARCHAR(45) NOT NULL,
  nota integer NOT NULL,
  dataNascimento DATE NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

SELECT * FROM aluno;

