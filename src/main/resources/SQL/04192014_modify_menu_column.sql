DROP table menu;

CREATE TABLE `menu` (
`menu_id`  int(11) NOT NULL AUTO_INCREMENT ,
`title`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`contents`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`parent_menu_id`  int(11) NULL DEFAULT NULL ,
`edit_all_property`  bit(1) NOT NULL DEFAULT b'0' ,
`display_order`  int(4) NOT NULL ,
PRIMARY KEY (`menu_id`),
FOREIGN KEY (`parent_menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `parent_menu_id` (`parent_menu_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=47
ROW_FORMAT=COMPACT
;