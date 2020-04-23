CREATE DATABASE mydb;
USE mydb;

CREATE TABLE `menu` (
  `Menu_Id` varchar(50) NOT NULL,
  `Menu_Name` varchar(50) DEFAULT NULL,
  `Menu_Level` int(11) NOT NULL,
  `Parent` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Menu_Id`),
  UNIQUE KEY `Menu_Name_UNIQUE` (`Menu_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
  `role_id` varchar(5) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `Role_Name_UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_menu` (
  `Role_Id` varchar(5) DEFAULT NULL,
  `Menu_Id` varchar(50) DEFAULT NULL,
  `Role_Menu_Id` varchar(6) NOT NULL,
  PRIMARY KEY (`Role_Menu_Id`),
  UNIQUE KEY `RM_Unique` (`Role_Id`,`Menu_Id`),
  KEY `Role_Id_idx` (`Role_Id`),
  KEY `Menu_Id_idx` (`Menu_Id`),
  CONSTRAINT `Menu_Id` FOREIGN KEY (`Menu_Id`) REFERENCES `menu` (`Menu_Id`),
  CONSTRAINT `Menu_Role_Id` FOREIGN KEY (`Role_Id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `user_id` varchar(5) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_pwd` varchar(60) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `User_Name_UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` varchar(5) DEFAULT NULL,
  `role_id` varchar(5) DEFAULT NULL,
  `user_role_id` varchar(6) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `UR_Unique` (`user_id`,`role_id`),
  KEY `Role_Id_idx` (`role_id`),
  KEY `User_Id_idx` (`user_id`),
  CONSTRAINT `User_Id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `User_Role_Id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `mydb`.`id_control_table` (
  `Table_Name` VARCHAR(30) NOT NULL,
  `Id_Prefix` VARCHAR(5) NOT NULL,
  `Id_Number` INT NOT NULL,
  `Id_Length` INT NOT NULL,
  `Id` VARCHAR(10) NOT NULL);
 
 
ALTER TABLE `mydb`.`id_control_table`
ADD PRIMARY KEY (`Table_Name`);
;


INSERT INTO `mydb`.`id_control_table` (`Table_Name`, `Id_Prefix`, `Id_Number`, `Id_Length`, `Id`) VALUES ('role', 'R', '0', '3', 'R000');
DELETE FROM `mydb`.`id_control_table` WHERE (`Table_Name` = 'user');
INSERT INTO `mydb`.`id_control_table` (`Table_Name`, `Id_Prefix`, `Id_Number`, `Id_Length`, `Id`) VALUES ('user', 'U', '0', '3', 'U000');
INSERT INTO `mydb`.`id_control_table` (`Table_Name`, `Id_Prefix`, `Id_Number`, `Id_Length`, `Id`) VALUES ('role_menu', 'RM', '0', '5', 'RM00000');
INSERT INTO `mydb`.`id_control_table` (`Table_Name`, `Id_Prefix`, `Id_Number`, `Id_Length`, `Id`) VALUES ('user_role', 'UR', '0', '5', 'UR00000');


SET GLOBAL log_bin_trust_function_creators = 1;


USE `mydb`;
DROP function IF EXISTS `Get_Next_ID`;

DELIMITER $$
USE `mydb`$$
CREATE FUNCTION `Get_Next_ID`(
In_Table_Name varchar(50)) RETURNS varchar(20) CHARSET utf8
BEGIN

DECLARE Var_ID_Prefix VARCHAR(5);
DECLARE Var_ID_Number INT ;
DECLARE Var_ID_Length INT DEFAULT 0;
DECLARE Var_ID VARCHAR(20) DEFAULT null;
    DECLARE Var_Today_YYYYMMDD VARCHAR(20) DEFAULT NULL;
DECLARE OUT_Id VARCHAR(20);

   
SELECT
`ID_Prefix`, `ID_Number`, `ID_Length`, `ID`
INTO Var_ID_Prefix , Var_ID_Number , Var_ID_Length , Var_ID FROM
id_control_table
WHERE
Table_Name = In_Table_Name;

-- Increment ID_Num by 1
SET Var_ID_Number = Var_ID_Number + 1;

-- Generate Id using Prefix + Incremental Id with padding "0"s
SET Out_ID = CONCAT(Var_ID_Prefix,LPAD(Var_ID_Number,Var_ID_Length,'0'));

-- Update wms_id_control with new Id
UPDATE id_control_table
SET
ID_Number = Var_ID_Number,
ID = Out_ID
WHERE
Table_Name = In_Table_Name;        
   
RETURN (Out_ID);

END$$

DELIMITER ;