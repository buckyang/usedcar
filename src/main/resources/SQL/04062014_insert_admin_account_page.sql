INSERT INTO `admin_account` (`account_id`,`login_name`,`password`,`last_login_time`,`locked`) VALUES (1,'admin','kS5ylYVpuSEjnR2THUfC20eDaby3jjAii+P/RcXpe24x1FtD7Ckg99QmjP4rzmD7oY6qW5Nw522GSvWSG8p7EhGQcRnOfWPZOlPAuwyBSXHFJPVBpx+C7qfHFYeT0ZxNpSypvyzs+6S1l55VdVA5a/FcdWjw0u4iLZv6l/IgZm4=','2014-04-06 17:52:46',0);
INSERT INTO `admin_account_permission` (`id`,`permission`,`admin_account_id`) VALUES (1,'admin',1);
INSERT INTO `admin_account_permission` (`id`,`permission`,`admin_account_id`) VALUES (2,'merchandise',1);

INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (1,'页面管理','页面管理','',1,1);
INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (2,'静态页面管理','静态页面管理','/admin/staticPage/index',1,1);
INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (3,'菜单管理','菜单管理','/admin/manageMenu',2,1);
INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (4,'查询静态页面','查询静态页面','/admin/staticPage/queryPage',1,2);
INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (5,'添加静态页面','添加静态页面','/admin/staticPage/addPage',2,2);
INSERT INTO `admin_page` (`page_id`,`name`,`description`,`url`,`rank`,`type`) VALUES (6,'删除静态页面','删除静态页面','/admin/staticPage/deletePage',3,2);

INSERT INTO `admin_page_chd_page` (`primary_page_id`,`child_page_id`) VALUES (1,2);
INSERT INTO `admin_page_chd_page` (`primary_page_id`,`child_page_id`) VALUES (1,3);
INSERT INTO `admin_page_chd_page` (`primary_page_id`,`child_page_id`) VALUES (2,4);
INSERT INTO `admin_page_chd_page` (`primary_page_id`,`child_page_id`) VALUES (2,5);
INSERT INTO `admin_page_chd_page` (`primary_page_id`,`child_page_id`) VALUES (2,6);

INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (1,'admin',1);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (2,'merchandise',1);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (3,'admin',2);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (4,'merchandise',2);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (5,'admin',3);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (6,'merchandise',3);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (7,'admin',4);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (8,'merchandise',4);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (9,'admin',5);
INSERT INTO `admin_page_permission` (`id`,`permission`,`page_id`) VALUES (10,'admin',6);