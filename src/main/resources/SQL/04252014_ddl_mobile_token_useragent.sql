delete from mobile_token where account_id < 1000000;
delete from account where account_id < 1000000;
ALTER TABLE `mobile_token` ADD user_agent VARCHAR(500);
ALTER TABLE `account` MODIFY `email` VARCHAR(45) NULL ;