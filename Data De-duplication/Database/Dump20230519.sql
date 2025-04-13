-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: dedup
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Admin Dedup','admin@gmail.com','Admin1234','8657976088');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blockindex`
--

DROP TABLE IF EXISTS `blockindex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blockindex` (
  `filename` varchar(45) NOT NULL,
  `blockName` varchar(45) NOT NULL,
  `bolckPath` varchar(2000) NOT NULL,
  `hash` varchar(2000) NOT NULL,
  `status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blockindex`
--

LOCK TABLES `blockindex` WRITE;
/*!40000 ALTER TABLE `blockindex` DISABLE KEYS */;
/*!40000 ALTER TABLE `blockindex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dupindex`
--

DROP TABLE IF EXISTS `dupindex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dupindex` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `hashvalue` varchar(20000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dupindex`
--

LOCK TABLES `dupindex` WRITE;
/*!40000 ALTER TABLE `dupindex` DISABLE KEYS */;
INSERT INTO `dupindex` VALUES (1,'demo2.txt','shilpa@gmail.com','fd9a72b14c99433ffa9625bec67f9e461576a7a4a7fb1a2f0caa0fe879839202'),(2,'Eg3.txt','navanathmemane@gmail.com','745fbe4b2c10bae6c445b4320225bec921b2bea182d9e5463efb0b9da0a33d65'),(3,'ZZ - Copy.txt','datta@gmail.com','64ec88ca00b268e5ba1a35678a1b5316d212f4f366b2477232534a8aeca37f3c');
/*!40000 ALTER TABLE `dupindex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filestorage`
--

DROP TABLE IF EXISTS `filestorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filestorage` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `filepath` varchar(1000) NOT NULL,
  `hash` varchar(20000) NOT NULL,
  `encryptkey` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `pow` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filestorage`
--

LOCK TABLES `filestorage` WRITE;
/*!40000 ALTER TABLE `filestorage` DISABLE KEYS */;
INSERT INTO `filestorage` VALUES (4,'demo1.txt','navanathmemane@gmail.com','C:/Deduplication/EncryptFile/demo1.txt','fd9a72b14c99433ffa9625bec67f9e461576a7a4a7fb1a2f0caa0fe879839202','fd9a72b14c99433f','original','1020203496'),(5,'demo2.txt','shilpa@gmail.com','C:/Deduplication/EncryptFile/demo2.txt','fd9a72b14c99433ffa9625bec67f9e461576a7a4a7fb1a2f0caa0fe879839202','fd9a72b14c99433f','duplicate','-1933697063'),(6,'Eg2.txt','datta@gmail.com','C:/Deduplication/EncryptFile/Eg2.txt','745fbe4b2c10bae6c445b4320225bec921b2bea182d9e5463efb0b9da0a33d65','745fbe4b2c10bae6','original','-1081204112'),(7,'Eg3.txt','navanathmemane@gmail.com','C:/Deduplication/EncryptFile/Eg3.txt','745fbe4b2c10bae6c445b4320225bec921b2bea182d9e5463efb0b9da0a33d65','745fbe4b2c10bae6','duplicate','2058441657'),(8,'Eg4.txt','navanathmemane@gmail.com','C:/Deduplication/EncryptFile/Eg4.txt','b3d646a723bd3b2f274ef788ec2baa4704b0eb3814a0f1a2c3e1a0165d9ca0cc','b3d646a723bd3b2f','original','-801100348'),(9,'ZZ.txt','datta@gmail.com','C:/Deduplication/EncryptFile/ZZ.txt','64ec88ca00b268e5ba1a35678a1b5316d212f4f366b2477232534a8aeca37f3c','64ec88ca00b268e5','original','1334528391'),(10,'abc.txt','shri@gmail.com','C:/Deduplication/EncryptFile/abc.txt','0b8b16143618c9238da725a83cc9820f2d0d1faec08b10d2f09e3b42f35b3d34','0b8b16143618c923','original','-929843421'),(11,'Shri.txt','shri@gmail.com','C:/Deduplication/EncryptFile/Shri.txt','43ad5ed3c7fb81d4d37a86d28b879b8e34d969329edfec4da9487817dbc10c46','43ad5ed3c7fb81d4','original','-1529076840'),(12,'datta.txt','dattasirsath8308@gmail.com','C:/Deduplication/EncryptFile/datta.txt','3f1c711ebaf4b1093ca1d1083410cb2c59681632224e6c3b50a44f5cf6505993','3f1c711ebaf4b109','original','-1700779599'),(13,'ZZ - Copy.txt','datta@gmail.com','C:/Deduplication/EncryptFile/ZZ - Copy.txt','64ec88ca00b268e5ba1a35678a1b5316d212f4f366b2477232534a8aeca37f3c','64ec88ca00b268e5','duplicate','-2113384451'),(14,'VM argument - Copy.txt','dattasirsath8308@gmail.com','C:/Deduplication/EncryptFile/VM argument - Copy.txt','9ac1d124c314e0ebb3a10bbef60b96ecb5fd9ed52b63b3cd7f194e862cbdb17e','9ac1d124c314e0eb','original','588906442');
/*!40000 ALTER TABLE `filestorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyrequest`
--

DROP TABLE IF EXISTS `keyrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keyrequest` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `ownername` varchar(45) NOT NULL,
  `filename` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyrequest`
--

LOCK TABLES `keyrequest` WRITE;
/*!40000 ALTER TABLE `keyrequest` DISABLE KEYS */;
INSERT INTO `keyrequest` VALUES (2,'navanathmemane@gmail.com','demo1.txt','shilpa@gmail.com','no'),(3,'navanathmemane@gmail.com','demo1.txt','datta@gmail.com','send'),(4,'navanathmemane@gmail.com','Eg4.txt','shri@gmail.com','no'),(5,'datta@gmail.com','Eg2.txt','dattasirsath8308@gmail.com','no'),(6,'dattasirsath8308@gmail.com','datta.txt','datta@gmail.com','no'),(7,'datta@gmail.com','Eg2.txt','dattasirsath8308@gmail.com','no'),(8,'navanathmemane@gmail.com','demo1.txt','dattasirsath8308@gmail.com','no');
/*!40000 ALTER TABLE `keyrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otpvalidation`
--

DROP TABLE IF EXISTS `otpvalidation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otpvalidation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `end` time NOT NULL,
  `otp` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otpvalidation`
--

LOCK TABLES `otpvalidation` WRITE;
/*!40000 ALTER TABLE `otpvalidation` DISABLE KEYS */;
/*!40000 ALTER TABLE `otpvalidation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'Navanath Memane','navanathmemane@gmail.com','Navanath1133','7798230923'),(7,'Shilpa Tandale','shilpa@gmail.com','Shilpa11','8899556633'),(8,'Sagar Tupe','sagar@gmail.com','Sagar11','8877445511'),(10,'Rasika Gode','rasikagode@gmail.com','Rasika11','9855669988'),(11,'dipak','bhore.dipak@gmail.com','Dddd123','9889789878'),(12,'Datta Shirsath','datta@gmail.com','Datta@123','9689189495'),(13,'Shri','shri@gmail.com','Shri@123','7451278451'),(14,'Datta Sirsath','dattasirsath8308@gmail.com','Datta@1234','7498767134');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-19 14:27:39
