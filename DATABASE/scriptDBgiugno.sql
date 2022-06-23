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
INSERT INTO `composizione` VALUES (1,1,10,6,1),(1,4,10,6,1),(1,7,10,6,2),(1,10,10,6,1),(1,17,10,6,2),(2,1,10,6,1),(2,5,10,6,1),(2,6,10,6,2),(2,7,10,6,2),(2,8,10,6,1),(2,9,10,6,1),(2,10,10,6,2),(2,16,10,6,1),(2,17,10,6,2),(2,18,10,6,1),(3,2,10,10,1),(3,3,10,10,2),(3,5,10,10,1),(3,7,10,10,2),(3,13,10,10,1),(3,14,10,10,5),(3,15,10,10,1),(3,18,10,10,1),(4,2,10,16,1),(4,3,10,16,2),(4,6,10,16,2),(4,8,10,16,1),(4,9,10,16,1),(4,13,10,16,1),(4,16,10,16,1),(4,17,10,16,1),(6,17,10,10,2),(7,3,10,10,2),(7,10,10,10,2),(8,10,10,8,1),(8,13,10,8,2),(9,11,10,5,1),(10,3,10,7.5,2),(10,6,10,7.5,2),(10,11,10,7.5,1),(10,12,10,7.5,1),(11,9,10,12.5,2),(11,12,10,12.5,1);
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
INSERT INTO `costituzione` VALUES ('Cioccolato',5),('Pistacchio',5),('Fragola',12);
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datifiscali`
--

LOCK TABLES `datifiscali` WRITE;
/*!40000 ALTER TABLE `datifiscali` DISABLE KEYS */;
INSERT INTO `datifiscali` VALUES (1,1,NULL),(2,2,NULL),(3,3,NULL),(4,4,1),(5,4,1),(6,5,7),(7,5,7),(8,5,7),(9,5,7),(10,5,7),(11,5,7),(12,5,7),(13,5,7),(14,5,7),(15,5,7),(16,5,7),(17,1,8),(18,1,8),(19,4,1);
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
INSERT INTO `datipagamento` VALUES ('DNNGLI01R51A717E',1),('GNVLRT01R10F924V',2),('RSSMRA96A01F839E',3),('TDSLLD00E18C129Y',4),('root',5),('root',6),('root',7),('root',9);
/*!40000 ALTER TABLE `datipagamento` ENABLE KEYS */;
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
INSERT INTO `gusto` VALUES ('Cioccolato','azzurro','con pregiato cioccolato olandese',200),('Cioccolato Bianco','bianco',NULL,2000),('Fragola','rosa','si',1900),('Nocciola','azzurrissimo','con nocciole piemonte igp',500),('Pistacchio','verde','fatto con veri pistacchi siciliani',300);
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
  `URL` varchar(45) NOT NULL,
  `testoALT` varchar(20) NOT NULL,
  `altezza` double DEFAULT NULL,
  `lunghezza` varchar(45) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idImmagine`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES (1,'tronchetto.jpg','buono',NULL,NULL,NULL),(2,'mono_mimosa.jpg','festa della donna',NULL,NULL,NULL),(3,'torta_amarena.jpg','torta',NULL,NULL,NULL),(4,'torta_cereali.jpg','torta',NULL,NULL,NULL),(5,'gelato_pistacchio.jpg','vaschetta',NULL,NULL,NULL),(6,'Screenshot (2).png','immagine mancante',NULL,NULL,NULL),(7,'Screenshot (1).png','immagine mancante',NULL,NULL,NULL),(8,'coni.jpeg','immagine coni',NULL,NULL,NULL),(9,'bueno_coconut_torta.jpeg','bueno_coconut_torta',NULL,NULL,NULL),(10,'gelato_fragola.jpeg','gelato_fragola',NULL,NULL,NULL),(11,'nutella.jpeg','nutella',NULL,NULL,NULL),(12,'profiteroles.jpeg','profiteroles',NULL,NULL,NULL),(13,'torta_bueno.jpeg','torta_bueno',NULL,NULL,NULL),(14,'zeppola_bueno.jpeg','zeppola_bueno',NULL,NULL,NULL),(15,'gelato_cioccolato_bianco.jpeg','gelato_',NULL,NULL,NULL),(16,'aragostine.jpeg','aragostine',NULL,NULL,NULL),(17,'tiramisu.jpeg','tiramisu',NULL,NULL,NULL),(18,'sanGiuseppe.jpeg','SAN GIUSEPPE',NULL,NULL,NULL),(19,'monoporzione_castagna.jpeg','castagna',NULL,NULL,NULL),(20,'gelato_pistache.jpeg','pistacchio',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,'Casa Russo','angri','SA','77',80057),(2,'Roma','Nocera Inferiore','SA','44',84012),(3,'Matteotti','Gragnano','NA','32',87006),(4,'Cavour','Napoli','NA','91',85077),(5,'Università','Portici','NA','34',80755),(6,'Giovanni XII','Fisciano','SA','14',81211),(7,'Casa Russo','Napoli','NA','67',80021),(8,'dei mulini','Napoli','NA','77',80021),(9,'Matteotti','Milano','MI','88',80011),(10,'Conigli','Roma','RM','12',98712),(11,'Imola','Imola','BO','23',22331),(12,'Gragnano','Gragnano','NA','23',23412),(13,'Mantova','Mantova','MV','88',89765),(14,'Limoni','Amalfi','SA','22',13245);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodopagamento`
--

LOCK TABLES `metodopagamento` WRITE;
/*!40000 ALTER TABLE `metodopagamento` DISABLE KEYS */;
INSERT INTO `metodopagamento` VALUES (1,'Carta','Giulia Donnaruma','1234567898765432',4,2026,NULL,NULL,'Mastercard',234),(2,'Carta','Alberto Genovese','0987654321234567',6,2027,NULL,NULL,'Postepay',575),(3,'Carta','Mario Rossi','3412340985651276',12,2028,NULL,NULL,'Mastercard',456),(4,'Carta','Leopoldo Todisco','1276452387567867',11,2023,NULL,NULL,'Mastercard',345),(5,'Carta','root','1111111111111111',11,2029,NULL,NULL,'Mastercard',343),(6,'Carta','Giuseppe','2222222222222222',12,2029,NULL,NULL,'Mastercard',454),(7,'Carta','s','8888999933332222',11,2029,NULL,NULL,'Mastercard',111),(8,'Carta','root','5555444477778888',11,2028,NULL,NULL,'Mastercard',121),(9,'Carta','root','3333999988887777',12,2029,NULL,NULL,'Mastercard',232);
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
  `dataEvasione` datetime NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,1,1,'DNNGLI01R51A717E',12,NULL,'2021-12-03 01:00:00',NULL,NULL,'refrgr',NULL),(2,2,2,'GNVLRT01R10F924V',26,NULL,'2022-04-03 02:00:00',NULL,NULL,'fdgvdf',NULL),(3,4,3,'TDSLLD00E18C129Y',43.5,'','2022-06-22 03:00:00',NULL,NULL,'placeolder',1),(4,5,3,'TDSLLD00E18C129Y',6,'','2022-06-22 04:00:00',NULL,NULL,'placeolder',1),(5,6,3,'root',16,'','2022-06-22 05:00:00',NULL,NULL,'placeolder',7),(6,7,3,'root',29.5,'','2022-06-22 06:00:00',NULL,NULL,'placeolder',7),(7,8,3,'root',22,'','2022-06-22 07:00:00',NULL,NULL,'placeolder',7),(8,9,3,'root',22,'','2022-06-22 08:00:00',NULL,NULL,'placeolder',7),(9,10,3,'root',34.5,'','2022-06-22 09:00:00',NULL,NULL,'placeolder',7),(10,11,3,'root',30,'','2022-06-22 10:00:00',NULL,NULL,'placeolder',7),(11,12,3,'root',12.5,'','2022-06-22 11:00:00',NULL,NULL,'placeolder',7),(12,13,3,'root',20,'','2022-06-22 12:00:00',NULL,NULL,'placeolder',7),(13,14,3,'root',34,'','2022-06-22 13:00:00',NULL,NULL,'placeolder',7),(14,15,3,'root',50,'','2022-06-22 14:00:00',NULL,NULL,'placeolder',7),(15,16,3,'root',10,'','2022-06-22 23:40:41',NULL,NULL,'placeolder',7),(16,17,3,'DNNGLI01R51A717E',22,'','2022-06-22 23:56:12',NULL,NULL,'placeolder',8),(17,18,3,'DNNGLI01R51A717E',44,'','2022-06-22 23:59:41',NULL,NULL,'placeolder',8),(18,19,3,'TDSLLD00E18C129Y',16,'','2022-06-23 00:04:21',NULL,NULL,'placeolder',1);
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
INSERT INTO `possessoimmagine` VALUES (4,1),(1,2),(2,3),(3,4),(5,5),(12,10),(14,11),(10,12),(11,13),(13,15),(6,16),(8,17),(7,18),(9,19),(15,20);
/*!40000 ALTER TABLE `possessoimmagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possessoindirizzo`
--

DROP TABLE IF EXISTS `possessoindirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `possessoindirizzo` (
  `utente` varchar(16) NOT NULL,
  `indirizzo` int NOT NULL,
  PRIMARY KEY (`utente`,`indirizzo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possessoindirizzo`
--

LOCK TABLES `possessoindirizzo` WRITE;
/*!40000 ALTER TABLE `possessoindirizzo` DISABLE KEYS */;
INSERT INTO `possessoindirizzo` VALUES ('DNNGLI01R51A717E',8),('root',7),('root',9),('root',10),('root',11),('root',12),('root',13),('root',14),('TDSLLD00E18C129Y',1),('TDSLLD00E18C129Y',2);
/*!40000 ALTER TABLE `possessoindirizzo` ENABLE KEYS */;
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
  `peso` enum('500','750','1000') DEFAULT NULL,
  `visibile` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `appartenenzaCategoria_idx` (`categoria`),
  CONSTRAINT `appartenenzaCategoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES ('Mimosa',1,'Monoporzione','Pasticceria',6,'Una buonissima monoporzione per festeggiare con dolcezza la festa della donna. La nostra mimosa utilizza limoni della costiera amalfitana!',2,10,NULL,1),('Torta Amarena',2,'Torta','Pasticceria',6,'Deliziosa torta all\'amarena, tutti i prodotti sono kilometro zero, forniti dal nostro fornitore di fiducia.',5,10,NULL,1),('Torta Cereali',3,'Torta','Pasticceria',10,'Sulla base del famoso Snack Kinder, solo da noi puoi trovare una buonissima torta Cereali per festeggiare con la tua famiglia!',1,10,NULL,1),('Tronchetto',4,'Torta','Pasticceria',16,'Il nostro tronchetto interamente artigianale con pezzi di vari prodotti Kinder. Servilo ai tuoi amici e non te ne pentirai!',4,10,NULL,1),('Vaschetta bigusto',5,'Gelato','Vaschetta',5,'Latte 100% italiano dal caseificio CheLatte situato a Battipaglia.Tutti i nostri gelati hanno il minimo quantitativo di zuccheri per un dolce fit.',6,10,'500',1),('Aragostine',6,'Piccola pasticceria','Pasticceria',10,'Un buonissimo vassoio dal peso di mezzo kg di aragostine alla crema Chantilly completamente artigianali.',5,10,NULL,1),('Zeppola San Giuseppe',7,'Piccola pasticceria','Pasticceria',10,'Un buonissimo vassoio dal peso di mezzo kg di zeppole alla crema Chantilly completamente artigianali.',5,10,NULL,1),('Tiramisu',8,'Monoporzione','Pasticceria',8,'Il famosissimo dolce al cucchiaio non ha bisogno di alcuna presentazione, la nostra versione ricalca la ricetta originale di Aldo Campeol! Provare per credere!',19,10,NULL,1),('Torta Castagna',9,'Monoporzione','Pasticceria',5,'Un dolce diverso dal solito, dalle umili origini, ma possiamo assicurarvi che si tratta di una vera prelibatezza',5,10,NULL,1),('Profiteroles',10,'Monoporzione','Pasticceria',7.5,'Dolci e golose sfere al cioccolato fondente, accompagnate da fiocchi di panna montata, tutte assemblate per dare una forma piramidale. Il dolce Francese per eccellenza!',100,10,NULL,1),('Torta Bueno',11,'Torta','Pasticceria',12.5,'La torta per antonomasia fra quelle del nostro catalogo, di fatti la sua ricetta non cambia dal 1997 dato che tutti la amano! Attenzione, potrebbe causare dipendenza!',19,10,NULL,1),('Vaschetta Fragola',12,'Gelato','Vaschetta',8,'Latte 100% italiano dal caseificio CheLatte situato a Battipaglia.Tutti i nostri gelati hanno il minimo quantitativo di zuccheri per un dolce fit.',11,10,'500',1),('Vaschetta Cioccolato Binaco',13,'Gelato','Vaschetta',8,'Latte 100% italiano dal caseificio CheLatte situato a Battipaglia.Tutti i nostri gelati hanno il minimo quantitativo di zuccheri per un dolce fit.',100,10,NULL,1),('Vaschetta Nutella',14,'Gelato','Vaschetta',8,'Latte 100% italiano dal caseificio CheLatte situato a Battipaglia.Tutti i nostri gelati hanno il minimo quantitativo di zuccheri per un dolce fit.',900,10,'500',1),('Vaschetta Pistacchio',15,'Gelato','Vaschetta',8,'Latte 100% italiano dal caseificio CheLatte situato a Battipaglia.Tutti i nostri gelati hanno il minimo quantitativo di zuccheri per un dolce fit.',8000,10,NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (1,3,'TDSLLD00E18C129Y',5,'Una torta davvero squisita, apprezzata dai piccoli, consigliata a tutti!');
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
INSERT INTO `utente` VALUES ('admin','a','admin','2000-09-09','admin','admin','admin','M',1),('bgvhndcfxrstyeui','root','dede','2001-10-10','root@gmail.com','5439087654','root','M',0),('DNNGLI01R51A717E','Giulia','Donnarumma','2001-10-11','gi.donnarumma@gmail.com','3318899543','giDonna8','F',0),('GNVLRT01R10F924V','Alberto','Genovese','2001-10-10','alb.genovese@gmail.com','3342524079','Gengar10?','M',1),('root','root','root',NULL,'root','11111111','root','M',0),('RSSMRA96A01F839E','Mario','Rossi','1996-01-01','mario.rossi@gmail.com','3456545678','marioRossi','M',0),('SFODGL02M46H703Y','Sofia','De Angelis','2002-08-06','sofia.deangelis02@gmail.com','3467896756','sofia!','F',0),('TDSLLD00E18C129Y','Leopoldo','Todisco','2000-05-18','leopoldo.todiscozte@gmail.com','3245167890','le0p0ld0','M',0);
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

-- Dump completed on 2022-06-23 17:56:55
