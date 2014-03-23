CREATE TABLE IF NOT EXISTS `usedcar`.`mobile_token` (
  `access_token` VARCHAR(50) NOT NULL,
  `valid_date` TIMESTAMP NULL,
  `account_id` INT NOT NULL,
  `client_identifier` VARCHAR(50) NULL,
  PRIMARY KEY (`access_token`),
  UNIQUE INDEX `access_token_UNIQUE` (`access_token` ASC),
  INDEX `fk_mobile_token_account1_idx` (`account_id` ASC),
  CONSTRAINT `fk_mobile_token_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `usedcar`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB