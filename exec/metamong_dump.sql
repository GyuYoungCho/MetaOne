-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 13.125.29.233    Database: metamong
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `education_id` int DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `pass_time` int DEFAULT NULL,
  `is_educated` tinyint DEFAULT '0',
  `is_authenticated` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `education_id` (`education_id`),
  CONSTRAINT `certificate_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `certificate_ibfk_2` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (8,6,2,'2021-11-18 04:04:33',45,1,1),(15,6,1,'2021-11-18 03:00:11',74,1,1),(16,7,2,'2021-11-18 02:27:50',49,1,1),(17,7,1,'2021-11-18 04:03:14',45,1,1),(18,1,1,'2021-11-18 11:17:59',57,1,1),(22,25,1,'2021-11-18 19:04:37',49,1,1);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (1,'Ch01.png'),(2,'Ch02.png'),(3,'Ch03.png'),(4,'Ch04.png'),(5,'Ch05.png'),(6,'Ch06.png'),(7,'Ch07.png'),(8,'Ch08.png'),(9,'Ch09.png'),(10,'Ch10.png'),(11,'Ch11.png'),(12,'Ch12.png'),(13,'Ch13.png'),(14,'Ch14.png'),(15,'Ch15.png'),(16,'Ch16.png'),(17,'Ch17.png'),(18,'Ch18.png'),(19,'Ch19.png'),(20,'Ch20.png');
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration` int DEFAULT NULL,
  `education` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `education` (`education`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,120,'지진'),(2,120,'화재');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firebase_token`
--

DROP TABLE IF EXISTS `firebase_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firebase_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `token` varchar(300) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `firebase_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firebase_token`
--

LOCK TABLES `firebase_token` WRITE;
/*!40000 ALTER TABLE `firebase_token` DISABLE KEYS */;
INSERT INTO `firebase_token` VALUES (453,27,'e-DFgxMKuzBgLfYA3Ch9Wa:APA91bGp7S6tjgW5rtF4UObqU5MYTK_QILXPRF0AjlCb8cEEmSWCIl2pEriZ5CPa4rtxlSGURMbf6XPoqWu9d0sltPoaFGjfCPswNhEO6FHiXRh6Z0O5FjF0BJyOAsh7y1kituYhmtck','2021-11-18 22:12:52'),(454,26,'fTIs2udje-P6LJTpk1tUjx:APA91bEKi7lrh9JUc-bY3wdxtgbvsjYz3kyga0IzKPlCqsfziKgqHiCgC3fMRlV1n0et5AnSPM4lB2s7lTvQZbHVBCl4bU7c4nUuzE1E89_jxc6qaphLkr2D21wsLP3_jojQhfCgrOQP','2021-11-18 22:13:19');
/*!40000 ALTER TABLE `firebase_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_book`
--

DROP TABLE IF EXISTS `guest_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `guest_book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_book`
--

LOCK TABLES `guest_book` WRITE;
/*!40000 ALTER TABLE `guest_book` DISABLE KEYS */;
INSERT INTO `guest_book` VALUES (11,7,'2021-11-17 02:18:15','우하하하하하'),(14,6,'2021-11-17 02:34:13','?'),(16,7,'2021-11-17 10:11:14','하이룽~'),(17,6,'2021-11-17 10:30:17','명록아~'),(19,6,'2021-11-17 20:55:29','잘 왔다 갑니다'),(20,7,'2021-11-18 21:06:41','일빠 ㅋㅠㅠ'),(23,1,'2021-11-18 09:40:13','출근!'),(24,25,'2021-11-18 19:07:16','오예~'),(25,28,'2021-11-18 21:16:37','유니티는 어려워ㅠ');
/*!40000 ALTER TABLE `guest_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sent_user_id` int DEFAULT NULL,
  `recv_user_id` int DEFAULT NULL,
  `title` varchar(300) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `is_read` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sent_user_id` (`sent_user_id`),
  KEY `recv_user_id` (`recv_user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sent_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`recv_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (39,6,1,'알림','가니','2021-11-17 23:05:41',1),(40,1,6,'쪽지','답장','2021-11-17 23:07:45',1),(41,6,1,'?','?','2021-11-17 23:11:52',1),(42,1,6,'바로가는','쪽지','2021-11-17 23:13:28',1),(43,6,1,'그냥','보내보기','2021-11-17 23:14:34',1),(50,6,1,'새쪽지','보내기','2021-11-18 11:48:13',1),(51,7,6,'ㅠㅠ','ㅠㅠ 나 왜 안보여','2021-11-18 18:45:06',0),(52,25,27,'뭐가 안돼','외않되','2021-11-18 19:08:06',1),(53,27,25,'그냥','안돼','2021-11-18 19:08:42',1),(54,25,7,'어디야','윤주야 도대체 어디에 있는거니','2021-11-18 21:09:06',1),(55,7,25,'ㅠㅠ','나좀 찾아줘','2021-11-18 21:09:57',1),(56,28,7,'쪽지','보낸다~~','2021-11-18 21:21:17',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `max_population` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `character_id` int DEFAULT NULL,
  `auth` tinyint DEFAULT '0',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `state` tinyint DEFAULT '0',
  `tutorial` tinyint DEFAULT '0',
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`),
  KEY `character_id` (`character_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'rbud33@naver.com','{bcrypt}$2a$10$oa4ElSuhG6CZuRtF.iE36e0O1tZzWwsmEiYbYrz2OCQbOxi7sil3a',3,0,'메타멍','조규영',0,1,NULL),(5,'jinwoo6612@naver.com','{bcrypt}$2a$10$UAl49VgvqZWfM2JSczpZAe897n8QVWY29OKgnyAS1TaaII0DELite',NULL,0,'메타몽','진우',0,0,NULL),(6,'rbud613@naver.com','0',5,0,'3성','조규영',0,1,NULL),(7,'bbung64@naver.com','0',1,0,'쿠키런마스터','김윤주',0,1,NULL),(8,'yoonzuckerberg@gmail.com','{bcrypt}$2a$10$G.BKL8iQGkvdfVj59Z5ULOcvZbUgOax6fte3OyVUf2ZXkmX7omIoq',3,0,'zi존두두','윤두두',0,1,NULL),(9,'24_nahyun@hanmail.net','{bcrypt}$2a$10$DP8pyAgrueHtJPqFUYml5udkmfQKPF0NGRnY5M7pW/kopNz6zOk1i',NULL,0,'nahyun','nahyun',0,0,NULL),(25,'1996skdms@gmail.com','{bcrypt}$2a$10$KesOhkdfGWP.IO2LtKgQUevPzKb91Z4aaucbfTlm4QFBnli2f7hnO',1,0,'ssafy','김나은',0,1,NULL),(26,'yjyjk20@naver.com','{bcrypt}$2a$10$r.hu0Z2C/QPF/pGFTtrjWeMd7WoZqTtGILojAnSBVJPewwxJ2.iz6',8,0,'어쩔티비','어쩔티비',1,1,NULL),(27,'rbdud613@naver.com','{bcrypt}$2a$10$L8jsxFKkkPOSXa0X0HYzueYLqVS.QU0Y4QR0l27jbhKTj4Xj68pvO',2,0,'안돼에','조규영',1,1,NULL),(28,'1996skdms@naver.com','0',5,0,'유니티어렵','김나은',0,1,NULL);
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

-- Dump completed on 2021-11-18 22:35:32
