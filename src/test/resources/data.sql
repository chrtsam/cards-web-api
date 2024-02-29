INSERT INTO `users` (`email`,`role`,`password`) VALUES ('member_one@company.gr','Member','$2a$10$XRdIQpGplY..Wl5HGax/ae4sJA08NiFP40HWE0WS6pAGG/WtACL1y');  -- 1
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_1','Nice big description of task 1','#cedce6','In Progress',1); -- 1
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_2','Nice small description of task 2','#a78faf','To Do',1); -- 2
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_3','Nice lazy description of task 3','#147274','Done',1); -- 3
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_4','Nice professional description of task 4','#0e2b7b','Done',1); -- 4

INSERT INTO `users` (`email`,`role`,`password`) VALUES ('member_two@company.gr','Member','$2a$10$XRdIQpGplY..Wl5HGax/ae4sJA08NiFP40HWE0WS6pAGG/WtACL1y'); -- 2
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_5','Nice big description of task 1','#b2a0b2','In Progress',2); -- 5
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_6','Nice small description of task 2','#175676','To Do',2); -- 6
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_7','Nice lazy description of task 3','#63080c','Done',2); -- 7
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_8','Nice professional description of task 4','#cb2351','To Do',2); -- 8

INSERT INTO `users` (`email`,`role`,`password`) VALUES ('admin@company.gr','Admin','$2a$10$XRdIQpGplY..Wl5HGax/ae4sJA08NiFP40HWE0WS6pAGG/WtACL1y'); -- 3
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_9','Nice professional description of task 4','#c8d7eb','To Do',3); -- 9
INSERT INTO `tasks` (`name`,`description`,`color`,`status`,`owner`) VALUES ('Task_10','Nice professional description of task 4','#a78faf','To Do',3); -- 10

