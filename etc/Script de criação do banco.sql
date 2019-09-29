CREATE SCHEMA `controle_academico`;

CREATE TABLE `controle_academico`.`pessoa`(
	`id_pessoa` INT NOT NULL AUTO_INCREMENT,
	`matricula` INT NOT NULL UNIQUE,
	`nome` VARCHAR(255) NULL,
	`curso` VARCHAR(255) NULL,
	PRIMARY KEY (`id_pessoa`)
);

CREATE TABLE `controle_academico`.`projeto`(
	`id_projeto` INT NOT NULL AUTO_INCREMENT,
    `id_professor` INT NOT NULL,
	`titulo` VARCHAR(255) NOT NULL,
	`area` VARCHAR(255) NULL,
	`resumo` VARCHAR(3000) NULL,
	`palavra_chave1` VARCHAR(50) NULL,
	`palavra_chave2` VARCHAR(50) NULL,
	`palavra_chave3` VARCHAR(50) NULL,
	`url_documento` VARCHAR(50) NULL UNIQUE,
	PRIMARY KEY (`id_projeto`),
    FOREIGN KEY(`id_professor`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
    ON DELETE CASCADE
);

CREATE TABLE `controle_academico`.`endereco`(
	`id_endereco` INT NOT NULL AUTO_INCREMENT,
    `id_pessoa` INT NOT NULL,
    `rua` VARCHAR(255) NULL,
	`numero` VARCHAR(8) NULL,
	`cep` VARCHAR(14) NULL,
	`cidade` VARCHAR(50) NULL,
	`estado` VARCHAR(50) NULL,
	`pais` VARCHAR(50) NULL,
    PRIMARY KEY(`id_endereco`),
    FOREIGN KEY(`id_pessoa`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
    ON DELETE CASCADE
);

CREATE TABLE `controle_academico`.`aluno` (
  `id_aluno` INT NOT NULL,
  `cpf` VARCHAR(14) NULL,
  FOREIGN KEY(`id_aluno`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
  ON DELETE CASCADE
);
CREATE TABLE `controle_academico`.`professor`(
  `id_professor` INT NULL,
  FOREIGN KEY(`id_professor`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
  ON DELETE CASCADE
);
 
CREATE TABLE `controle_academico`.`aluno_projeto`(
  `id_aluno` INT NOT NULL,
  `id_projeto` INT NOT NULL,
  FOREIGN KEY(`id_aluno`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
  ON DELETE CASCADE,
  FOREIGN KEY(`id_projeto`) REFERENCES `controle_academico`.`projeto`(`id_projeto`)
  ON DELETE CASCADE
);

CREATE TABLE `controle_academico`.`nota`(
  `primeira_nota` INT NULL,
  `segunda_nota` INT NULL,
  `id_aluno` INT NOT NULL,
  FOREIGN KEY(`id_aluno`) REFERENCES `controle_academico`.`pessoa`(`id_pessoa`)
  ON DELETE CASCADE
);