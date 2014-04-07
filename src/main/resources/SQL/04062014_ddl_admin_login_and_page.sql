-- -----------------------------------------------------
-- Table `usedcar`.`admin_page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usedcar`.`admin_page` (
  `page_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `url` VARCHAR(200) NULL,
  `rank` INT NULL,
  `type` TINYINT NULL,
  PRIMARY KEY (`page_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usedcar`.`admin_page_chd_page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usedcar`.`admin_page_chd_page` (
  `primary_page_id` INT NOT NULL,
  `child_page_id` INT NOT NULL,
  INDEX `fk_admin_page_chd_page_admin_page1_idx` (`primary_page_id` ASC),
  INDEX `fk_admin_page_chd_page_admin_page2_idx` (`child_page_id` ASC),
  PRIMARY KEY (`primary_page_id`, `child_page_id`),
  CONSTRAINT `fk_admin_page_chd_page_admin_page1`
    FOREIGN KEY (`primary_page_id`)
    REFERENCES `usedcar`.`admin_page` (`page_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admin_page_chd_page_admin_page2`
    FOREIGN KEY (`child_page_id`)
    REFERENCES `usedcar`.`admin_page` (`page_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usedcar`.`admin_page_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usedcar`.`admin_page_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `permission` VARCHAR(45) NULL,
  `page_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_admin_page_permission_admin_page1_idx` (`page_id` ASC),
  CONSTRAINT `fk_admin_page_permission_admin_page1`
    FOREIGN KEY (`page_id`)
    REFERENCES `usedcar`.`admin_page` (`page_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usedcar`.`admin_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usedcar`.`admin_account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `login_name` VARCHAR(45) NULL,
  `password` VARCHAR(500) NULL,
  `last_login_time` TIMESTAMP NULL,
  `locked` TINYINT(1) NULL,
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usedcar`.`admin_account_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usedcar`.`admin_account_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `permission` VARCHAR(45) NULL,
  `admin_account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_permission_admin_account1_idx` (`admin_account_id` ASC),
  CONSTRAINT `fk_account_permission_admin_account1`
    FOREIGN KEY (`admin_account_id`)
    REFERENCES `usedcar`.`admin_account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
