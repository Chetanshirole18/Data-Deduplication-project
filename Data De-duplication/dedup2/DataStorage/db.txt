-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dedup
--

CREATE DATABASE IF NOT EXISTS dedup;
USE dedup;

--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`,`Name`,`Email`,`Password`,`Contact`) VALUES 
 (1,'Admin Dedup','admin@gmail.com','Admin1234','8657976088');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `blockindex`
--

DROP TABLE IF EXISTS `blockindex`;
CREATE TABLE `blockindex` (
  `filename` varchar(45) NOT NULL,
  `blockName` varchar(45) NOT NULL,
  `bolckPath` varchar(2000) NOT NULL,
  `hash` varchar(2000) NOT NULL,
  `status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blockindex`
--

/*!40000 ALTER TABLE `blockindex` DISABLE KEYS */;
/*!40000 ALTER TABLE `blockindex` ENABLE KEYS */;


--
-- Definition of table `dupindex`
--

DROP TABLE IF EXISTS `dupindex`;
CREATE TABLE `dupindex` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `hashvalue` varchar(20000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dupindex`
--

/*!40000 ALTER TABLE `dupindex` DISABLE KEYS */;
/*!40000 ALTER TABLE `dupindex` ENABLE KEYS */;


--
-- Definition of table `filestorage`
--

DROP TABLE IF EXISTS `filestorage`;
CREATE TABLE `filestorage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `filepath` varchar(1000) NOT NULL,
  `hash` varchar(20000) NOT NULL,
  `encryptkey` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `pow` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `filestorage`
--

/*!40000 ALTER TABLE `filestorage` DISABLE KEYS */;
/*!40000 ALTER TABLE `filestorage` ENABLE KEYS */;


--
-- Definition of table `keyrequest`
--

DROP TABLE IF EXISTS `keyrequest`;
CREATE TABLE `keyrequest` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ownername` varchar(45) NOT NULL,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keyrequest`
--

/*!40000 ALTER TABLE `keyrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyrequest` ENABLE KEYS */;


--
-- Definition of table `otpvalidation`
--

DROP TABLE IF EXISTS `otpvalidation`;
CREATE TABLE `otpvalidation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `end` time NOT NULL,
  `otp` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `otpvalidation`
--

/*!40000 ALTER TABLE `otpvalidation` DISABLE KEYS */;
/*!40000 ALTER TABLE `otpvalidation` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`Name`,`Email`,`Password`,`Contact`) VALUES 
 (6,'Navanath Memane','navanathmemane@gmail.com','Navanath1133','7798230923'),
 (7,'Shilpa Tandale','shilpa@gmail.com','Shilpa11','8899556633'),
 (8,'Sagar Tupe','sagar@gmail.com','Sagar11','8877445511'),
 (10,'Rasika Gode','rasikagode@gmail.com','Rasika11','9855669988'),
 (11,'dipak','bhore.dipak@gmail.com','Dddd123','9889789878');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of procedure `deleterecords`
--

DROP PROCEDURE IF EXISTS `deleterecords`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleterecords`()
BEGIN
delete FROM dedup.blockindex;
delete FROM dedup.dupindex;
delete FROM dedup.filestorage;
delete FROM dedup.keyrequest;
delete FROM dedup.otpvalidation;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
