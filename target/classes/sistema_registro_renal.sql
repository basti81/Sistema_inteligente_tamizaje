-- MySQL Script generated by MySQL Workbench
-- Wed Dec 15 14:53:55 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sistema_registro_renal
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sistema_registro_renal` ;

-- -----------------------------------------------------
-- Schema sistema_registro_renal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema_registro_renal` DEFAULT CHARACTER SET utf8 ;
USE `sistema_registro_renal` ;

-- -----------------------------------------------------
-- Table `sistema_registro_renal`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_registro_renal`.`usuario` ;

CREATE TABLE IF NOT EXISTS `sistema_registro_renal`.`usuario` (
  `id_usuario` BIGINT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_registro_renal`.`paciente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_registro_renal`.`paciente` ;

CREATE TABLE IF NOT EXISTS `sistema_registro_renal`.`paciente` (
  `id_paciente` BIGINT NOT NULL AUTO_INCREMENT,
  `id_usuario` BIGINT NOT NULL,
  `region` VARCHAR(45) NULL,
  `comuna` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `prevision` VARCHAR(45) NULL,
  `fecha_nac` DATE NOT NULL,
  PRIMARY KEY (`id_paciente`),
  INDEX `fk_usuario_paciente_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_paciente`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `sistema_registro_renal`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_registro_renal`.`filiacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_registro_renal`.`filiacion` ;

CREATE TABLE IF NOT EXISTS `sistema_registro_renal`.`filiacion` (
  `id_filiacion` BIGINT NOT NULL AUTO_INCREMENT,
  `id_paciente` BIGINT NULL,
  `sexo` VARCHAR(5) NOT NULL,
  `raza` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id_filiacion`),
  INDEX `fk_paciente_filiacion_idx` (`id_paciente` ASC) VISIBLE,
  CONSTRAINT `fk_paciente_filiacion`
    FOREIGN KEY (`id_paciente`)
    REFERENCES `sistema_registro_renal`.`paciente` (`id_paciente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;