-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema superherosightingstest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `superherosightingstest` ;

-- -----------------------------------------------------
-- Schema superherosightingstest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `superherosightingstest` DEFAULT CHARACTER SET utf8 ;
USE `superherosightingstest` ;

-- -----------------------------------------------------
-- Table `superherosightingstest`.`superpower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`superpower` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`superpower` (
  `superpowerId` INT NOT NULL AUTO_INCREMENT,
  `power` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`superpowerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightingstest`.`superhero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`superhero` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`superhero` (
  `superheroId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `superpowerId` INT NULL,
  PRIMARY KEY (`superheroId`),
  INDEX `fk_superhero_superpower1_idx` (`superpowerId` ASC) VISIBLE,
  CONSTRAINT `fk_superhero_superpower1`
    FOREIGN KEY (`superpowerId`)
    REFERENCES `superherosightingstest`.`superpower` (`superpowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightingstest`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`location` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`location` (
  `locationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `lattitude` VARCHAR(45) NULL,
  `longitude` VARCHAR(45) NULL,
  PRIMARY KEY (`locationId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightingstest`.`organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`organization` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`organization` (
  `organizationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `contactInfo` VARCHAR(45) NULL,
  PRIMARY KEY (`organizationId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightingstest`.`superhero_organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`superhero_organization` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`superhero_organization` (
  `superheroId` INT NOT NULL,
  `organizationId` INT NOT NULL,
  PRIMARY KEY (`superheroId`, `organizationId`),
  INDEX `fk_Superhero_has_Organization_Organization1_idx` (`organizationId` ASC) VISIBLE,
  INDEX `fk_Superhero_has_Organization_Superhero_idx` (`superheroId` ASC) VISIBLE,
  CONSTRAINT `fk_Superhero_has_Organization_Superhero`
    FOREIGN KEY (`superheroId`)
    REFERENCES `superherosightingstest`.`superhero` (`superheroId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Superhero_has_Organization_Organization1`
    FOREIGN KEY (`organizationId`)
    REFERENCES `superherosightingstest`.`organization` (`organizationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightingstest`.`sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightingstest`.`sighting` ;

CREATE TABLE IF NOT EXISTS `superherosightingstest`.`sighting` (
  `sightingId` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `superheroId` INT NOT NULL,
  `locationId` INT NOT NULL,
  PRIMARY KEY (`sightingId`),
  INDEX `fk_Sighting_Superhero1_idx` (`superheroId` ASC) VISIBLE,
  INDEX `fk_Sighting_Location1_idx` (`locationId` ASC) VISIBLE,
  CONSTRAINT `fk_Sighting_Superhero1`
    FOREIGN KEY (`superheroId`)
    REFERENCES `superherosightingstest`.`superhero` (`superheroId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sighting_Location1`
    FOREIGN KEY (`locationId`)
    REFERENCES `superherosightingstest`.`location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

use superherosightingstest;
insert into superhero (name, description) values ("batman", "The Dark Knight");
insert into superhero (name, description) values ("Superman", "Man of Steel");
select * from superhero;
select * from organization;
select * from sighting;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;