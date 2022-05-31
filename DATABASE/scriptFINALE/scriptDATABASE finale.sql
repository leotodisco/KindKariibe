CREATE DATABASE  IF NOT EXISTS `kindkaribe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kindkaribe`;
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `nome` varchar(40) NOT NULL,
  `descrizione` tinytext,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('Babà','Profumatissimo'),('Gelato','Fresci ed artigianale'),('Monoporzione','porzione per una singola persona'),('Piccola pasticceria','Perfetta per le domeniche'),('Torta','tutti i gusti');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composizione` (
  `prodotto` int NOT NULL,
  `ordine` int NOT NULL,
  `IVA` double NOT NULL,
  `prezzo` double NOT NULL,
  `quantita` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`prodotto`,`ordine`),
  KEY `ordineReference_idx` (`ordine`),
  CONSTRAINT `ordineReference` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`idOrdine`),
  CONSTRAINT `prodottoOrdine` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
INSERT INTO `composizione` VALUES ('1',1,10,6,1),('2',1,10,6,1),('3',2,10,10,1),('4',2,10,16,1);
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corriere`
--

DROP TABLE IF EXISTS `corriere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corriere` (
  `idCorriere` int NOT NULL AUTO_INCREMENT,
  `nTelefono` varchar(10) NOT NULL,
  `azienda` varchar(45) NOT NULL,
  PRIMARY KEY (`idCorriere`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
INSERT INTO `corriere` VALUES (1,'3451234543','brt'),(2,'3343354634','sda'),(3,'4565754345','poste');
/*!40000 ALTER TABLE `corriere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costituzione`
--

DROP TABLE IF EXISTS `costituzione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `costituzione` (
  `gusto` varchar(30) NOT NULL,
  `prodotto` int NOT NULL,
  PRIMARY KEY (`gusto`,`prodotto`),
  KEY `product_idx` (`prodotto`),
  CONSTRAINT `gusto` FOREIGN KEY (`gusto`) REFERENCES `gusto` (`nome`),
  CONSTRAINT `product` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costituzione`
--

LOCK TABLES `costituzione` WRITE;
/*!40000 ALTER TABLE `costituzione` DISABLE KEYS */;
INSERT INTO `costituzione` VALUES ('Cioccolato','5'),('Pistacchio','5');
/*!40000 ALTER TABLE `costituzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datifiscali`
--

DROP TABLE IF EXISTS `datifiscali`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datifiscali` (
  `idDatiFiscali` int NOT NULL AUTO_INCREMENT,
  `metodoPagamento` int NOT NULL,
  `indirizzoFatturazione` int DEFAULT NULL,
  PRIMARY KEY (`idDatiFiscali`),
  KEY `_metodoPagamento_idx` (`metodoPagamento`),
  KEY `_address_idx` (`indirizzoFatturazione`),
  CONSTRAINT `_address` FOREIGN KEY (`indirizzoFatturazione`) REFERENCES `indirizzo` (`id`),
  CONSTRAINT `_metodoPagamento` FOREIGN KEY (`metodoPagamento`) REFERENCES `metodopagamento` (`idMetodoPagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datifiscali`
--

LOCK TABLES `datifiscali` WRITE;
/*!40000 ALTER TABLE `datifiscali` DISABLE KEYS */;
INSERT INTO `datifiscali` VALUES (1,1,NULL),(2,2,NULL),(3,3,NULL);
/*!40000 ALTER TABLE `datifiscali` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datipagamento`
--

DROP TABLE IF EXISTS `datipagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datipagamento` (
  `utente` varchar(16) NOT NULL,
  `metodo` int NOT NULL,
  PRIMARY KEY (`utente`,`metodo`),
  KEY `method_idx` (`metodo`),
  CONSTRAINT `method` FOREIGN KEY (`metodo`) REFERENCES `metodopagamento` (`idMetodoPagamento`),
  CONSTRAINT `userPayments` FOREIGN KEY (`utente`) REFERENCES `utente` (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datipagamento`
--

LOCK TABLES `datipagamento` WRITE;
/*!40000 ALTER TABLE `datipagamento` DISABLE KEYS */;
INSERT INTO `datipagamento` VALUES ('DNNGLI01R51A717E',1),('GNVLRT01R10F924V',2),('RSSMRA96A01F839E',3),('TDSLLD00E18C129Y',4);
/*!40000 ALTER TABLE `datipagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiprodotto`
--

DROP TABLE IF EXISTS `datiprodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiprodotto` (
  `peso` double NOT NULL,
  PRIMARY KEY (`peso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiprodotto`
--

LOCK TABLES `datiprodotto` WRITE;
/*!40000 ALTER TABLE `datiprodotto` DISABLE KEYS */;
INSERT INTO `datiprodotto` VALUES (500),(750),(1000);
/*!40000 ALTER TABLE `datiprodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estensione`
--

DROP TABLE IF EXISTS `estensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estensione` (
  `prodotto` int NOT NULL,
  `peso` double NOT NULL,
  PRIMARY KEY (`prodotto`,`peso`),
  KEY `peso_idx` (`peso`),
  CONSTRAINT `peso` FOREIGN KEY (`peso`) REFERENCES `datiprodotto` (`peso`),
  CONSTRAINT `prodotto_` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estensione`
--

LOCK TABLES `estensione` WRITE;
/*!40000 ALTER TABLE `estensione` DISABLE KEYS */;
INSERT INTO `estensione` VALUES ('5',500);
/*!40000 ALTER TABLE `estensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gusto`
--

DROP TABLE IF EXISTS `gusto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gusto` (
  `nome` varchar(30) NOT NULL,
  `colore` varchar(20) NOT NULL,
  `descrizione` tinytext,
  `quantitaResidua` double NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gusto`
--

LOCK TABLES `gusto` WRITE;
/*!40000 ALTER TABLE `gusto` DISABLE KEYS */;
INSERT INTO `gusto` VALUES ('Cioccolato','marrone','con pregiato cioccolato olandese',200),('Nocciola','marrone chiaro','con nocciole piemonte igp',500),('Pistacchio','verde','fatto con veri pistacchi siciliani',300);
/*!40000 ALTER TABLE `gusto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine`
--

DROP TABLE IF EXISTS `immagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immagine` (
  `idImmagine` int NOT NULL AUTO_INCREMENT,
  `altezza` double NOT NULL,
  `lunghezza` double NOT NULL,
  `URL` varchar(45) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `testoALT` varchar(20) NOT NULL,
  PRIMARY KEY (`idImmagine`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES (1,1206,1280,'tronchetto.jpg','Tronchetto Amarena','buono'),(2,1206,1280,'mono_mimosa.jpg','mimosa','festa della donna'),(3,960,1280,'torta_amarena.jpg','torta amarena','torta'),(4,1024,1280,'torta_cereali.jpg','torta cereali','torta'),(5,960,1280,'gelato_pistacchio','vaschetta bigusto','vaschetta');
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `via` varchar(45) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `numCivico` varchar(10) NOT NULL,
  `CAP` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,'Casa Russo','angri','SA','77',80057),(2,'Roma','Nocera Inferiore','SA','44',84012),(3,'Matteotti','Gragnano','NA','32',87006),(4,'Cavour','Napoli','NA','91',85077),(5,'Università','Portici','NA','34',80755),(6,'Giovanni XII','Fisciano','SA','14',81211);
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodopagamento`
--

DROP TABLE IF EXISTS `metodopagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodopagamento` (
  `idMetodoPagamento` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('Carta','Bonifico') NOT NULL,
  `nomeIntestatario` varchar(70) NOT NULL,
  `numeroCarta` varchar(16) DEFAULT NULL,
  `meseScadenza` int DEFAULT NULL,
  `annoScadenza` int DEFAULT NULL,
  `IBAN` varchar(27) DEFAULT NULL,
  `causale` varchar(45) DEFAULT NULL,
  `circuito` enum('Paypal','Mastercard','Postepay') DEFAULT NULL,
  `CVV` int DEFAULT NULL,
  PRIMARY KEY (`idMetodoPagamento`),
  UNIQUE KEY `numeroCarta_UNIQUE` (`numeroCarta`),
  UNIQUE KEY `IBAN_UNIQUE` (`IBAN`),
  UNIQUE KEY `CVV_UNIQUE` (`CVV`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodopagamento`
--

LOCK TABLES `metodopagamento` WRITE;
/*!40000 ALTER TABLE `metodopagamento` DISABLE KEYS */;
INSERT INTO `metodopagamento` VALUES (1,'Carta','Giulia Donnaruma','1234567898765432',4,2026,NULL,NULL,'Mastercard',234),(2,'Carta','Alberto Genovese','0987654321234567',6,2027,NULL,NULL,'Postepay',575),(3,'Carta','Mario Rossi','3412340985651276',12,2028,NULL,NULL,'Mastercard',456),(4,'Carta','Leopoldo Todisco','1276452387567867',11,2023,NULL,NULL,'Mastercard',345);
/*!40000 ALTER TABLE `metodopagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL AUTO_INCREMENT,
  `datiFiscali` int NOT NULL,
  `corriere` int NOT NULL,
  `utente` varchar(16) NOT NULL,
  `costoTotale` double NOT NULL,
  `codiceSconto` varchar(12) DEFAULT NULL,
  `dataEvasione` date NOT NULL,
  `dataPartenza` datetime DEFAULT NULL,
  `dataArrivo` datetime DEFAULT NULL,
  `urlPdf` varchar(100) NOT NULL,
  `indirizzoSpedizione` int DEFAULT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `fiscali_idx` (`datiFiscali`),
  KEY `spedizione_idx` (`corriere`),
  KEY `_user_idx` (`utente`),
  KEY `indirizzo__idx` (`indirizzoSpedizione`),
  CONSTRAINT `_user` FOREIGN KEY (`utente`) REFERENCES `utente` (`codiceFiscale`),
  CONSTRAINT `fiscali` FOREIGN KEY (`datiFiscali`) REFERENCES `datifiscali` (`idDatiFiscali`),
  CONSTRAINT `indirizzo_` FOREIGN KEY (`indirizzoSpedizione`) REFERENCES `indirizzo` (`id`),
  CONSTRAINT `spedizione` FOREIGN KEY (`corriere`) REFERENCES `corriere` (`idCorriere`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,1,1,'DNNGLI01R51A717E',12,NULL,'2022-12-03',NULL,NULL,'refrgr',NULL),(2,2,2,'GNVLRT01R10F924V',26,NULL,'2022-04-03',NULL,NULL,'fdgvdf',NULL);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possessoimmagine`
--

DROP TABLE IF EXISTS `possessoimmagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `possessoimmagine` (
  `prodotto` int NOT NULL,
  `immagine` int NOT NULL,
  PRIMARY KEY (`prodotto`,`immagine`),
  KEY `immagine_idx` (`immagine`),
  CONSTRAINT `immagine` FOREIGN KEY (`immagine`) REFERENCES `immagine` (`idImmagine`),
  CONSTRAINT `ImmagineProdotto` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possessoimmagine`
--

LOCK TABLES `possessoimmagine` WRITE;
/*!40000 ALTER TABLE `possessoimmagine` DISABLE KEYS */;
INSERT INTO `possessoimmagine` VALUES ('2',1),('1',2),('3',3),('4',4),('5',5);
/*!40000 ALTER TABLE `possessoimmagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possessoIndirizzo`
--

DROP TABLE IF EXISTS `possessoIndirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `possessoIndirizzo` (
  `utente` varchar(16) NOT NULL,
  `indirizzo` int NOT NULL,
  PRIMARY KEY (`utente`,`indirizzo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possessoIndirizzo`
--

LOCK TABLES `possessoIndirizzo` WRITE;
/*!40000 ALTER TABLE `possessoIndirizzo` DISABLE KEYS */;
INSERT INTO `possessoIndirizzo` VALUES ('TDSLLD00E18C129Y',1),('TDSLLD00E18C129Y',2);
/*!40000 ALTER TABLE `possessoIndirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `nome` varchar(30) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT, 
  `categoria` varchar(40) DEFAULT NULL,
  `tipo` enum('Vaschetta','Pasticceria') NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` tinytext,
  `quantitaDisponibili` double NOT NULL,
  `IVA` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `appartenenzaCategoria_idx` (`categoria`),
  CONSTRAINT `appartenenzaCategoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES ('Mimosa','1','Monoporzione','Pasticceria',6,'Ottima per la festa della donna',2,10),('Torta Amarena','2','Torta','Pasticceria',6,'Deliziosa torta all\'amarena',5,10),('Torta Cereali','3','Torta','Pasticceria',10,'Kinder cereali',1,10),('Tronchetto Amarena','4','Torta','Pasticceria',16,'Anarena e cioccolato',4,10),('Vaschetta bigusto','5','Gelato','Vaschetta',9,'Pistacchio e cioccolato' , 6 ,10);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `idRecensione` int NOT NULL AUTO_INCREMENT,
  `prodotto` int NOT NULL,
  `utente` varchar(16) NOT NULL,
  `voto` int NOT NULL,
  `testo` tinytext,
  PRIMARY KEY (`idRecensione`),
  KEY `prodotto_idx` (`prodotto`),
  KEY `user_idx` (`utente`),
  CONSTRAINT `prodotto` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`id`),
  CONSTRAINT `user` FOREIGN KEY (`utente`) REFERENCES `utente` (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

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
  `genere` enum('F','M') DEFAULT 'M',
  `admin` tinyint(1) DEFAULT '0',
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
INSERT INTO `utente` VALUES ('DNNGLI01R51A717E','Giulia','Donnarumma','2001-10-11','gi.donnarumma@gmail.com','3318899543','giDonna8','F',0),('GNVLRT01R10F924V','Alberto','Genovese','2001-10-10','alb.genovese@gmail.com','3342524079','Gengar10?','M',1),('RSSMRA96A01F839E','Mario','Rossi','1996-01-01','mario.rossi@gmail.com','3456545678','marioRossi','M',0),('SFODGL02M46H703Y','Sofia','De Angelis','2002-08-06','sofia.deangelis02@gmail.com','3467896756','sofia!','F',0),('TDSLLD00E18C129Y','Leopoldo','Todisco','2000-05-18','leopoldo.todiscozte@gmail.com','3245167890','le0p0ld0','M',1);
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

-- Dump completed on 2022-05-05 12:43:04
