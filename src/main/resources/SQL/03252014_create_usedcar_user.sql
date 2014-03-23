CREATE USER 'usedcar'@'%' IDENTIFIED BY 'usedcar';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
    ON usedcar.*
    TO 'usedcar'@'%';