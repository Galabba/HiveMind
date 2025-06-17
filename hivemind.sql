-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hivemind
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
  `nTicketRis` varchar(45) NOT NULL DEFAULT '0',
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
INSERT INTO `dipendente` VALUES ('a','a','a','a','SviluppoMO','Modena','6'),('alice_w','Alice','Williams','alice789','SviluppoMO','Modena','3'),('amelia_cyan','Amelia','Cyan','amelia404','SviluppoMO','Modena','2'),('anna_red','Anna','Red','anna808','SviluppoMO','Modena','2'),('ava_maroon','Ava','Maroon','ava808','SviluppoMO','Modena','1'),('bob_brown','Bob','Brown','bob101','ProgettazioneMO','Modena','0'),('chris_black','Chris','Black','chris404','ProgettazioneMO','Modena','0'),('daniel_orange','Daniel','Orange','daniel808','ProgettazioneMO','Modena','0'),('david_yellow','David','Yellow','david707','ProgettazioneMO','Modena','0'),('emily_blue','Emily','Blue','emily606','MarketingMO','Modena','0'),('emma_purple','Emma','Purple','emma909','MarketingMO','Modena','0'),('isabella_silver','Isabella','Silver','isabella202','MarketingMO','Modena','0'),('jack_gold','Jack','Gold','jack101','MarketingBO','Bologna','0'),('jane_smith','Jane','Smith','jane456','MarketingBO','Bologna','0'),('john_doe','John','Doe','john123','MarketingBO','Bologna','0'),('laura_pink','Laura','Pink','laura101','ProgettazioneBO','Bologna','0'),('lisa_green','Lisa','Green','lisa202','ProgettazioneBO','Bologna','0'),('logan_teal','Logan','Teal','logan707','ProgettazioneBO','Bologna','0'),('lucas_olive','Lucas','Olive','lucas909','ProgettazioneBO','Bologna','0'),('maria_brown','Maria','Brown','maria303','ProgettazioneBO','Bologna','0'),('mia_lime','Mia','Lime','mia606','SviluppoBO','Bologna','0'),('mike_black','Mike','Black','mike303','SviluppoBO','Bologna','0'),('noah_magenta','Noah','Magenta','noah505','SviluppoBO','Bologna','0'),('olivia_yellow','Olivia','Yellow','olivia707','MarketingMI','Milano','0'),('paul_green','Paul','Green','paul909','MarketingMI','Milano','0'),('peter_blue','Peter','Blue','peter606','SviluppoMI','Milano','0'),('ryan_bronze','Ryan','Bronze','ryan303','SviluppoMI','Milano','0'),('sara_white','Sara','White','sara404','SviluppoMI','Milano','0'),('sophia_gray','Sophia','Gray','sophia505','ProgettazioneMI','Milano','0'),('steve_white','Steve','White','steve202','ProgettazioneMI','Milano','0'),('tom_gray','Tom','Gray','tom505','ProgettazioneMI','Milano','0');
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
INSERT INTO `manager` VALUES ('andrea_rossi','Andrea','Rossi','andrea789','SviluppoMO'),('b','b','b','b','SviluppoMO'),('chiara_indaco','Chiara','Indaco','chiara909','SviluppoBO'),('elena_marrone','Elena','Marrone','elena303','SviluppoMI'),('federico_rosa','Federico','Rosa','federico808','SviluppoRO'),('francesca_neri','Francesca','Neri','francesca101','ProgettazioneMO'),('giorgia_celesti','Giorgia','Celesti','giorgia707','ProgettazioneMI'),('giulia_bianchi','Giulia','Bianchi','giulia456','ProgettazioneBO'),('luca_verdi','Luca','Verdi','luca123','ProgettazioneRO'),('matteo_gialli','Matteo','Gialli','matteo202','MarketingMI'),('riccardo_azzurri','Riccardo','Azzurri','riccardo404','MarketingMO'),('simone_arancioni','Simone','Arancioni','simone606','MarketingBO'),('valentina_viola','Valentina','Viola','valentina505','MarketingRO');
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
  `Nome` varchar(100) NOT NULL,
  `Descrizione` varchar(255) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  `Dipartimento` varchar(45) NOT NULL,
  PRIMARY KEY (`idTicket`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'Cambiare IDE per il progetto \"Schema Ponzi\"','NetBeans lo usavano FORSE nel 1980, sarebbe ora di darsi una svegliata','start','MarketingMO'),(2,'Implementazione Login','Creazione del sistema di autenticazione per gli utenti.','stop','SviluppoMO'),(3,'Campagna Social','Pianificazione post per le piattaforme social di giugno.','progress','MarketingMO'),(4,'Analisi requisiti','Raccolta e analisi dei requisiti funzionali del cliente.','stop','ProgettazioneMO'),(5,'Ottimizzazione Backend','Refactoring codice per migliorare le performance.','progress','SviluppoMO'),(6,'Creazione Landing Page','Progettazione grafica e contenuti per la nuova landing.','start','MarketingMO'),(7,'Prototipo UI','Realizzazione mockup per la dashboard utente.','start','ProgettazioneMO'),(8,'Debugging API','Individuazione e correzione bug nelle API REST.','progress','SviluppoMO'),(9,'Studio mercato','Analisi competitor e benchmark per il nuovo prodotto.','stop','MarketingMO'),(10,'Architettura Sistema','Definizione dell’architettura software per il progetto.','progress','ProgettazioneMO'),(11,'Setup Continuous Integration','Configurazione pipeline CI con GitHub Actions.','start','SviluppoMO'),(12,'Piano Editoriale','Stesura piano editoriale Q3 per blog aziendale.','progress','MarketingMO'),(13,'Test Usabilità','Sessioni di test con utenti su prototipi interattivi.','start','ProgettazioneMO'),(14,'Refactoring Frontend','Aggiornamento componenti React obsoleti.','stop','SviluppoMO'),(15,'Analisi Engagement','Valutazione metriche di coinvolgimento social.','progress','MarketingMO'),(16,'Definizione Flussi UX','Mappatura percorsi utente principali nel portale.','start','ProgettazioneMO'),(17,'prova1','prova 2','progress','SviluppoMO'),(21,'prova 2','proino','finish','SviluppoMO'),(23,'asdasd','sadsad','finish','SviluppoMO'),(24,'asdas','asas','finish','SviluppoMO'),(25,'ffsdfd','sdasdas','finish','SviluppoMO');
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

-- Dump completed on 2025-06-17 23:32:20
