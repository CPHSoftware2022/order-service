-- MySQL Script generated by MySQL Workbench
-- Mon Dec 12 19:50:39 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema order_service
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `order_service` ;

-- -----------------------------------------------------
-- Schema order_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `order_service` DEFAULT CHARACTER SET utf8 ;
USE `order_service` ;

-- -----------------------------------------------------
-- Table `order_service`.`customer_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_service`.`customer_order` ;

CREATE TABLE IF NOT EXISTS `order_service`.`customer_order` (
   `customer_order_id` INT NOT NULL AUTO_INCREMENT,
   `order_start` DATETIME NULL,
   `order_end` DATETIME NULL,
   `total_price` DOUBLE NOT NULL,
   `accepted` TINYINT NULL,
   `canceled_reason` LONGTEXT NULL,
   `delivered` TINYINT NULL,
   `customer_id` INT NOT NULL,
   `restaurant_id` INT NOT NULL,
   `feedback_id` INT NULL,
   `employee_id` INT NULL,
   PRIMARY KEY (`customer_order_id`),
   UNIQUE INDEX `idcustomer_order_id_UNIQUE` (`customer_order_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_service`.`customerOrderItems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_service`.`customerOrderItems` ;

CREATE TABLE IF NOT EXISTS `order_service`.`customerOrderItems` (
   `customer_order_id` INT NOT NULL,
   `item_id` INT NOT NULL,
   INDEX `FK_customer_order_id_idx` (`customer_order_id` ASC) VISIBLE,
   CONSTRAINT `FK_customer_order_id`
       FOREIGN KEY (`customer_order_id`)
           REFERENCES `order_service`.`customer_order` (`customer_order_id`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
