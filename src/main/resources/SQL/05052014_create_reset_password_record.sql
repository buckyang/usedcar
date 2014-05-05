CREATE TABLE `NewTable` (
`active_code`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`expired_date`  bigint(11) NOT NULL ,
`account_id`  int(11) NOT NULL ,
`mail`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`account_id`),
FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT
;
