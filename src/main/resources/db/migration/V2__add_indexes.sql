ALTER TABLE `card_db`.`tasks` 
ADD INDEX `owner_color_idx` (`owner` ASC, `color` ASC) VISIBLE,
ADD INDEX `owner_name_idx` (`owner` ASC, `name` ASC) VISIBLE,
ADD INDEX `owner_status_idx` (`owner` ASC, `status` ASC) VISIBLE,
ADD INDEX `owner_datecr_idx` (`owner` ASC, `date_cr` ASC) VISIBLE;
