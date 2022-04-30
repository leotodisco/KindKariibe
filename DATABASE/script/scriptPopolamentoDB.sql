CREATE DATABASE  IF NOT EXISTS `kindkaribe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kindkaribe`;
-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
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
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('Babà','Profumatissimo'),('Gelato','Fresci ed artigianale'),('Monoporzione','porzione per una singola persona'),('Piccola pasticceria','Perfetta per le domeniche'),('Torta','tutti i gusti');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
INSERT INTO `composizione` VALUES ('Mimosa',1,10,6,1),('Torta amarena',1,10,6,1),('Torta Cereali',2,10,10,1),('Tronchetto Amarena',2,10,16,1);
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
INSERT INTO `corriere` VALUES (1,'3451234543','brt'),(2,'3343354634','sda'),(3,'4565754345','poste');
/*!40000 ALTER TABLE `corriere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `costituzione`
--

LOCK TABLES `costituzione` WRITE;
/*!40000 ALTER TABLE `costituzione` DISABLE KEYS */;
INSERT INTO `costituzione` VALUES ('Cioccolato','Vaschetta bigusto'),('Pistacchio','Vaschetta bigusto');
/*!40000 ALTER TABLE `costituzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `datiFiscali`
--

LOCK TABLES `datiFiscali` WRITE;
/*!40000 ALTER TABLE `datiFiscali` DISABLE KEYS */;
INSERT INTO `datiFiscali` VALUES (1,1,'Abbacchio',3,'CE',81020,'Caserta'),(2,2,'Roma',23,'SA',84084,'Nocera'),(3,3,'Napoli',2,'NA',34098,'Napoli');
/*!40000 ALTER TABLE `datiFiscali` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `datiPagamento`
--

LOCK TABLES `datiPagamento` WRITE;
/*!40000 ALTER TABLE `datiPagamento` DISABLE KEYS */;
INSERT INTO `datiPagamento` VALUES ('DNNGLI01R51A717E',1),('GNVLRT01R10F924V',2),('RSSMRA96A01F839E',3),('TDSLLD00E18C129Y',4);
/*!40000 ALTER TABLE `datiPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `datiProdotto`
--

LOCK TABLES `datiProdotto` WRITE;
/*!40000 ALTER TABLE `datiProdotto` DISABLE KEYS */;
INSERT INTO `datiProdotto` VALUES (500),(750),(1000);
/*!40000 ALTER TABLE `datiProdotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estensione`
--

LOCK TABLES `estensione` WRITE;
/*!40000 ALTER TABLE `estensione` DISABLE KEYS */;
INSERT INTO `estensione` VALUES ('Vaschetta bigusto',500);
/*!40000 ALTER TABLE `estensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gusto`
--

LOCK TABLES `gusto` WRITE;
/*!40000 ALTER TABLE `gusto` DISABLE KEYS */;
INSERT INTO `gusto` VALUES ('Cioccolato','marrone','con pregiato cioccolato olandese',200),('Nocciola','marrone chiaro','con nocciole piemonte igp',500),('Pistacchio','verde','fatto con veri pistacchi siciliani',300);
/*!40000 ALTER TABLE `gusto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES (2,1206,1280,'mono_mimosa.jpg','mimosa','festa della donna'),(3,960,1280,'torta_amarena.jpg','torta amarena','torta'),(4,1024,1280,'torta_cereali.jpg','torta cereali','torta'),(5,960,1280,'gelato_pistacchio','vaschetta bigusto','vaschetta');
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `metodoPagamento`
--

LOCK TABLES `metodoPagamento` WRITE;
/*!40000 ALTER TABLE `metodoPagamento` DISABLE KEYS */;
INSERT INTO `metodoPagamento` VALUES (1,'Carta','Giulia Donnaruma','1234567898765432',4,2026,NULL,NULL,'Mastercard',234),(2,'Carta','Alberto Genovese','0987654321234567',6,2027,NULL,NULL,'Postepay',575),(3,'Carta','Mario Rossi','3412340985651276',12,2028,NULL,NULL,'Mastercard',456),(4,'Carta','Leopoldo Todisco','1276452387567867',11,2023,NULL,NULL,'Mastercard',345);
/*!40000 ALTER TABLE `metodoPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,1,1,'DNNGLI01R51A717E',12,NULL,'2022-12-03',NULL,NULL,'refrgr'),(2,2,2,'GNVLRT01R10F924V',26,NULL,'2022-04-03',NULL,NULL,'fdgvdf');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `possessoImmagine`
--

LOCK TABLES `possessoImmagine` WRITE;
/*!40000 ALTER TABLE `possessoImmagine` DISABLE KEYS */;
INSERT INTO `possessoImmagine` VALUES ('Mimosa',2),('Torta amarena',3),('Torta cereali',4),('vaschetta bigusto',5);
/*!40000 ALTER TABLE `possessoImmagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES ('Mimosa','Monoporzione','Pasticceria',6,'Ottima per la festa della donna',2,10),('Torta Amarena','Torta','Pasticceria',6,'Deliziosa torta all\'amarena',5,10),('Torta Cereali','Torta','Pasticceria',10,'Kinder cereali',1,10),('Tronchetto Amarena','Torta','Pasticceria',16,'Anarena e cioccolato',4,10),('Vaschetta bigusto','Gelato','Vaschetta',9,'Pistacchio e cioccolato',6,10);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2022-04-30 13:03:30
