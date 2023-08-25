-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dualitydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dualitydb` ;

-- -----------------------------------------------------
-- Schema dualitydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dualitydb` DEFAULT CHARACTER SET utf8 ;
USE `dualitydb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `avatar` VARCHAR(500) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NOT NULL,
  `date_of_birth` DATE NULL,
  `about_me` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `active` TINYINT NULL,
  `create_date` DATETIME NOT NULL,
  `message` TEXT NULL,
  `anonymous` TINYINT NULL,
  `personal` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL,
  `body` TEXT NULL,
  `active` TINYINT NULL,
  `flagged` TINYINT NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `behavior_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `behavior_type` ;

CREATE TABLE IF NOT EXISTS `behavior_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `behavior`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `behavior` ;

CREATE TABLE IF NOT EXISTS `behavior` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `severity` INT NOT NULL,
  `behavior_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_behavior_behavior_type1_idx` (`behavior_type_id` ASC),
  CONSTRAINT `fk_behavior_behavior_type1`
    FOREIGN KEY (`behavior_type_id`)
    REFERENCES `behavior_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `direct_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `direct_message` ;

CREATE TABLE IF NOT EXISTS `direct_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `create_date` DATETIME NOT NULL,
  `active` TINYINT NULL,
  `sender_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_direct_message_user1_idx` (`sender_id` ASC),
  INDEX `fk_direct_message_user2_idx` (`recipient_id` ASC),
  CONSTRAINT `fk_direct_message_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direct_message_user2`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `behavior_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `behavior_report` ;

CREATE TABLE IF NOT EXISTS `behavior_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `behavior_id` INT NOT NULL,
  `intensity` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_behavior_report_user1_idx` (`user_id` ASC),
  INDEX `fk_behavior_report_behavior1_idx` (`behavior_id` ASC),
  CONSTRAINT `fk_behavior_report_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_behavior_report_behavior1`
    FOREIGN KEY (`behavior_id`)
    REFERENCES `behavior` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `behavior_report_remark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `behavior_report_remark` ;

CREATE TABLE IF NOT EXISTS `behavior_report_remark` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `remarks` TEXT NULL,
  `create_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_behavior_report_remarks_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_behavior_report_remarks_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flagged_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `flagged_post` ;

CREATE TABLE IF NOT EXISTS `flagged_post` (
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`post_id`, `user_id`),
  INDEX `fk_post_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_post_has_user_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_post_has_user_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flagged_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `flagged_comment` ;

CREATE TABLE IF NOT EXISTS `flagged_comment` (
  `comment_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`, `user_id`),
  INDEX `fk_comment_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_has_user_comment1_idx` (`comment_id` ASC),
  CONSTRAINT `fk_comment_has_user_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource` ;

CREATE TABLE IF NOT EXISTS `resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resource_link` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  `create_date` DATETIME NULL,
  `active` TINYINT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resource_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_resource_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS dualityuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'dualityuser'@'localhost' IDENTIFIED BY 'dualityuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'dualityuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'admin@aol.com', 'cat.png', 'joe', 'smith', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '1995-08-23', 'Administrator of administrators');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (2, 'provider', '1234', 1, 'provider', 'provider@aol.com', 'whale.png', 'amy', 'kenny', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '1985-01-12', 'Provider provider');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (3, 'user0', '1234', 1, 'user', 'user0@aol.com', 'squirrel.png', 'shelia', 'kennedy', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '2000-07-15', 'User of users');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (4, 'user1', '1234', 1, 'user', 'user1@aol.com', 'bird.png', 'Alex', 'Apakidze', '2023-08-22 17:30:00', '2023-08-25 17:30:00', '1990-08-25', 'Super cool and chill dude.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (5, 'user2', '1234', 1, 'user', 'user2@aol.com', 'snake.png', 'Justin', 'Martz', '2023-08-22 18:30:00', '2023-08-25 17:30:00', '1985-06-20', 'Awesome and fantastic dude.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (6, 'user3', '1234', 1, 'user', 'user3@aol.com', 'jellyfish.png', 'Jay', 'Perkins', '2023-08-22 19:30:00', '2023-08-25 17:30:00', '1996-07-15', 'Totally awesome super cool.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (7, 'user4', '1234', 1, 'user', 'user4@aol.com', 'bobcat.png', 'Sam', 'Fath', '2023-08-22 20:30:00', '2023-08-22 17:30:00', '1995-01-12', 'Pretty cool dude.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (1, 'how are ya\'ll feeling today', 4, 1, '2023-08-25 11:40:00', 'feeling good today', 1, 2);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (2, 'ya\'ll suck', 5, 1, '2023-08-25 11:25:00', 'booo', 1, 2);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (3, 'day out with family', 6, 1, '2023-08-25 12:20:00', 'went to the park today', 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (1, '2023-08-25 11:15:00', 'you suck', 1, 1, 5, 1);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (2, '2023-08-25 11:20:00', 'you rock', 1, 2, 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `behavior_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `behavior_type` (`id`, `name`, `description`) VALUES (1, 'depressive', 'Continued feelings of sadness, hopelessness, pessimism, emptiness. Fatigue, lack of energy. Insomnia or other sleep issues, such as waking up very early or sleeping too much. Anxiety, irritability, restlessness.');
INSERT INTO `behavior_type` (`id`, `name`, `description`) VALUES (2, 'mania', 'Abnormally upbeat, jumpy or wired. Increased activity, energy or agitation. Exaggerated sense of well-being and self-confidence.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `behavior`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (1, 'increased need for sleep', 1, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (2, 'slowed behavior', 1, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (3, 'decreased ability to think', 1, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (4, 'decrease in appetite', 2, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (5, 'thinking about, planning or attemping suicide', 3, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (6, 'loss of interest in almost all activities', 2, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (7, 'feeling sad, empty, hopeless or tearful', 2, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (8, 'decreased energy, feeling tired', 1, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (9, 'feeling no pleasure in all, or almost all activities', 3, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (10, 'feeling guilt and despare', 2, 1);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (11, 'restlessness and agitation', 1, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (12, 'overconfidence', 1, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (13, 'impulsive and risky behavior', 2, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (14, 'feeling full of energy', 1, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (15, 'being delusional, having hallucinations or disturbed thinking', 3, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (16, 'doing things that have disastrous consequences - such as spending large sums of money', 3, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (17, 'withdrawn from family and friends', 2, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (18, 'racing thoughts and accelerated speech', 1, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (19, 'decreased need for sleep', 2, 2);
INSERT INTO `behavior` (`id`, `name`, `severity`, `behavior_type_id`) VALUES (20, 'thinking about or planning to hurt others', 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `direct_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `direct_message` (`id`, `message`, `create_date`, `active`, `sender_id`, `recipient_id`) VALUES (1, 'Yo what\'s up!', '2023-08-23 17:45:00', 1, 1, 1);
INSERT INTO `direct_message` (`id`, `message`, `create_date`, `active`, `sender_id`, `recipient_id`) VALUES (2, 'Not much!', '2023-08-23 17:45:00', 1, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `behavior_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (1, '2023-08-24 10:15:00', 4, 1, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (2, '2023-08-24 10:15:00', 4, 2, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (3, '2023-08-24 10:15:00', 4, 5, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (4, '2023-08-25 11:15:00', 5, 15, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (5, '2023-08-25 11:15:00', 5, 11, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (6, '2023-08-25 11:15:00', 5, 20, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `behavior_report_remark`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `behavior_report_remark` (`id`, `remarks`, `create_date`, `user_id`) VALUES (1, 'dog ran away and girlfriend broke up with me', '2023-08-24 10:15:00', 4);
INSERT INTO `behavior_report_remark` (`id`, `remarks`, `create_date`, `user_id`) VALUES (2, 'felt overwhelmed from everything', '2023-08-25 11:15:00', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `flagged_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `flagged_post` (`post_id`, `user_id`) VALUES (2, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `flagged_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `flagged_comment` (`comment_id`, `user_id`) VALUES (1, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `resource` (`id`, `resource_link`, `description`, `create_date`, `active`, `user_id`) VALUES (1, 'https://www.nami.org/', 'National Alliance on metal illness', '2023-08-24 11:50:00', 1, 1);
INSERT INTO `resource` (`id`, `resource_link`, `description`, `create_date`, `active`, `user_id`) VALUES (2, 'tel:988', 'National Suicide hotline', '2023-08-24 11:50:00', 1, 1);
INSERT INTO `resource` (`id`, `resource_link`, `description`, `create_date`, `active`, `user_id`) VALUES (3, 'https://www.thetrevorproject.org/', 'LGBTQ youth Alliance', '2023-08-24 11:50:00', 1, 1);
INSERT INTO `resource` (`id`, `resource_link`, `description`, `create_date`, `active`, `user_id`) VALUES (4, 'https://www.veteranscrisisline.net/', 'National Crisis Alliance for Veterans', '2023-08-24 11:50:00', 1, 1);
INSERT INTO `resource` (`id`, `resource_link`, `description`, `create_date`, `active`, `user_id`) VALUES (5, 'https://www.thehotline.org/', 'National Domestic Violence hotline', '2023-08-24 11:50:00', 1, 1);

COMMIT;

