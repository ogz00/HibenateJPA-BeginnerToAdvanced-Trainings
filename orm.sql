-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema orm
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `orm` ;

-- -----------------------------------------------------
-- Schema orm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `orm` DEFAULT CHARACTER SET utf8 ;
USE `orm` ;

-- -----------------------------------------------------
-- Table `orm`.`bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`bank` ;

CREATE TABLE IF NOT EXISTS `orm`.`bank` (
  `BANK_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `ADDRESS_LINE_1` VARCHAR(100) NOT NULL,
  `ADDRESS_LINE_2` VARCHAR(100) NOT NULL,
  `CITY` VARCHAR(100) NOT NULL,
  `STATE` VARCHAR(2) NOT NULL,
  `ZIP_CODE` VARCHAR(5) NOT NULL,
  `IS_INTERNATIONAL` TINYINT(1) NOT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_DATE` DATETIME NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  `ADDRESS_TYPE` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`account` ;

CREATE TABLE IF NOT EXISTS `orm`.`account` (
  `ACCOUNT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `BANK_ID` BIGINT(20) NOT NULL,
  `ACCOUNT_TYPE` VARCHAR(45) NULL DEFAULT NULL,
  `NAME` VARCHAR(100) NOT NULL,
  `INITIAL_BALANCE` DECIMAL(10,2) NOT NULL,
  `CURRENT_BALANCE` DECIMAL(10,2) NOT NULL,
  `OPEN_DATE` DATE NOT NULL,
  `CLOSE_DATE` DATE NOT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_DATE` DATETIME NULL DEFAULT NULL,
  `CREATED_BY` VARCHAR(45) NULL DEFAULT NULL,
  `CREATED_DATE` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  INDEX `BANK_FK` (`BANK_ID` ASC),
  INDEX `ACCOUNT_TYPE_FK_idx` (`ACCOUNT_TYPE` ASC),
  CONSTRAINT `BANK_FK`
    FOREIGN KEY (`BANK_ID`)
    REFERENCES `orm`.`bank` (`BANK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`bank_contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`bank_contact` ;

CREATE TABLE IF NOT EXISTS `orm`.`bank_contact` (
  `BANK_ID` BIGINT(20) NOT NULL,
  `NAME` VARCHAR(100) NOT NULL,
  `POSITION_TYPE` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`, `NAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`budget` ;

CREATE TABLE IF NOT EXISTS `orm`.`budget` (
  `BUDGET_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `GOAL_AMOUNT` DECIMAL(10,2) NOT NULL,
  `PERIOD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BUDGET_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`transaction` ;

CREATE TABLE IF NOT EXISTS `orm`.`transaction` (
  `TRANSACTION_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` BIGINT(20) NOT NULL,
  `TRANSACTION_TYPE` VARCHAR(45) NOT NULL,
  `TITLE` VARCHAR(100) NOT NULL,
  `AMOUNT` DECIMAL(10,2) NOT NULL,
  `INITIAL_BALANCE` DECIMAL(10,2) NOT NULL,
  `CLOSING_BALANCE` DECIMAL(10,2) NOT NULL,
  `NOTES` MEDIUMTEXT NULL DEFAULT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_DATE` DATETIME NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  INDEX `ACCOUNT_FK2_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `ACCOUNT_FK2`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `orm`.`account` (`ACCOUNT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`budget_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`budget_transaction` ;

CREATE TABLE IF NOT EXISTS `orm`.`budget_transaction` (
  `TRANSACTION_ID` BIGINT(20) NOT NULL,
  `BUDGET_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`, `BUDGET_ID`),
  UNIQUE INDEX `TRANSACTION_ID_UNIQUE` (`TRANSACTION_ID` ASC),
  INDEX `BUDGET_FK_idx` (`BUDGET_ID` ASC),
  CONSTRAINT `BUDGET_FK`
    FOREIGN KEY (`BUDGET_ID`)
    REFERENCES `orm`.`budget` (`BUDGET_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TRANSACTION_FK2`
    FOREIGN KEY (`TRANSACTION_ID`)
    REFERENCES `orm`.`transaction` (`TRANSACTION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`finances_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`finances_user` ;

CREATE TABLE IF NOT EXISTS `orm`.`finances_user` (
  `USER_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(45) NOT NULL,
  `LAST_NAME` VARCHAR(45) NOT NULL,
  `BIRTH_DATE` DATE NOT NULL,
  `EMAIL_ADDRESS` VARCHAR(100) NOT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_DATE` DATETIME NOT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`credential`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`credential` ;

CREATE TABLE IF NOT EXISTS `orm`.`credential` (
  `CREDENTIAL_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` BIGINT(20) NOT NULL,
  `USERNAME` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`CREDENTIAL_ID`),
  UNIQUE INDEX `USER_ID_UNIQUE` (`USER_ID` ASC),
  CONSTRAINT `FINANCES_USER_FK`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `orm`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`user_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`user_account` ;

CREATE TABLE IF NOT EXISTS `orm`.`user_account` (
  `USER_ID` BIGINT(20) NOT NULL,
  `ACCOUNT_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`USER_ID`, `ACCOUNT_ID`),
  INDEX `ACCOUNT_FK_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `ACCOUNT_FK`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `orm`.`account` (`ACCOUNT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_FK`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `orm`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orm`.`user_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orm`.`user_address` ;

CREATE TABLE IF NOT EXISTS `orm`.`user_address` (
  `USER_ID` BIGINT(20) NOT NULL,
  `USER_ADDRESS_LINE_1` VARCHAR(100) NOT NULL,
  `USER_ADDRESS_LINE_2` VARCHAR(100) NOT NULL,
  `CITY` VARCHAR(100) NOT NULL,
  `STATE` VARCHAR(2) NOT NULL,
  `ZIP_CODE` VARCHAR(5) NOT NULL,
  `ADDRESS_TYPE` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ADDRESS_LINE_1`, `USER_ADDRESS_LINE_2`, `CITY`, `STATE`, `ZIP_CODE`, `USER_ID`),
  INDEX `fk_user_address_finances_user1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_user_address_finances_user1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `orm`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
