-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema es_na_palma_da_mao
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema es_na_palma_da_mao
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `es_na_palma_da_mao` DEFAULT CHARACTER SET utf8 ;
USE `es_na_palma_da_mao` ;

-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`candidato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`candidato` (
  `candidato_id` INT NOT NULL AUTO_INCREMENT,
  `candidato_nome_varchar` VARCHAR(45) NOT NULL,
  `candidato_nascimento_date` DATE NOT NULL,
  `candidato_cpf_varchar` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`candidato_id`),
  UNIQUE INDEX `candidato_id_UNIQUE` (`candidato_id` ASC),
  UNIQUE INDEX `candidato_cpf_varchar_UNIQUE` (`candidato_cpf_varchar` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`concurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`concurso` (
  `concurso_id` INT NOT NULL AUTO_INCREMENT,
  `concurso_orgao_varchar` VARCHAR(45) NOT NULL,
  `concurso_edital_varchar` VARCHAR(10) NOT NULL,
  `concurso_codigo_varchar` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`concurso_id`),
  UNIQUE INDEX `concurso_id_UNIQUE` (`concurso_id` ASC),
  UNIQUE INDEX `concurso_codigo_varchar_UNIQUE` (`concurso_codigo_varchar` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`vaga_profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`vaga_profissao` (
  `vaga_profissao_id` INT NOT NULL AUTO_INCREMENT,
  `vaga_profissao_descricao_varchar` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`vaga_profissao_id`),
  UNIQUE INDEX `vaga_profissao_id_UNIQUE` (`vaga_profissao_id` ASC),
  UNIQUE INDEX `vaga_profissao_descricao_varchar_UNIQUE` (`vaga_profissao_descricao_varchar` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`candidato_profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`candidato_profissao` (
  `candidato_profissao_id` INT NOT NULL AUTO_INCREMENT,
  `candidato_id` INT NOT NULL,
  `vaga_profissao_id` INT NOT NULL,
  PRIMARY KEY (`candidato_profissao_id`),
  UNIQUE INDEX `candidato_profissao_id_UNIQUE` (`candidato_profissao_id` ASC),
  INDEX `fk_candidato_profissao_candidato_idx` (`candidato_id` ASC),
  INDEX `fk_candidato_profissao_vaga_profissao1_idx` (`vaga_profissao_id` ASC),
  CONSTRAINT `fk_candidato_profissao_candidato`
    FOREIGN KEY (`candidato_id`)
    REFERENCES `es_na_palma_da_mao`.`candidato` (`candidato_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_profissao_vaga_profissao1`
    FOREIGN KEY (`vaga_profissao_id`)
    REFERENCES `es_na_palma_da_mao`.`vaga_profissao` (`vaga_profissao_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`concurso_vaga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`concurso_vaga` (
  `concurso_vaga_id` INT NOT NULL AUTO_INCREMENT,
  `vaga_profissao_id` INT NOT NULL,
  `concurso_id` INT NOT NULL,
  PRIMARY KEY (`concurso_vaga_id`),
  UNIQUE INDEX `concurso_vaga_id_UNIQUE` (`concurso_vaga_id` ASC),
  INDEX `fk_concurso_vaga_vaga_profissao1_idx` (`vaga_profissao_id` ASC),
  INDEX `fk_concurso_vaga_concurso1_idx` (`concurso_id` ASC),
  CONSTRAINT `fk_concurso_vaga_vaga_profissao1`
    FOREIGN KEY (`vaga_profissao_id`)
    REFERENCES `es_na_palma_da_mao`.`vaga_profissao` (`vaga_profissao_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concurso_vaga_concurso1`
    FOREIGN KEY (`concurso_id`)
    REFERENCES `es_na_palma_da_mao`.`concurso` (`concurso_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
