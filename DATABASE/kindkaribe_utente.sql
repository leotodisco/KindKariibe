-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: localhost    Database: kindkaribe
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `codiceFiscale` varchar(16) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `dataNascita` date DEFAULT NULL,
  `email` varchar(75) NOT NULL,
  `nTelefono` varchar(10) NOT NULL,
  `password` varchar(12) NOT NULL,
  `via` varchar(45) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `CAP` int NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `numCivico` int NOT NULL,
  `genere` enum('F','M') DEFAULT 'M',
  PRIMARY KEY (`codiceFiscale`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nTelefono_UNIQUE` (`nTelefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('DNNGLI01R51A717E','Giulia','Donnarumma','2001-10-11','gi.donnarumma@gmail.com','3318899543','giDonna8','Giuseppe Mazzini','Battipaglia',84091,'SA',12,'F'),('GNVLRT01R10F924V','Alberto','Genovese','2001-10-10','alb.genovese@gmail.com','3342524079','Gengar10?','Dei Gigli','Tufino',80030,'NA',11,'M'),('RSSMRA96A01F839E','Mario','Rossi','1996-01-01','mario.rossi@gmail.com','3338899210','marioRossi','Brombeis','Napoli',80121,'NA',12,'M'),('SFODGL02M46H703Y','Sofia','De Angelis','2002-08-06','sofia.deangelis02@gmail.com','3331122345','sofia!','Lungomare Trieste','Salerno',84122,'SA',56,'F'),('TDSLLD00E18C129Y','Leopoldo','Todisco','2000-05-18','leopoldo.todiscozte@gmail.com','3887868300','le0p0ld0','Casa Russo','Sant\'Antonio Abate',80057,'NA',77,'M');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23 16:18:27
