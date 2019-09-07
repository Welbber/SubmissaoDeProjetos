CREATE SCHEMA `controle_academico` ;

CREATE TABLE `controle_academico`.`alunos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(12) NOT NULL,
  `nome` VARCHAR(200) NULL,
  `cpf` VARCHAR(14) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `controle_academico`.`alunos` (`matricula`, `nome`, `cpf`) VALUES ('201720130022', 'Angelo Roncalli', '365.456.678-30');

