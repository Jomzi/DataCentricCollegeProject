-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: collegedb
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `collegedb`;

CREATE DATABASE `collegedb`;

USE `collegedb`;


--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `colID` varchar(10) NOT NULL,
  `colName` varchar(50) DEFAULT NULL,
  `colAddr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`colID`)
) ENGINE=innoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES ('GMIT','Galway-Mayo Institute of Technology','Dublin Rd., Galway'),('NUIG','National University of Ireland, Galway','University Rd., Galway'),('UL','University of Limerick','Castletroy, Limerick'),('AIT','Athlone Institute of Technology','Dublin Rd., Athlone');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `colID` varchar(10) NOT NULL,
  `cID` varchar(10) NOT NULL,
  `cName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`colID`,`cID`),
  FOREIGN KEY (`colID`) REFERENCES `college` (`colID`)
) ENGINE=innoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('GMIT','SW','B.Sc. in Software Development'),('GMIT','DIG MEDIA','B.Sc. in Digital Media'),('GMIT','BUS','B.A. in Business Studies'),('NUIG','SW','B.Sc. in Software Development'),('NUIG','LAW','B.A. in Legal Studies'),('UL','EUROSTUD','B.A. in European Studies'),('UL','SW','B.Sc. in Software Development'),('AIT','DIG MEDIA','B.Sc. in Digital Media'),('AIT','HOSP','B.A. in Hospitality');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` varchar(10) NOT NULL,
  `colID` varchar(10) DEFAULT NULL,
  `cID` varchar(10) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  FOREIGN KEY (`colID`,`cID`) REFERENCES `course` (`colID`,`cID`)
) ENGINE=innoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('G0012345','GMIT','SW','John','Smith','Main St. Tuam'),('G0022222','GMIT','SW','Sean','Walsh','High St. Castlebar'),('G0033333','GMIT','SW','Aine','O\'Brien','Henry St. Castlebar'),('G004444','GMIT','DIG MEDIA','James','Allen','Church Row, Tuam'),('G0044455','GMIT','DIG MEDIA','Aine','Tully','John St. Spiddal'),('N23423423','NUIG','SW','Fred','Jones','Lower St. Tuam'),('N23423333','NUIG','SW','Brian','Jones','Lower St. Tuam'),('N23423334','NUIG','SW','Flan','O\'Brien','Castlebar'),('N23423335','NUIG','SW','Peter','Nolan','Claregalway'),('N23423336','NUIG','SW','Paul','McPadden','Knocknacarra, Galway'),('N23423356','NUIG','SW','Ronan','Kenna','Athenry'),('N23423332','NUIG','LAW','Donnacha','Kenna','Athenry'),('N23423232','NUIG','LAW','Seamus','Hanly','High St. Tuam'),('N23423244','NUIG','LAW','Orla','Henry','Chapel Lane, Ballinasloe'),('U00023244','UL','EUROSTUD','Orla','Ganly','Limerick'),('U00023255','UL','EUROSTUD','Paul','Mulligan','Scariff'),('U00333255','UL','EUROSTUD','Morgan','Murphy','Adare'),('U30333255','UL','SW','Morgan','Collins','Gort'),('U50333255','UL','SW','Sarah','Collins','Castlebar'),('U60333255','UL','SW','Mick','Collins','Tuam'),('A88883255','AIT','DIG MEDIA','Mick','Boland','Limerick'),('A88883235','AIT','DIG MEDIA','Harry','McNamara','Grace Rd. Athlone'),('A88234235','AIT','DIG MEDIA','Ian','Smith','Willow Park, Athlone'),('A88234277','AIT','DIG MEDIA','Pippa','Cassidy','Castlebar'),('A88233337','AIT','HOSP','Fiona','O\'Rourke','Ballinasloe'),('A83233337','AIT','HOSP','Mary','Murphy','Tuam'),('A8323537','AIT','HOSP','Sean','Burke','Athlone'),('A832037','AIT','HOSP','James','Lemass','Athlone');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-10  9:46:18