CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root` 
    SQL SECURITY DEFINER
VIEW `v_user_credential` AS
    SELECT 
        `ifinances`.`finances_user`.`USER_ID` AS `user_id`,
        `ifinances`.`finances_user`.`FIRST_NAME` AS `FIRST_NAME`,
        `ifinances`.`finances_user`.`LAST_NAME` AS `last_name`,
        `ifinances`.`credential`.`USERNAME` AS `USERNAME`,
        `ifinances`.`credential`.`PASSWORD` AS `password`
    FROM
        (`finances_user`
        JOIN `credential` ON ((`ifinances`.`finances_user`.`USER_ID` = `ifinances`.`credential`.`USER_ID`)))
