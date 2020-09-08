-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema superherosightings
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `superherosightings` ;

-- -----------------------------------------------------
-- Schema superherosightings
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `superherosightings` DEFAULT CHARACTER SET utf8 ;
USE `superherosightings` ;

-- -----------------------------------------------------
-- Table `superherosightings`.`superpower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`superpower` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`superpower` (
  `superpowerId` INT NOT NULL AUTO_INCREMENT,
  `power` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`superpowerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightings`.`superhero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`superhero` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`superhero` (
  `superheroId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `superpowerId` INT NULL,
  PRIMARY KEY (`superheroId`),
  INDEX `fk_superhero_superpower1_idx` (`superpowerId` ASC) VISIBLE,
  CONSTRAINT `fk_superhero_superpower1`
    FOREIGN KEY (`superpowerId`)
    REFERENCES `superherosightings`.`superpower` (`superpowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightings`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`location` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`location` (
  `locationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `lattitude` VARCHAR(45) NULL,
  `longitude` VARCHAR(45) NULL,
  PRIMARY KEY (`locationId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightings`.`organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`organization` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`organization` (
  `organizationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `contactInfo` VARCHAR(45) NULL,
  PRIMARY KEY (`organizationId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightings`.`superhero_organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`superhero_organization` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`superhero_organization` (
  `superheroId` INT NOT NULL,
  `organizationId` INT NOT NULL,
  PRIMARY KEY (`superheroId`, `organizationId`),
  INDEX `fk_Superhero_has_Organization_Organization1_idx` (`organizationId` ASC) VISIBLE,
  INDEX `fk_Superhero_has_Organization_Superhero_idx` (`superheroId` ASC) VISIBLE,
  CONSTRAINT `fk_Superhero_has_Organization_Superhero`
    FOREIGN KEY (`superheroId`)
    REFERENCES `superherosightings`.`superhero` (`superheroId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Superhero_has_Organization_Organization1`
    FOREIGN KEY (`organizationId`)
    REFERENCES `superherosightings`.`organization` (`organizationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `superherosightings`.`sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `superherosightings`.`sighting` ;

CREATE TABLE IF NOT EXISTS `superherosightings`.`sighting` (
  `sightingId` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `superheroId` INT NOT NULL,
  `locationId` INT NOT NULL,
  PRIMARY KEY (`sightingId`),
  INDEX `fk_Sighting_Superhero1_idx` (`superheroId` ASC) VISIBLE,
  INDEX `fk_Sighting_Location1_idx` (`locationId` ASC) VISIBLE,
  CONSTRAINT `fk_Sighting_Superhero1`
    FOREIGN KEY (`superheroId`)
    REFERENCES `superherosightings`.`superhero` (`superheroId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sighting_Location1`
    FOREIGN KEY (`locationId`)
    REFERENCES `superherosightings`.`location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

use superherosightings;
insert into superhero (name, description) values ("batman", "The Dark Knight");
insert into superhero (name, description) values ("Superman", "Man of Steel");
select * from superhero;
select * from organization;
select * from superpower;
select * from location;
select * from sighting;
SELECT * FROM superhero_organization;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



