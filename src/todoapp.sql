-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.15-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for systech_todoapp
DROP DATABASE IF EXISTS `systech_todoapp`;
CREATE DATABASE IF NOT EXISTS `systech_todoapp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `systech_todoapp`;

-- Dumping structure for table systech_todoapp.todo
DROP TABLE IF EXISTS `todo`;
CREATE TABLE IF NOT EXISTS `todo` (
  `nt_id` int(11) NOT NULL AUTO_INCREMENT,
  `nt_userid` int(11) NOT NULL,
  `nt_title` varchar(100) NOT NULL,
  `nt_desc` text NOT NULL,
  `nt_color` text NOT NULL,
  `nt_status` tinyint(4) NOT NULL DEFAULT 0,
  `nt_duedate` datetime NOT NULL,
  `nt_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`nt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_todoapp.todo: ~0 rows (approximately)
DELETE FROM `todo`;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` (`nt_id`, `nt_userid`, `nt_title`, `nt_desc`, `nt_color`, `nt_status`, `nt_duedate`, `nt_date`) VALUES
	(1, 1, 'Test MyTodo', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ', 'w3-amber', 0, '2020-08-26 08:49:22', '2020-08-26 08:49:23');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;

-- Dumping structure for table systech_todoapp.todoitems
DROP TABLE IF EXISTS `todoitems`;
CREATE TABLE IF NOT EXISTS `todoitems` (
  `ni_id` int(11) NOT NULL AUTO_INCREMENT,
  `ni_noteid` int(11) NOT NULL,
  `ni_text` text NOT NULL,
  `nt_status` tinyint(4) NOT NULL DEFAULT 0,
  `nt_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ni_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_todoapp.todoitems: ~2 rows (approximately)
DELETE FROM `todoitems`;
/*!40000 ALTER TABLE `todoitems` DISABLE KEYS */;
INSERT INTO `todoitems` (`ni_id`, `ni_noteid`, `ni_text`, `nt_status`, `nt_date`) VALUES
	(1, 1, ' Lorem ipsum dolor sit amet, consectetur ', 0, '2020-08-26 08:49:46'),
	(2, 1, 'Some item....', 0, '2020-08-26 09:02:10'),
	(3, 1, 'Another item...', 0, '2020-08-26 09:02:21');
/*!40000 ALTER TABLE `todoitems` ENABLE KEYS */;

-- Dumping structure for table systech_todoapp.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_username` varchar(80) NOT NULL,
  `usr_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_username` (`usr_username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_todoapp.users: ~1 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`usr_id`, `usr_username`, `usr_date`) VALUES
	(1, 'Aviator', '2020-08-26 08:33:49');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
