-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: eight_queens_game
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `game_results`
--

DROP TABLE IF EXISTS `game_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_results` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_name` varchar(255) NOT NULL,
  `time_seconds` bigint NOT NULL,
  `method` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_results`
--

LOCK TABLES `game_results` WRITE;
/*!40000 ALTER TABLE `game_results` DISABLE KEYS */;
INSERT INTO `game_results` VALUES (1,'rabbit',16,'manual','win'),(2,'cat',3,'manual','loss'),(3,'5eyry',67,'manual','loss'),(4,'5eyry',93,'manual','loss'),(5,'5eyry',51,'manual','loss'),(6,'5eyry',93,'manual','loss'),(7,'5eyry',62,'manual','loss'),(8,'5eyry',22,'manual','loss'),(9,'5eyry',11,'manual','loss'),(10,'5eyry',27,'manual','loss'),(11,'5eyry',56,'manual','loss'),(12,'5eyry',36,'manual','loss'),(13,'5eyry',32,'manual','win'),(14,'maaaaaaaaaaaa',13,'manual','win'),(15,'Malindie',12,'manual','win'),(16,'Malindie',7,'manual','loss'),(17,'Devindi',4,'manual','loss'),(18,'RMalindi',40,'manual','win'),(19,'DEvin',22,'manual','loss'),(20,'malendie',17,'manual','win'),(21,'malendie',5,'manual','loss'),(22,'malendie',3,'manual','loss'),(23,'malendie',1,'manual','loss'),(24,'malendie',11,'manual','loss'),(25,'malendie',6,'manual','loss'),(26,'Malithi',3,'manual','win'),(27,'hhh',9,'manual','win'),(28,'fff',3,'manual','win'),(29,'Vihaga',94,'manual','win'),(30,'Dushan',2,'manual','win'),(31,'PRIYANTHA',15,'manual','win'),(32,'PERERA',3,'manual','loss');
/*!40000 ALTER TABLE `game_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_attempts`
--

DROP TABLE IF EXISTS `player_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_attempts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_name` varchar(255) NOT NULL,
  `solution` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_attempts`
--

LOCK TABLES `player_attempts` WRITE;
/*!40000 ALTER TABLE `player_attempts` DISABLE KEYS */;
INSERT INTO `player_attempts` VALUES (1,'TestUser','[0, 4, 7, 5, 2, 6, 1, 3]');
/*!40000 ALTER TABLE `player_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solutions`
--

DROP TABLE IF EXISTS `solutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solutions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `solution` text NOT NULL,
  `recognized` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solutions`
--

LOCK TABLES `solutions` WRITE;
/*!40000 ALTER TABLE `solutions` DISABLE KEYS */;
/*!40000 ALTER TABLE `solutions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-30 21:22:18
