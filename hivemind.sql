-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hive_mind
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `dipartimento`
--

DROP TABLE IF EXISTS `dipartimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipartimento` (
  `NomeDip` varchar(45) NOT NULL,
  `NumTicketRisoltiDip` int NOT NULL,
  `Sede` varchar(30) NOT NULL,
  PRIMARY KEY (`NomeDip`),
  KEY `Sede_idx` (`Sede`),
  CONSTRAINT `Sede` FOREIGN KEY (`Sede`) REFERENCES `sede` (`Città`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipartimento`
--

LOCK TABLES `dipartimento` WRITE;
/*!40000 ALTER TABLE `dipartimento` DISABLE KEYS */;
INSERT INTO `dipartimento` VALUES ('MarketingBO',0,'Bologna'),('MarketingMI',0,'Milano'),('MarketingMO',0,'Modena'),('MarketingRO',0,'Roma'),('ProgettazioneBO',0,'Bologna'),('ProgettazioneMI',0,'Milano'),('ProgettazioneMO',0,'Modena'),('ProgettazioneRO',0,'Roma'),('SviluppoBO',0,'Bologna'),('SviluppoMI',0,'Milano'),('SviluppoMO',0,'Modena'),('SviluppoRO',0,'Roma');
/*!40000 ALTER TABLE `dipartimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendente` (
  `User` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Dipartimento` varchar(45) NOT NULL,
  `Sede` varchar(45) NOT NULL,
  PRIMARY KEY (`User`),
  KEY `Dipartimento_idx` (`Dipartimento`),
  CONSTRAINT `Dipartimento` FOREIGN KEY (`Dipartimento`) REFERENCES `dipartimento` (`NomeDip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES ('alice_w','Alice','Williams','alice789','SviluppoMO','Modena'),('amelia_cyan','Amelia','Cyan','amelia404','SviluppoMO','Modena'),('anna_red','Anna','Red','anna808','SviluppoMO','Modena'),('ava_maroon','Ava','Maroon','ava808','SviluppoMO','Modena'),('bob_brown','Bob','Brown','bob101','ProgettazioneMO','Modena'),('chris_black','Chris','Black','chris404','ProgettazioneMO','Modena'),('daniel_orange','Daniel','Orange','daniel808','ProgettazioneMO','Modena'),('david_yellow','David','Yellow','david707','ProgettazioneMO','Modena'),('emily_blue','Emily','Blue','emily606','MarketingMO','Modena'),('emma_purple','Emma','Purple','emma909','MarketingMO','Modena'),('isabella_silver','Isabella','Silver','isabella202','MarketingMO','Modena'),('jack_gold','Jack','Gold','jack101','MarketingBO','Bologna'),('jane_smith','Jane','Smith','jane456','MarketingBO','Bologna'),('john_doe','John','Doe','john123','MarketingBO','Bologna'),('laura_pink','Laura','Pink','laura101','ProgettazioneBO','Bologna'),('lisa_green','Lisa','Green','lisa202','ProgettazioneBO','Bologna'),('logan_teal','Logan','Teal','logan707','ProgettazioneBO','Bologna'),('lucas_olive','Lucas','Olive','lucas909','ProgettazioneBO','Bologna'),('maria_brown','Maria','Brown','maria303','ProgettazioneBO','Bologna'),('mia_lime','Mia','Lime','mia606','SviluppoBO','Bologna'),('mike_black','Mike','Black','mike303','SviluppoBO','Bologna'),('noah_magenta','Noah','Magenta','noah505','SviluppoBO','Bologna'),('olivia_yellow','Olivia','Yellow','olivia707','MarketingMI','Milano'),('paul_green','Paul','Green','paul909','MarketingMI','Milano'),('peter_blue','Peter','Blue','peter606','SviluppoMI','Milano'),('ryan_bronze','Ryan','Bronze','ryan303','SviluppoMI','Milano'),('sara_white','Sara','White','sara404','SviluppoMI','Milano'),('sophia_gray','Sophia','Gray','sophia505','ProgettazioneMI','Milano'),('steve_white','Steve','White','steve202','ProgettazioneMI','Milano'),('tom_gray','Tom','Gray','tom505','ProgettazioneMI','Milano');
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `User` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Dipartimento` varchar(45) NOT NULL,
  PRIMARY KEY (`User`),
  KEY `Dipartimento_idx` (`Dipartimento`) /*!80000 INVISIBLE */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('andrea_rossi','Andrea','Rossi','andrea789','SviluppoMO'),('chiara_indaco','Chiara','Indaco','chiara909','SviluppoBO'),('elena_marrone','Elena','Marrone','elena303','SviluppoMI'),('federico_rosa','Federico','Rosa','federico808','SviluppoRO'),('francesca_neri','Francesca','Neri','francesca101','ProgettazioneMO'),('giorgia_celesti','Giorgia','Celesti','giorgia707','ProgettazioneMI'),('giulia_bianchi','Giulia','Bianchi','giulia456','ProgettazioneBO'),('luca_verdi','Luca','Verdi','luca123','ProgettazioneRO'),('matteo_gialli','Matteo','Gialli','matteo202','MarketingMI'),('riccardo_azzurri','Riccardo','Azzurri','riccardo404','MarketingMO'),('simone_arancioni','Simone','Arancioni','simone606','MarketingBO'),('valentina_viola','Valentina','Viola','valentina505','MarketingRO');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sede`
--

DROP TABLE IF EXISTS `sede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sede` (
  `Città` varchar(45) NOT NULL,
  `NumDip` int NOT NULL,
  `NumTicketRisoltiSede` int NOT NULL,
  PRIMARY KEY (`Città`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sede`
--

LOCK TABLES `sede` WRITE;
/*!40000 ALTER TABLE `sede` DISABLE KEYS */;
INSERT INTO `sede` VALUES ('Bologna',0,0),('Milano',0,0),('Modena',0,0),('Roma',0,0);
/*!40000 ALTER TABLE `sede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Descrizione` varchar(45) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`idTicket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-18 15:12:25
