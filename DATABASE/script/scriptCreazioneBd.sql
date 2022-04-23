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
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composizione` (
  `prodotto` varchar(30) NOT NULL,
  `ordine` int NOT NULL,
  `IVA` double NOT NULL,
  `prezzo` double NOT NULL,
  `quantita` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`prodotto`,`ordine`),
  KEY `ordineReference_idx` (`ordine`),
  CONSTRAINT `ordineReference` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`idOrdine`),
  CONSTRAINT `prodottoOrdine` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
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
  `prodotto` varchar(30) NOT NULL,
  PRIMARY KEY (`gusto`,`prodotto`),
  KEY `product_idx` (`prodotto`),
  CONSTRAINT `gusto` FOREIGN KEY (`gusto`) REFERENCES `gusto` (`nome`),
  CONSTRAINT `product` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costituzione`
--

LOCK TABLES `costituzione` WRITE;
/*!40000 ALTER TABLE `costituzione` DISABLE KEYS */;
/*!40000 ALTER TABLE `costituzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiFiscali`
--

DROP TABLE IF EXISTS `datiFiscali`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiFiscali` (
  `idDatiFiscali` int NOT NULL AUTO_INCREMENT,
  `metodoPagamento` int NOT NULL,
  `numeroFattura` varchar(15) NOT NULL,
  `via` varchar(45) NOT NULL,
  `nCivico` int NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `CAP` int NOT NULL,
  `citta` varchar(45) NOT NULL,
  PRIMARY KEY (`idDatiFiscali`),
  KEY `_metodoPagamento_idx` (`metodoPagamento`),
  CONSTRAINT `_metodoPagamento` FOREIGN KEY (`metodoPagamento`) REFERENCES `metodoPagamento` (`idMetodoPagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiFiscali`
--

LOCK TABLES `datiFiscali` WRITE;
/*!40000 ALTER TABLE `datiFiscali` DISABLE KEYS */;
/*!40000 ALTER TABLE `datiFiscali` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiPagamento`
--

DROP TABLE IF EXISTS `datiPagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiPagamento` (
  `utente` varchar(16) NOT NULL,
  `metodo` int NOT NULL,
  PRIMARY KEY (`utente`,`metodo`),
  KEY `method_idx` (`metodo`),
  CONSTRAINT `method` FOREIGN KEY (`metodo`) REFERENCES `metodoPagamento` (`idMetodoPagamento`),
  CONSTRAINT `userPayments` FOREIGN KEY (`utente`) REFERENCES `utente` (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiPagamento`
--

LOCK TABLES `datiPagamento` WRITE;
/*!40000 ALTER TABLE `datiPagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `datiPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiProdotto`
--

DROP TABLE IF EXISTS `datiProdotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiProdotto` (
  `peso` double NOT NULL,
  PRIMARY KEY (`peso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiProdotto`
--

LOCK TABLES `datiProdotto` WRITE;
/*!40000 ALTER TABLE `datiProdotto` DISABLE KEYS */;
/*!40000 ALTER TABLE `datiProdotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estensione`
--

DROP TABLE IF EXISTS `estensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estensione` (
  `prodotto` varchar(30) NOT NULL,
  `peso` double NOT NULL,
  PRIMARY KEY (`prodotto`,`peso`),
  KEY `peso_idx` (`peso`),
  CONSTRAINT `peso` FOREIGN KEY (`peso`) REFERENCES `datiProdotto` (`peso`),
  CONSTRAINT `prodotto_` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estensione`
--

LOCK TABLES `estensione` WRITE;
/*!40000 ALTER TABLE `estensione` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodoPagamento`
--

DROP TABLE IF EXISTS `metodoPagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodoPagamento` (
  `idMetodoPagamento` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('Carta','Bonifico') NOT NULL,
  `nomeIntestatario` varchar(70) NOT NULL,
  `numeroCarta` varchar(16) DEFAULT NULL,
  `meseScadenza` int DEFAULT NULL,
  `annoScadenza` int DEFAULT NULL,
  `IBAN` varchar(27) DEFAULT NULL,
  `causale` varchar(45) DEFAULT NULL,
  `circuito` enum('Paypal','Mastercard','Postepay') DEFAULT NULL,
  `dataPagamento` datetime NOT NULL,
  `CVV` int DEFAULT NULL,
  PRIMARY KEY (`idMetodoPagamento`),
  UNIQUE KEY `numeroCarta_UNIQUE` (`numeroCarta`),
  UNIQUE KEY `IBAN_UNIQUE` (`IBAN`),
  UNIQUE KEY `CVV_UNIQUE` (`CVV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodoPagamento`
--

LOCK TABLES `metodoPagamento` WRITE;
/*!40000 ALTER TABLE `metodoPagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodoPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL AUTO_INCREMENT,
  `datiFiscali` int DEFAULT NULL,
  `corriere` int NOT NULL,
  `utente` varchar(16) NOT NULL,
  `costoTotale` double NOT NULL,
  `codiceSconto` varchar(12) DEFAULT NULL,
  `dataEvasione` date NOT NULL,
  `dataPartenza` datetime DEFAULT NULL,
  `dataArrivo` datetime DEFAULT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `fiscali_idx` (`datiFiscali`),
  KEY `spedizione_idx` (`corriere`),
  KEY `_user_idx` (`utente`),
  CONSTRAINT `_user` FOREIGN KEY (`utente`) REFERENCES `utente` (`codiceFiscale`),
  CONSTRAINT `fiscali` FOREIGN KEY (`datiFiscali`) REFERENCES `datiFiscali` (`idDatiFiscali`),
  CONSTRAINT `spedizione` FOREIGN KEY (`corriere`) REFERENCES `corriere` (`idCorriere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possessoImmagine`
--

DROP TABLE IF EXISTS `possessoImmagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `possessoImmagine` (
  `prodotto` varchar(30) NOT NULL,
  `immagine` int NOT NULL,
  PRIMARY KEY (`prodotto`,`immagine`),
  KEY `immagine_idx` (`immagine`),
  CONSTRAINT `immagine` FOREIGN KEY (`immagine`) REFERENCES `immagine` (`idImmagine`),
  CONSTRAINT `ImmagineProdotto` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`nome`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possessoImmagine`
--

LOCK TABLES `possessoImmagine` WRITE;
/*!40000 ALTER TABLE `possessoImmagine` DISABLE KEYS */;
/*!40000 ALTER TABLE `possessoImmagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `nome` varchar(30) NOT NULL,
  `categoria` varchar(40) DEFAULT NULL,
  `tipo` enum('Vaschetta','Pasticceria') NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` tinytext,
  `quantitaDisponibili` double NOT NULL,
  `IVA` double NOT NULL,
  PRIMARY KEY (`nome`),
  KEY `appartenenzaCategoria_idx` (`categoria`),
  CONSTRAINT `appartenenzaCategoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
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
  `prodotto` varchar(30) NOT NULL,
  `utente` varchar(16) NOT NULL,
  `voto` int NOT NULL,
  `testo` tinytext,
  PRIMARY KEY (`idRecensione`),
  KEY `prodotto_idx` (`prodotto`),
  KEY `user_idx` (`utente`),
  CONSTRAINT `prodotto` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`nome`),
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

-- Dump completed on 2022-04-23 16:19:45
