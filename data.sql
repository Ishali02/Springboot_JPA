
INSERT INTO `mydb`.`user` (`user_id`,`user_name`,`user_pwd`) VALUES ('U001','admin','admin');

INSERT INTO `mydb`.`role` (`role_id`,`role_name`) VALUES ('R001','Admin');

INSERT INTO `mydb`.`user_role` (`user_id`,`role_id`,`user_role_id`) VALUES ('U001','R001','UR001');

INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('addRoleId','Add Role',2,'adminId');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('addRoleMenuId','Add RoleMenu',2,'adminId');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('addUserId','Add User',2,'adminId');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('adminId','Admin',1,NULL);
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('homeId','Home',1,NULL);
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('page11Id','Page1-1',2,'page1Id');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('page12Id','Page1-2',2,'page1Id');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('page13Id','Page1-3',2,'page1Id');
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('page1Id','Page1',1,NULL);
INSERT INTO `mydb`.`menu` (`Menu_Id`,`Menu_Name`,`Menu_Level`,`Parent`) VALUES ('page2Id','Page2',1,NULL);


INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','addRoleId','RM009');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','addRoleMenuId','RM010');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','addUserId','RM008');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','adminId','RM007');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','homeId','RM001');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','page11Id','RM003');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','page12Id','RM004');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','page13Id','RM005');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','page1Id','RM002');
INSERT INTO `mydb`.`role_menu` (`Role_Id`,`Menu_Id`,`Role_Menu_Id`) VALUES ('R001','page2Id','RM006');

