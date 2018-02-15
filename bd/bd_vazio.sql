-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 15/02/2018 às 22:07
-- Versão do servidor: 10.1.26-MariaDB
-- Versão do PHP: 7.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `es_na_palma_da_mao`
--
CREATE SCHEMA IF NOT EXISTS `es_na_palma_da_mao` DEFAULT CHARACTER SET utf8 ;
USE `es_na_palma_da_mao` ;
-- --------------------------------------------------------

--
-- Estrutura para tabela `candidato`
--

CREATE TABLE IF NOT EXISTS `candidato` (
  `candidato_id` int(11) NOT NULL,
  `candidato_nome_varchar` varchar(45) NOT NULL,
  `candidato_nascimento_date` date NOT NULL,
  `candidato_cpf_varchar` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidato_profissao`
--

CREATE TABLE IF NOT EXISTS `candidato_profissao` (
  `candidato_profissao_id` int(11) NOT NULL,
  `candidato_id` int(11) NOT NULL,
  `vaga_profissao_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `concurso`
--

CREATE TABLE IF NOT EXISTS `concurso` (
  `concurso_id` int(11) NOT NULL,
  `concurso_orgao_varchar` varchar(45) NOT NULL,
  `concurso_edital_varchar` varchar(10) NOT NULL,
  `concurso_codigo_varchar` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `concurso_vaga`
--

CREATE TABLE IF NOT EXISTS `concurso_vaga` (
  `concurso_vaga_id` int(11) NOT NULL,
  `vaga_profissao_id` int(11) NOT NULL,
  `concurso_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vaga_profissao`
--

CREATE TABLE IF NOT EXISTS `vaga_profissao` (
  `vaga_profissao_id` int(11) NOT NULL,
  `vaga_profissao_descricao_varchar` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `candidato`
--
ALTER TABLE `candidato`
  ADD PRIMARY KEY (`candidato_id`),
  ADD UNIQUE KEY `candidato_id_UNIQUE` (`candidato_id`),
  ADD UNIQUE KEY `candidato_cpf_varchar_UNIQUE` (`candidato_cpf_varchar`);

--
-- Índices de tabela `candidato_profissao`
--
ALTER TABLE `candidato_profissao`
  ADD PRIMARY KEY (`candidato_profissao_id`),
  ADD UNIQUE KEY `candidato_profissao_id_UNIQUE` (`candidato_profissao_id`),
  ADD UNIQUE KEY `uk_candidato_profissao` (`candidato_id`,`vaga_profissao_id`),
  ADD KEY `fk_candidato_profissao_candidato_idx` (`candidato_id`),
  ADD KEY `fk_candidato_profissao_vaga_profissao1_idx` (`vaga_profissao_id`);

--
-- Índices de tabela `concurso`
--
ALTER TABLE `concurso`
  ADD PRIMARY KEY (`concurso_id`),
  ADD UNIQUE KEY `concurso_id_UNIQUE` (`concurso_id`),
  ADD UNIQUE KEY `concurso_codigo_varchar_UNIQUE` (`concurso_codigo_varchar`);

--
-- Índices de tabela `concurso_vaga`
--
ALTER TABLE `concurso_vaga`
  ADD PRIMARY KEY (`concurso_vaga_id`),
  ADD UNIQUE KEY `concurso_vaga_id_UNIQUE` (`concurso_vaga_id`),
  ADD UNIQUE KEY `uk_concurso_vaga` (`concurso_id`,`vaga_profissao_id`),
  ADD KEY `fk_concurso_vaga_vaga_profissao1_idx` (`vaga_profissao_id`),
  ADD KEY `fk_concurso_vaga_concurso1_idx` (`concurso_id`);

--
-- Índices de tabela `vaga_profissao`
--
ALTER TABLE `vaga_profissao`
  ADD PRIMARY KEY (`vaga_profissao_id`),
  ADD UNIQUE KEY `vaga_profissao_id_UNIQUE` (`vaga_profissao_id`),
  ADD UNIQUE KEY `vaga_profissao_descricao_varchar_UNIQUE` (`vaga_profissao_descricao_varchar`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `candidato`
--
ALTER TABLE `candidato`
  MODIFY `candidato_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `candidato_profissao`
--
ALTER TABLE `candidato_profissao`
  MODIFY `candidato_profissao_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `concurso`
--
ALTER TABLE `concurso`
  MODIFY `concurso_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `concurso_vaga`
--
ALTER TABLE `concurso_vaga`
  MODIFY `concurso_vaga_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vaga_profissao`
--
ALTER TABLE `vaga_profissao`
  MODIFY `vaga_profissao_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `candidato_profissao`
--
ALTER TABLE `candidato_profissao`
  ADD CONSTRAINT `fk_candidato_profissao_candidato` FOREIGN KEY (`candidato_id`) REFERENCES `candidato` (`candidato_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_candidato_profissao_vaga_profissao1` FOREIGN KEY (`vaga_profissao_id`) REFERENCES `vaga_profissao` (`vaga_profissao_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `concurso_vaga`
--
ALTER TABLE `concurso_vaga`
  ADD CONSTRAINT `fk_concurso_vaga_concurso1` FOREIGN KEY (`concurso_id`) REFERENCES `concurso` (`concurso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_concurso_vaga_vaga_profissao1` FOREIGN KEY (`vaga_profissao_id`) REFERENCES `vaga_profissao` (`vaga_profissao_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
