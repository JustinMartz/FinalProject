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
  `title` VARCHAR(500) NULL,
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
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'admin@aol.com', 'cat.png', 'joe', 'smith', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '1995-08-23', 'Administrator');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (2, 'provider', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'provider', 'provider@aol.com', 'whale.png', 'amy', 'kenny', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '1985-01-12', 'Mental health provider for my patients.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (3, 'FlyingSaucer105', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'user0@aol.com', 'squirrel.png', 'shelia', 'kennedy', '2023-08-22 17:30:00', '2023-08-23 17:30:00', '2000-07-15', 'I\'m passionate about mental health and love riding my bike.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (4, 'TomBigCat808', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'user1@aol.com', 'bird.png', 'Alex', 'Apakidze', '2023-08-22 17:30:00', '2023-08-25 17:30:00', '1990-08-25', 'I love thinking outside the box while solving problems in my code. I enjoy sitting outside in the morning to feed the birds my leftover toast from breakfast. I also love bass music.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (5, 'TomKitten23', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'user2@aol.com', 'snake.png', 'Justin', 'Martz', '2023-08-22 18:30:00', '2023-08-25 17:30:00', '1985-06-20', 'My super awesome special talent is that I can play guitar while I code. I\'m easy going and love a tall glass of Japense whiskey after work.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (6, 'TomCat11', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'user3@aol.com', 'jellyfish.png', 'Jay', 'Perkins', '2023-08-22 19:30:00', '2023-08-25 17:30:00', '1996-07-15', 'I\'m an up and coming actor, thriving on the west coast with my cat. I really love my cat but sometimes they step on my keyboard and break my code. It\'s all good though because I can fix it in an instance. ');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (7, 'TomBobCat82', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'user4@aol.com', 'bobcat.png', 'Sam', 'Fath', '2023-08-22 20:30:00', '2023-08-22 17:30:00', '1995-01-12', 'I love hiking with my famil. I enjoy coding and love testing my code almost as much as writing it. ');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `avatar`, `first_name`, `last_name`, `create_date`, `update_date`, `date_of_birth`, `about_me`) VALUES (8, 'TreeClimber123', '$2a$10$c9e8.qhWOKEbz02Vfc3dCO16/lJLOHxF4fwBReCXE.glEzWqY96Pe', 1, 'user', 'treeclimber5@aol.com', 'dolphin.png', 'Rob', 'Adams', '2023-07-25 20:23:00', '2023-08-26 12:17:00', '1994-04-22', 'Climber of all things tall.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (1, 'How are ya\'ll feeling today?', 4, 1, '2023-08-25 11:40:00', 'I\'m feeling alright today.', 1, 0);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (2, 'I\'ve been feeling a sad recently, anyone wanna chat?', 5, 1, '2023-08-25 11:25:00', 'I could use a pick me up.', 1, 0);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (3, 'Is there anything ya\'ll incorporate into your morning routine for your mental health?', 6, 1, '2023-08-25 12:20:00', 'I started meditating in the morning for my mental health. ', 1, 0);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (4, 'Is there anything ya\'ll do to help ease late-night mania?', 6, 1, '2023-08-27 13:20:00', 'I\'ve been setting a timer on my phone when it\'s time for me to go to bed.', 1, 0);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (5, 'Cognitive Behavioral Therapy thoughts?', 5, 1, '2023-08-26 11:25:00', 'Thought records have been a game-changer for me. Basically, you jot down the situation, your thoughts, feelings, and behaviors, and then you challenge those thoughts. It helps you see the cognitive distortions you might be making.', 1, 0);
INSERT INTO `post` (`id`, `title`, `user_id`, `active`, `create_date`, `message`, `anonymous`, `personal`) VALUES (6, 'Does anyone have any good motivation boosters during your depressive episodes?', 5, 1, '2023-08-28 11:25:00', 'Hey everyone, during my low days, I really struggle to do even the simplest tasks.', 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dualitydb`;
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (1, '2023-08-25 11:15:00', 'I\'m feeling really excited for this weekend. I\'m going to see one of my favorite artists.', 1, 0, 5, 1);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (2, '2023-08-25 11:20:00', 'Yesterday was tough. I recieved some bad news. I\'ve been able to sit down and process it a bit and I\'m feeling a lot better about it today. ', 1, 0, 4, 1);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (3, '2023-08-25 11:20:00', 'I\'m here for you. You matter and whatever emotions you\'re feeling right now will pass. I promise! Look forward to a brighter day.', 1, 0, 5, 2);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (4, '2023-08-27 11:20:00', 'I keep getting stuck at the \'cognitive restructuring\' part. It\'s hard to challenge my own beliefs. Any tips?', 1, 0, 4, 5);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (5, '2023-08-27 11:30:00', 'It\'s okay to feel stuck. What helped me was listing down evidence that supports and contradicts my belief. It kind of makes it easier to see the thought from different angles.', 1, 0, 6, 5);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (6, '2023-08-27 11:45:00', 'CBT offers techniques to better regulate emotions, thereby potentially mitigating the intensity of mood swings. The therapy aims to challenge dysfunctional thought patterns that can contribute to depressive or manic symptoms. ', 1, 0, 7, 5);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (7, '2023-08-29 11:45:00', 'I totally get you. Something that helps me is breaking tasks down into smaller parts. It makes them less intimidating and a little more manageable.', 1, 0, 6, 6);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (8, '2023-08-29 11:47:00', 'I like to set mini-goals for myself and reward myself when I achieve them. Could be as simple as reading for 10 minutes and then having a piece of chocolate. It makes tasks more enticing.', 1, 0, 5, 6);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (9, '2023-08-29 11:50:00', 'When I\'m really low, I reach out to friends or support groups. Just talking about what I\'m going through makes me feel a bit lighter and less isolated. I also found that listening to uplifting music can change my mood almost instantly. I have a playlist just for those gloomy days. ', 1, 0, 4, 6);
INSERT INTO `comment` (`id`, `create_date`, `body`, `active`, `flagged`, `user_id`, `post_id`) VALUES (10, '2023-08-29 11:51:00', 'Remember, it\'s okay to have down days and not be productive. Sometimes, it\'s essential to give yourself permission to rest without feeling guilty.', 1, 0, 7, 6);

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
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (3, '2023-08-24 10:15:00', 4, 5, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (4, '2023-08-25 11:15:00', 5, 15, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (5, '2023-08-25 11:15:00', 5, 11, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (6, '2023-08-25 11:15:00', 5, 20, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (7, '2023-08-25 11:15:00', 5, 1, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (8, '2023-08-25 11:15:00', 5, 2, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (9, '2023-08-25 11:15:00', 5, 4, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (10, '2023-08-25 11:15:00', 5, 5, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (11, '2023-08-25 11:15:00', 5, 6, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (12, '2023-08-25 11:15:00', 5, 8, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (13, '2023-08-24 11:15:00', 5, 1, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (14, '2023-08-24 11:15:00', 5, 2, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (15, '2023-08-24 11:15:00', 5, 3, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (16, '2023-08-24 11:15:00', 5, 4, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (17, '2023-08-24 11:15:00', 5, 5, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (18, '2023-08-24 11:15:00', 5, 11, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (19, '2023-08-24 11:15:00', 5, 12, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (20, '2023-08-24 11:15:00', 5, 13, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (21, '2023-08-23 11:15:00', 5, 1, 10);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (22, '2023-08-23 11:15:00', 5, 2, 9);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (23, '2023-08-23 11:15:00', 5, 4, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (24, '2023-08-23 11:15:00', 5, 5, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (25, '2023-08-23 11:15:00', 5, 6, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (26, '2023-08-23 11:15:00', 5, 7, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (27, '2023-08-23 11:15:00', 5, 8, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (28, '2023-08-23 11:15:00', 5, 15, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (29, '2023-08-23 11:15:00', 5, 16, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (30, '2023-08-23 11:15:00', 5, 17, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (31, '2023-08-25 11:15:00', 4, 1, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (32, '2023-08-25 11:15:00', 4, 2, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (33, '2023-08-25 11:15:00', 4, 3, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (34, '2023-08-25 11:15:00', 4, 4, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (35, '2023-08-25 11:15:00', 4, 5, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (36, '2023-08-25 11:15:00', 4, 6, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (37, '2023-08-25 11:15:00', 4, 7, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (38, '2023-08-25 11:15:00', 4, 8, 9);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (39, '2023-08-25 11:15:00', 4, 9, 10);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (40, '2023-08-25 11:15:00', 4, 10, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (41, '2023-08-25 11:15:00', 4, 11, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (42, '2023-08-25 11:15:00', 4, 12, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (43, '2023-08-25 11:15:00', 4, 13, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (44, '2023-08-25 11:15:00', 4, 14, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (45, '2023-08-25 11:15:00', 4, 15, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (46, '2023-08-25 11:15:00', 4, 16, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (47, '2023-08-25 11:15:00', 4, 17, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (48, '2023-08-25 11:15:00', 4, 18, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (49, '2023-08-25 11:15:00', 4, 19, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (50, '2023-08-25 11:15:00', 4, 20, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (51, '2023-08-26 11:15:00', 5, 1, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (52, '2023-08-26 11:15:00', 5, 2, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (53, '2023-08-26 11:15:00', 5, 3, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (54, '2023-08-26 11:15:00', 5, 4, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (55, '2023-08-26 11:15:00', 5, 8, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (56, '2023-08-26 11:15:00', 5, 11, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (57, '2023-08-26 11:15:00', 5, 12, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (58, '2023-08-26 11:15:00', 5, 14, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (59, '2023-08-26 11:15:00', 5, 18, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (60, '2023-08-27 11:15:00', 5, 4, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (61, '2023-08-27 11:15:00', 5, 5, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (62, '2023-08-27 11:15:00', 5, 7, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (63, '2023-08-27 11:15:00', 5, 9, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (64, '2023-08-27 11:15:00', 5, 15, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (65, '2023-08-27 11:15:00', 5, 16, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (66, '2023-08-27 11:15:00', 5, 17, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (67, '2023-08-27 11:15:00', 5, 20, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (68, '2023-08-28 11:15:00', 5, 1, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (69, '2023-08-28 11:15:00', 5, 2, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (70, '2023-08-28 11:15:00', 5, 3, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (71, '2023-08-28 11:15:00', 5, 4, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (72, '2023-08-28 11:15:00', 5, 8, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (73, '2023-08-28 11:15:00', 5, 11, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (74, '2023-08-28 11:15:00', 5, 12, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (75, '2023-08-28 11:15:00', 5, 14, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (76, '2023-08-28 11:15:00', 5, 18, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (77, '2023-08-29 11:15:00', 5, 1, 9);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (78, '2023-08-29 11:15:00', 5, 2, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (79, '2023-08-29 11:15:00', 5, 3, 9);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (80, '2023-08-29 11:15:00', 5, 4, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (81, '2023-08-29 11:15:00', 5, 6, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (82, '2023-08-29 11:15:00', 5, 8, 10);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (83, '2023-08-29 11:15:00', 5, 11, 10);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (84, '2023-08-29 11:15:00', 5, 12, 10);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (85, '2023-08-29 11:15:00', 5, 14, 7);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (86, '2023-08-30 11:15:00', 5, 1, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (87, '2023-08-30 11:15:00', 5, 2, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (88, '2023-08-30 11:15:00', 5, 3, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (89, '2023-08-30 11:15:00', 5, 4, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (90, '2023-08-30 11:15:00', 5, 5, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (91, '2023-08-30 11:15:00', 5, 6, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (92, '2023-08-30 11:15:00', 5, 7, 4);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (93, '2023-08-30 11:15:00', 5, 8, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (94, '2023-08-30 11:15:00', 5, 9, 6);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (95, '2023-08-30 11:15:00', 5, 10, 8);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (96, '2023-08-30 11:15:00', 5, 11, 3);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (97, '2023-08-30 11:15:00', 5, 12, 2);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (98, '2023-08-30 11:15:00', 5, 13, 1);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (99, '2023-08-30 11:15:00', 5, 14, 5);
INSERT INTO `behavior_report` (`id`, `create_date`, `user_id`, `behavior_id`, `intensity`) VALUES (100, '2023-08-30 11:15:00', 5, 15, 6);

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

