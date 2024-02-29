
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date_cr` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_up` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `owner` int NOT NULL
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date_cr` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
