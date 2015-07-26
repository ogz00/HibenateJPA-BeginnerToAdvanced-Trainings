-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ifinances
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ifinances` ;

-- -----------------------------------------------------
-- Schema ifinances
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ifinances` DEFAULT CHARACTER SET utf8 ;
USE `ifinances` ;

-- -----------------------------------------------------
-- Table `ifinances`.`bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`bank` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`bank` (
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
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`account` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`account` (
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
    REFERENCES `ifinances`.`bank` (`BANK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`account_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`account_type` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`account_type` (
  `ACCOUNT_TYPE_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` DATE NULL DEFAULT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NULL DEFAULT NULL,
  `CREATED_DATE` DATE NULL DEFAULT NULL,
  `CREATED_BY` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_TYPE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`bank_contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`bank_contact` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`bank_contact` (
  `NAME` VARCHAR(100) NOT NULL,
  `POSITION_TYPE` VARCHAR(100) NULL DEFAULT NULL,
  `BANK_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`NAME`, `BANK_ID`),
  INDEX `fk_bank_contact_bank1_idx` (`BANK_ID` ASC),
  CONSTRAINT `fk_bank_contact_bank1`
    FOREIGN KEY (`BANK_ID`)
    REFERENCES `ifinances`.`bank` (`BANK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`portfolio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`portfolio` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`portfolio` (
  `PORTFOLIO_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PORTFOLIO_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`bond`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`bond` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`bond` (
  `INVESTMENT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `ISSUER` VARCHAR(100) NOT NULL,
  `PURCHASE_DATE` DATETIME NOT NULL,
  `VALUE` DECIMAL(10,2) NOT NULL,
  `INTEREST_RATE` DECIMAL(10,2) NOT NULL,
  `MATURITY_DATE` DATETIME NOT NULL,
  `PORTFOLIO_ID` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  INDEX `PORTFOLIO_FK2_idx` (`PORTFOLIO_ID` ASC),
  CONSTRAINT `PORTFOLIO_FK2`
    FOREIGN KEY (`PORTFOLIO_ID`)
    REFERENCES `ifinances`.`portfolio` (`PORTFOLIO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1454
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`budget` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`budget` (
  `BUDGET_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `GOAL_AMOUNT` DECIMAL(10,2) NOT NULL,
  `PERIOD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BUDGET_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`transaction` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`transaction` (
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
    REFERENCES `ifinances`.`account` (`ACCOUNT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`budget_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`budget_transaction` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`budget_transaction` (
  `TRANSACTION_ID` BIGINT(20) NOT NULL,
  `BUDGET_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`, `BUDGET_ID`),
  UNIQUE INDEX `TRANSACTION_ID_UNIQUE` (`TRANSACTION_ID` ASC),
  INDEX `BUDGET_FK_idx` (`BUDGET_ID` ASC),
  CONSTRAINT `BUDGET_FK`
    FOREIGN KEY (`BUDGET_ID`)
    REFERENCES `ifinances`.`budget` (`BUDGET_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TRANSACTION_FK2`
    FOREIGN KEY (`TRANSACTION_ID`)
    REFERENCES `ifinances`.`transaction` (`TRANSACTION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`finances_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`finances_user` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`finances_user` (
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
AUTO_INCREMENT = 63
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`credential`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`credential` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`credential` (
  `CREDENTIAL_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` BIGINT(20) NOT NULL,
  `USERNAME` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`CREDENTIAL_ID`),
  UNIQUE INDEX `USER_ID_UNIQUE` (`USER_ID` ASC),
  CONSTRAINT `FINANCES_USER_FK`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `ifinances`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 63
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`currency` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`currency` (
  `NAME` VARCHAR(45) NOT NULL,
  `COUNTRY_NAME` VARCHAR(45) NOT NULL,
  `SYMBOL` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`NAME`, `COUNTRY_NAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`ifinances_keys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`ifinances_keys` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`ifinances_keys` (
  `PK_NAME` VARCHAR(45) NOT NULL,
  `PK_VALUE` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`PK_NAME`),
  UNIQUE INDEX `PK_VALUE_UNIQUE` (`PK_VALUE` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`investment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`investment` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`investment` (
  `INVESTMENT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `PORTFOLIO_ID` BIGINT(20) NULL DEFAULT NULL,
  `NAME` VARCHAR(100) NULL DEFAULT NULL,
  `ISSUER` VARCHAR(100) NULL DEFAULT NULL,
  `PURCHASE_DATE` DATETIME NULL DEFAULT NULL,
  `VALUE` DECIMAL(10,2) NULL DEFAULT NULL,
  `INTEREST_RATE` DECIMAL(10,2) NULL DEFAULT NULL,
  `MATURITY_DATE` DATETIME NULL DEFAULT NULL,
  `SHARE_PRICE` DECIMAL(10,2) NULL DEFAULT NULL,
  `QUANTITY` DECIMAL(19,2) NULL DEFAULT NULL,
  `INVESTMENT_TYPE` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 152
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`market`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`market` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`market` (
  `MARKET_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `CURRENCY_NAME` VARCHAR(45) NOT NULL,
  `COUNTRY_NAME` VARCHAR(45) NOT NULL,
  `MARKET_NAME` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`MARKET_ID`),
  INDEX `CURRENCY_FK_idx` (`CURRENCY_NAME` ASC, `COUNTRY_NAME` ASC),
  CONSTRAINT `CURRENCY_FK`
    FOREIGN KEY (`CURRENCY_NAME` , `COUNTRY_NAME`)
    REFERENCES `ifinances`.`currency` (`NAME` , `COUNTRY_NAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`stock` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`stock` (
  `INVESTMENT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `ISSUER` VARCHAR(45) NOT NULL,
  `PURCHASE_DATE` DATETIME NOT NULL,
  `SHARE_PRICE` DECIMAL(10,2) NOT NULL,
  `QUANTITY` BIGINT(20) NOT NULL,
  `PORTFOLIO_ID` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  INDEX `PORTFOLIO_FK_idx` (`PORTFOLIO_ID` ASC),
  CONSTRAINT `PORTFOLIO_FK`
    FOREIGN KEY (`PORTFOLIO_ID`)
    REFERENCES `ifinances`.`portfolio` (`PORTFOLIO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1454
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`user_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`user_account` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`user_account` (
  `USER_ID` BIGINT(20) NOT NULL,
  `ACCOUNT_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`USER_ID`, `ACCOUNT_ID`),
  INDEX `ACCOUNT_FK_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `ACCOUNT_FK`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `ifinances`.`account` (`ACCOUNT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_FK`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `ifinances`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ifinances`.`user_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifinances`.`user_address` ;

CREATE TABLE IF NOT EXISTS `ifinances`.`user_address` (
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
    REFERENCES `ifinances`.`finances_user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `ifinances` ;

-- -----------------------------------------------------
-- Placeholder table for view `ifinances`.`v_user_credential`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ifinances`.`v_user_credential` (`user_id` INT, `FIRST_NAME` INT, `last_name` INT, `USERNAME` INT, `password` INT);

-- -----------------------------------------------------
-- View `ifinances`.`v_user_credential`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `ifinances`.`v_user_credential` ;
DROP TABLE IF EXISTS `ifinances`.`v_user_credential`;
USE `ifinances`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ifinances`.`v_user_credential` AS select `ifinances`.`finances_user`.`USER_ID` AS `user_id`,`ifinances`.`finances_user`.`FIRST_NAME` AS `FIRST_NAME`,`ifinances`.`finances_user`.`LAST_NAME` AS `last_name`,`ifinances`.`credential`.`USERNAME` AS `USERNAME`,`ifinances`.`credential`.`PASSWORD` AS `password` from (`ifinances`.`finances_user` join `ifinances`.`credential` on((`ifinances`.`finances_user`.`USER_ID` = `ifinances`.`credential`.`USER_ID`)));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
