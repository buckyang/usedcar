CREATE TABLE IF NOT EXISTS `usedcar`.`menu` (
  `menu_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `order` TINYINT(4) NULL DEFAULT NULL,
  `link_url` VARCHAR(200) NULL DEFAULT NULL,
  `parent_menu_id` INT(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  INDEX `fk_menu_menu1_idx` (`parent_menu_id` ASC),
  CONSTRAINT `fk_menu_menu1`
    FOREIGN KEY (`parent_menu_id`)
    REFERENCES `usedcar`.`menu` (`menu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `usedcar`.`static_page` (
  `page_id` VARCHAR(20) NOT NULL,
  `url` VARCHAR(500) NULL,
  `creation_date` TIMESTAMP NULL,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(500) NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `enabled` TINYINT(1) NULL,
  `keyword` VARCHAR(500) NULL,
  `title` VARCHAR(500) NULL,
  `content` TEXT NULL,
  `rich_text_enabled` TINYINT(1) NULL,
  `last_modified` TIMESTAMP NULL,
  `rank` TINYINT NULL,
  `type` TINYINT NULL,
  PRIMARY KEY (`page_id`))
ENGINE = InnoDB;


CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`type`  tinyint(4) NULL DEFAULT NULL ,
`verification_code`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`valid_date`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`used`  bit(1) NULL DEFAULT NULL ,
`principle`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`used_date`  timestamp NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB