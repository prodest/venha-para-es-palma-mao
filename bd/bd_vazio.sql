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
  `idcandidato` INT NOT NULL AUTO_INCREMENT,
  `candidato_nome_varchar` VARCHAR(45) NOT NULL,
  `candidato_nasciment_date` DATE NOT NULL,
  `candidato_cpf_int` INT(11) NOT NULL,
  PRIMARY KEY (`idcandidato`),
  UNIQUE INDEX `idcandidato_UNIQUE` (`idcandidato` ASC),
  UNIQUE INDEX `candidato_cpf_int_UNIQUE` (`candidato_cpf_int` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`concurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`concurso` (
  `idconcurso` INT NOT NULL AUTO_INCREMENT,
  `concurso_orgao_varchar` VARCHAR(45) NOT NULL,
  `concurso_edital_varchar` VARCHAR(10) NOT NULL,
  `concurso_codigo_int` INT(11) NOT NULL,
  PRIMARY KEY (`idconcurso`),
  UNIQUE INDEX `idconcurso_UNIQUE` (`idconcurso` ASC),
  UNIQUE INDEX `concurso_codigo_int_UNIQUE` (`concurso_codigo_int` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`vaga_profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`vaga_profissao` (
  `idvaga_profissao` INT NOT NULL AUTO_INCREMENT,
  `vaga_profissao_descrição_varchar` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idvaga_profissao`),
  UNIQUE INDEX `idvaga_profissao_UNIQUE` (`idvaga_profissao` ASC),
  UNIQUE INDEX `vaga_profissao_descrição_varchar_UNIQUE` (`vaga_profissao_descrição_varchar` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`candidato_profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`candidato_profissao` (
  `idcandidato_profissao` INT NOT NULL AUTO_INCREMENT,
  `candidato_id` INT NOT NULL,
  `vaga_profissao_id` INT NOT NULL,
  PRIMARY KEY (`idcandidato_profissao`),
  UNIQUE INDEX `idcandidato_profissao_UNIQUE` (`idcandidato_profissao` ASC),
  INDEX `fk_candidato_profissao_candidato_idx` (`candidato_id` ASC),
  INDEX `fk_candidato_profissao_vaga_profissao1_idx` (`vaga_profissao_id` ASC),
  CONSTRAINT `fk_candidato_profissao_candidato`
    FOREIGN KEY (`candidato_id`)
    REFERENCES `es_na_palma_da_mao`.`candidato` (`idcandidato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_profissao_vaga_profissao1`
    FOREIGN KEY (`vaga_profissao_id`)
    REFERENCES `es_na_palma_da_mao`.`vaga_profissao` (`idvaga_profissao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `es_na_palma_da_mao`.`concurso_vaga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `es_na_palma_da_mao`.`concurso_vaga` (
  `idconcurso_vaga` INT NOT NULL AUTO_INCREMENT,
  `vaga_profissao_id` INT NOT NULL,
  `concurso_id` INT NOT NULL,
  PRIMARY KEY (`idconcurso_vaga`),
  UNIQUE INDEX `idconcurso_vaga_UNIQUE` (`idconcurso_vaga` ASC),
  INDEX `fk_concurso_vaga_vaga_profissao1_idx` (`vaga_profissao_id` ASC),
  INDEX `fk_concurso_vaga_concurso1_idx` (`concurso_id` ASC),
  CONSTRAINT `fk_concurso_vaga_vaga_profissao1`
    FOREIGN KEY (`vaga_profissao_id`)
    REFERENCES `es_na_palma_da_mao`.`vaga_profissao` (`idvaga_profissao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concurso_vaga_concurso1`
    FOREIGN KEY (`concurso_id`)
    REFERENCES `es_na_palma_da_mao`.`concurso` (`idconcurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
