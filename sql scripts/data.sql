-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: covoiturage
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB-1:10.4.13+maria~bionic

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `covoiturage`
--

DROP TABLE IF EXISTS `covoiturage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `covoiturage` (
  `id` bigint(20) NOT NULL,
  `date_depart` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_fumer` bit(1) NOT NULL,
  `nbr_place_dispo` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `gouv_arr_id` bigint(20) DEFAULT NULL,
  `gouv_dep_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `ville_arr_id` bigint(20) DEFAULT NULL,
  `ville_dep_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg74a67ih7q376v9i0iopqea6d` (`gouv_arr_id`),
  KEY `FK89j2d7oi1q2kf70t7twdfg39o` (`gouv_dep_id`),
  KEY `FK340nqc9cq61ssh7eya1rv8i6k` (`user_id`),
  KEY `FKgpne1ligpr469cvg99nbqrnxw` (`ville_arr_id`),
  KEY `FK3oap8n3q9haob46adqmy1fe6j` (`ville_dep_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covoiturage`
--

LOCK TABLES `covoiturage` WRITE;
/*!40000 ALTER TABLE `covoiturage` DISABLE KEYS */;
INSERT INTO `covoiturage` (`id`, `date_depart`, `description`, `is_fumer`, `nbr_place_dispo`, `price`, `gouv_arr_id`, `gouv_dep_id`, `user_id`, `ville_arr_id`, `ville_dep_id`) VALUES (16,'2020-08-03 17:14:06',NULL,'',3,53,13,17,12,153,205),(17,'2020-08-04 11:05:01',NULL,'\0',2,39,9,9,3,92,88),(18,'2020-07-19 07:51:53',NULL,'',3,28,13,12,5,136,129),(19,'2020-08-06 20:25:59',NULL,'',2,50,15,12,12,178,123),(20,'2020-08-09 18:49:52',NULL,'\0',2,72,13,20,5,145,238),(21,'2020-07-04 21:45:19',NULL,'',3,35,12,12,14,123,126),(22,'2020-08-04 02:57:07',NULL,'\0',4,60,13,5,7,141,51),(23,'2020-06-28 11:04:03',NULL,'',4,28,15,8,3,184,80),(24,'2020-07-11 17:36:35',NULL,'\0',4,16,6,22,12,60,249),(25,'2020-08-11 03:19:30',NULL,'',1,10,7,1,13,70,4),(26,'2020-07-07 21:25:07',NULL,'',3,14,24,16,10,262,197),(27,'2020-08-02 11:48:15',NULL,'\0',2,59,8,15,6,83,180),(28,'2020-06-20 11:21:09',NULL,'',3,68,10,13,15,105,134),(29,'2020-07-29 18:22:05',NULL,'\0',2,51,11,15,13,112,184),(30,'2020-07-07 17:40:32',NULL,'',1,57,21,3,11,246,20),(31,'2020-08-15 04:50:57',NULL,'',2,81,14,24,7,172,263),(32,'2020-08-15 23:53:38',NULL,'',4,80,5,22,4,43,250),(33,'2020-07-22 12:42:36',NULL,'',2,98,5,9,9,49,87),(34,'2020-07-09 00:23:14',NULL,'',4,50,12,8,10,127,78),(35,'2020-06-28 05:10:43',NULL,'\0',4,29,15,5,6,187,50),(36,'2020-07-19 20:39:45',NULL,'',3,96,10,13,7,100,138),(37,'2020-07-04 22:38:37',NULL,'',4,45,19,12,3,226,117),(38,'2020-06-21 23:56:55',NULL,'\0',4,68,23,23,15,259,257),(39,'2020-06-21 00:30:23',NULL,'',3,61,12,3,6,130,23),(40,'2020-08-04 00:50:48',NULL,'',3,80,14,5,15,169,43),(41,'2020-07-18 13:34:13',NULL,'',4,86,5,3,13,50,23),(42,'2020-07-31 18:43:54',NULL,'\0',4,41,2,11,2,10,110),(43,'2020-08-09 15:21:32',NULL,'',1,45,3,6,9,18,59),(44,'2020-07-20 06:53:36',NULL,'',4,26,16,5,8,203,57),(45,'2020-06-27 01:14:54',NULL,'',1,44,13,16,13,150,204),(46,'2020-07-26 07:04:39',NULL,'\0',2,32,15,18,10,185,224),(47,'2020-07-31 13:25:20',NULL,'\0',4,40,1,21,8,2,243),(48,'2020-08-12 11:25:12',NULL,'\0',1,71,10,22,12,96,249),(49,'2020-08-01 22:32:04',NULL,'',4,24,10,16,10,102,198),(50,'2020-06-25 14:31:31',NULL,'\0',3,47,7,7,6,74,67),(51,'2020-08-02 11:21:50',NULL,'',4,57,12,15,2,122,178),(52,'2020-07-27 16:12:35',NULL,'',4,25,4,11,15,26,109),(53,'2020-07-18 22:33:29',NULL,'\0',4,82,23,13,5,259,135),(54,'2020-08-16 17:43:14',NULL,'',4,83,17,18,6,214,215),(55,'2020-08-05 10:23:28',NULL,'',2,31,17,13,3,210,155),(56,'2020-08-17 17:13:46',NULL,'\0',1,48,22,2,4,254,12),(57,'2020-07-24 06:46:48',NULL,'',3,47,11,1,9,109,1),(58,'2020-07-24 09:35:44',NULL,'\0',1,26,21,13,2,246,144),(59,'2020-07-25 13:30:25',NULL,'',4,35,19,15,1,231,181),(60,'2020-07-06 14:33:30',NULL,'\0',1,33,10,13,10,96,160),(61,'2020-07-05 22:24:09',NULL,'\0',2,49,13,21,3,142,242),(62,'2020-06-23 00:13:20',NULL,'',4,11,11,9,9,115,89),(63,'2020-07-25 11:58:31',NULL,'\0',4,31,19,1,6,228,4),(64,'2020-08-15 09:56:34',NULL,'\0',2,76,4,10,14,33,99),(65,'2020-07-18 22:46:40',NULL,'\0',2,64,5,2,7,42,13),(66,'2020-07-03 00:58:59',NULL,'\0',3,80,17,12,10,213,118),(67,'2020-07-25 03:15:16',NULL,'\0',3,41,7,24,13,65,262),(68,'2020-06-27 12:14:55',NULL,'\0',1,60,23,13,12,258,137),(69,'2020-08-09 02:16:00',NULL,'\0',4,75,16,3,6,203,23),(70,'2020-06-28 08:17:53',NULL,'\0',4,34,18,13,7,223,144),(71,'2020-07-04 14:16:45',NULL,'\0',3,35,10,15,9,97,180),(72,'2020-08-02 15:00:54',NULL,'',4,25,5,12,14,45,126),(73,'2020-08-06 13:38:08',NULL,'\0',2,55,12,13,7,120,133),(74,'2020-08-09 18:59:57',NULL,'',3,85,14,17,1,165,213),(75,'2020-06-29 09:36:48',NULL,'\0',2,71,5,12,9,50,120),(76,'2020-07-08 12:48:20',NULL,'',4,38,21,3,10,245,17),(77,'2020-07-29 10:52:05',NULL,'',3,11,22,10,5,248,105),(78,'2020-06-27 15:49:14',NULL,'',3,13,16,11,2,197,107),(79,'2020-06-24 15:59:21',NULL,'',4,97,17,10,6,211,104),(80,'2020-07-09 07:29:30',NULL,'\0',4,73,8,11,7,81,111),(81,'2020-07-12 07:21:50',NULL,'',4,34,3,16,12,23,203),(82,'2020-06-21 11:29:43',NULL,'\0',4,14,5,18,14,41,221),(83,'2020-08-06 16:40:34',NULL,'\0',4,37,15,1,11,177,4),(84,'2020-07-26 14:15:35',NULL,'\0',2,66,13,20,14,151,238),(85,'2020-07-15 13:52:15',NULL,'\0',4,25,5,21,4,46,244),(86,'2020-08-18 05:59:39',NULL,'',4,40,7,13,6,70,146),(87,'2020-07-18 04:46:32',NULL,'\0',2,28,24,5,1,263,58),(88,'2020-07-19 13:00:15',NULL,'\0',4,68,11,21,15,115,246),(89,'2020-07-26 18:16:19',NULL,'\0',2,83,5,16,4,53,202),(90,'2020-06-21 10:51:18',NULL,'',3,54,16,5,4,195,43),(91,'2020-07-26 02:51:05',NULL,'',3,74,17,14,3,214,164),(92,'2020-08-09 06:08:05',NULL,'',3,42,13,17,6,137,211),(93,'2020-08-11 16:23:36',NULL,'',4,33,9,1,4,88,5),(94,'2020-08-16 11:48:34',NULL,'\0',4,89,14,10,11,167,97),(95,'2020-06-23 10:53:52',NULL,'',4,14,5,5,8,44,35),(96,'2020-07-08 00:05:34',NULL,'',2,78,13,10,6,139,97),(97,'2020-07-12 22:03:22',NULL,'\0',4,49,22,12,14,254,123),(98,'2020-08-02 13:06:13',NULL,'\0',2,46,16,4,9,195,28),(99,'2020-06-23 00:45:00',NULL,'',4,72,12,12,6,117,124),(100,'2020-06-21 13:30:28',NULL,'\0',4,14,17,5,8,208,57),(101,'2020-08-02 18:56:06',NULL,'',4,70,23,12,1,258,126),(102,'2020-07-15 23:02:46',NULL,'\0',1,67,18,21,15,217,246),(103,'2020-07-05 14:20:05',NULL,'',4,26,13,14,9,162,174),(104,'2020-07-23 09:10:47',NULL,'',1,39,17,5,14,214,56),(105,'2020-06-28 18:45:28',NULL,'\0',2,70,16,7,6,194,74),(106,'2020-08-14 17:32:56',NULL,'',2,33,15,3,9,179,23),(107,'2020-08-07 14:55:29',NULL,'\0',3,56,13,13,9,132,160),(108,'2020-07-31 09:19:52',NULL,'',3,89,19,21,12,229,244),(109,'2020-07-19 02:35:08',NULL,'',3,16,7,15,13,71,179),(110,'2020-07-14 14:05:30',NULL,'\0',3,57,23,19,7,259,230),(111,'2020-07-04 15:04:21',NULL,'\0',3,92,5,5,5,54,35),(112,'2020-08-04 10:16:01',NULL,'\0',4,76,23,17,7,256,214),(113,'2020-07-03 01:34:50',NULL,'\0',2,46,3,4,10,19,28),(114,'2020-07-10 14:55:43',NULL,'\0',2,21,14,2,4,171,11),(115,'2020-07-15 22:12:52',NULL,'',2,56,19,5,4,233,53),(116,'2020-08-18 12:44:02',NULL,'',4,76,12,10,7,117,94),(117,'2020-06-24 16:02:24',NULL,'',3,33,9,16,1,89,195),(118,'2020-08-01 04:00:43',NULL,'',4,46,13,10,9,138,98),(119,'2020-07-08 13:22:03',NULL,'',4,67,12,11,4,120,107),(120,'2020-08-10 15:40:44',NULL,'',2,27,17,8,5,205,85),(121,'2020-07-09 15:47:21',NULL,'',1,29,1,3,15,7,24),(122,'2020-07-21 02:07:41',NULL,'\0',3,40,4,13,11,30,140),(123,'2020-08-06 03:46:00',NULL,'',2,18,15,5,12,180,53),(124,'2020-07-22 10:14:16',NULL,'',4,26,16,14,4,200,166),(125,'2020-07-25 23:05:55',NULL,'\0',4,14,4,12,9,30,122),(126,'2020-08-11 10:34:17',NULL,'',4,55,5,6,8,43,60),(127,'2020-06-23 08:36:57',NULL,'\0',4,30,1,3,7,6,15),(128,'2020-07-17 13:26:54',NULL,'',3,59,7,13,3,66,148),(129,'2020-07-16 12:10:32',NULL,'\0',2,50,7,17,11,71,210),(130,'2020-07-05 16:26:22',NULL,'\0',4,46,3,18,10,23,216),(131,'2020-07-31 05:49:20',NULL,'\0',1,12,1,2,7,8,14),(132,'2020-07-29 07:51:08',NULL,'',2,14,15,11,15,192,113),(133,'2020-07-23 15:50:04',NULL,'\0',4,13,22,10,5,247,99),(134,'2020-07-05 03:57:52',NULL,'',4,72,13,5,4,135,53),(135,'2020-07-05 06:35:05',NULL,'',2,82,5,9,13,48,88),(136,'2020-06-29 03:54:25',NULL,'',2,93,2,5,9,11,43),(137,'2020-06-28 22:04:32',NULL,'\0',4,54,12,6,10,119,62),(138,'2020-07-18 18:16:40',NULL,'',4,77,14,17,7,164,208),(139,'2020-07-23 17:42:08',NULL,'',2,26,9,12,10,91,127),(140,'2020-07-02 02:06:13',NULL,'',3,35,24,10,2,261,96),(141,'2020-07-21 22:44:27',NULL,'\0',2,81,13,6,1,132,61),(142,'2020-07-01 13:08:50',NULL,'\0',3,94,5,2,5,42,14),(143,'2020-07-05 12:15:25',NULL,'',2,41,16,13,12,203,143),(144,'2020-07-07 03:17:55',NULL,'\0',1,47,2,16,13,12,197),(145,'2020-07-21 05:01:07',NULL,'\0',3,60,14,14,11,176,168),(146,'2020-08-09 12:35:51',NULL,'',4,44,23,13,8,255,153),(147,'2020-06-23 14:27:18',NULL,'',4,25,3,14,7,17,165),(148,'2020-08-12 09:11:29',NULL,'',3,57,11,17,8,107,206),(149,'2020-06-27 19:06:02',NULL,'',2,65,19,21,2,232,245),(150,'2020-06-26 06:03:04',NULL,'',3,70,13,3,7,137,18),(151,'2020-08-05 09:22:16',NULL,'',2,16,9,17,6,93,208),(152,'2020-07-02 13:58:41',NULL,'',4,70,15,14,1,177,172),(153,'2020-06-29 17:17:26',NULL,'',2,63,13,3,11,141,25),(154,'2020-07-14 06:23:44',NULL,'',4,42,14,8,7,176,81),(155,'2020-07-26 09:25:48',NULL,'',3,11,17,13,8,210,158),(156,'2020-07-01 23:02:11',NULL,'',3,17,20,12,8,240,131),(157,'2020-07-04 01:05:07',NULL,'',3,64,10,5,2,99,44),(158,'2020-06-23 07:28:35',NULL,'',2,47,4,19,1,33,232),(159,'2020-08-13 22:15:39',NULL,'',3,94,16,2,15,193,13),(160,'2020-08-11 09:06:11',NULL,'\0',3,66,13,13,11,150,161),(161,'2020-07-17 20:48:41',NULL,'\0',4,32,22,5,13,252,39),(162,'2020-08-08 20:53:33',NULL,'',4,44,14,18,13,173,215),(163,'2020-07-31 14:05:47',NULL,'\0',4,70,5,20,13,36,235),(164,'2020-07-25 02:26:48',NULL,'',2,15,18,12,4,218,126),(165,'2020-07-12 02:41:06',NULL,'',1,60,19,5,5,231,40);
/*!40000 ALTER TABLE `covoiturage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gouvernorat`
--

DROP TABLE IF EXISTS `gouvernorat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gouvernorat` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gouvernorat`
--

LOCK TABLES `gouvernorat` WRITE;
/*!40000 ALTER TABLE `gouvernorat` DISABLE KEYS */;
INSERT INTO `gouvernorat` (`id`, `name`) VALUES (1,'Tunis'),(2,'Ariana'),(3,'Ben Arous'),(4,'Manouba'),(5,'Nabeul'),(6,'Zaghouan'),(7,'Bizerte'),(8,'Béja'),(9,'Jendouba'),(10,'Kef'),(11,'Siliana'),(12,'Sousse'),(13,'Monastir'),(14,'Mahdia'),(15,'Sfax'),(16,'Kairouan'),(17,'Kasserine'),(18,'Sidi Bouzid'),(19,'Gabès'),(20,'Mednine'),(21,'Tataouine'),(22,'Gafsa'),(23,'Tozeur'),(24,'Kebili');
/*!40000 ALTER TABLE `gouvernorat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (166),(166),(166),(166),(166),(166);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` bigint(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `submission_date` datetime DEFAULT NULL,
  `covoiturage_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpb96xjdvajx5ufsafruv7j4wx` (`covoiturage_id`),
  KEY `FK5gtt7nuqm7acju2vhmihw4r9k` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `age` int(11) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `avis` double NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `has_url` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `inscription_date` datetime DEFAULT NULL,
  `last_date_enetered` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `localisation` varchar(255) DEFAULT NULL,
  `num_tel` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `age`, `authorities`, `avis`, `email`, `first_name`, `gender`, `has_url`, `image_url`, `inscription_date`, `last_date_enetered`, `last_name`, `localisation`, `num_tel`, `password`, `reset_token`, `roles`, `status`) VALUES (1,43,NULL,0,'Kyong@gmail.com','Kyong',1,'','api/user/images/getImage/1.jpg','2020-06-19 20:07:43','2020-06-19 20:07:43','Reinger',NULL,'1-694-138-7474','$2a$10$Bi6/f47L5ISuSeW.qkRjrOgqdUgI7mGCN.k1fio5RR0oq8XqF9ORG',NULL,NULL,'Offline'),(2,50,NULL,0,'Dorathy@gmail.com','Dorathy',1,'','api/user/images/getImage/2.jpg','2020-06-19 20:07:43','2020-06-19 20:07:43','Hilpert',NULL,'252.616.9376','$2a$10$/4uHkkmO7SkkcuBcEN8O/.9DcOqdtR/OevpYy797EznbmSE0TdbWS',NULL,NULL,'Online'),(3,43,NULL,0,'Christina@gmail.com','Christina',0,'','api/user/images/getImage/3.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Crona',NULL,'309-877-1700','$2a$10$jAOLmBnKp4jvbQ0ErcvFsuC6U3fKqZfw1y2hSS4pFQQKOODkHXErW',NULL,NULL,'Offline'),(4,42,NULL,0,'Michiko@gmail.com','Michiko',1,'','api/user/images/getImage/4.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Marvin',NULL,'550.982.5839','$2a$10$cEVCxHoROPGU6i.0nz08zOgEc/my5/K97o8fcP5OyvJqSLcd3P11i',NULL,NULL,'Offline'),(5,29,NULL,0,'Josie@gmail.com','Josie',0,'','api/user/images/getImage/5.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Murazik',NULL,'407.019.0788','$2a$10$VklQKVYG3gNC2OegR7g/kOdCsBCNUvCbsRRVla9DHS8lfNPAIm7NC',NULL,NULL,'Offline'),(6,21,NULL,0,'Forrest@gmail.com','Forrest',0,'','api/user/images/getImage/6.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Lemke',NULL,'(952) 565-4419','$2a$10$Rxj6HLaH4Z3zJZTx24BtV.CVZnMxdPRXlkx5FMtDxz8wovQ7wAi6a',NULL,NULL,'Offline'),(7,54,NULL,0,'Shaquita@gmail.com','Shaquita',0,'','api/user/images/getImage/7.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Nienow',NULL,'391.960.6390','$2a$10$zsNU7jy1GNvMBQTJqTH3G.AwSILKBjJk.444sINgd4f56fKDUukPq',NULL,NULL,'Offline'),(8,38,NULL,0,'Mignon@gmail.com','Mignon',0,'','api/user/images/getImage/8.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Langosh',NULL,'1-859-093-5158','$2a$10$xbLo9bGIRH.GZ3dQCfJTf.wFtgu.R0FOZn/fV8HlpzzCNv/1zv8nS',NULL,NULL,'Online'),(9,53,NULL,0,'Quinton@gmail.com','Quinton',0,'','api/user/images/getImage/9.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Langworth',NULL,'262.784.3936','$2a$10$gJgiZdozqF6gwL94SrRDM.DlNi2dVMenzTXUcWQvfislO0rk.9iQS',NULL,NULL,'Offline'),(10,24,NULL,0,'Chantal@gmail.com','Chantal',1,'','api/user/images/getImage/10.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Balistreri',NULL,'1-500-380-1009','$2a$10$/ivahK/pW3Qhksc/AFKLoe2EhdXc9e0mCbRZMd3XVn6SpWfVEg4n2',NULL,NULL,'Online'),(11,41,NULL,0,'Breanna@gmail.com','Breanna',1,'','api/user/images/getImage/11.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Crist',NULL,'530-547-3149','$2a$10$05mB1eDGoOi2KIcC.Grey.IhPoeOtBiHwaspVM9fTJCF4X6fUFD7C',NULL,NULL,'Offline'),(12,36,NULL,0,'Wilburn@gmail.com','Wilburn',1,'','api/user/images/getImage/12.jpg','2020-06-19 20:07:44','2020-06-19 20:07:44','Lehner',NULL,'1-244-797-8039','$2a$10$2.FyDJXbw.a2YsX7BoWDw.1X7zyn.VeUnmDuhWNn3gDg/FyPntMl2',NULL,NULL,'Online'),(13,25,NULL,0,'Tory@gmail.com','Tory',1,'','api/user/images/getImage/13.jpg','2020-06-19 20:07:45','2020-06-19 20:07:45','Roob',NULL,'(918) 748-8806','$2a$10$mZ2b7Be6OgxhCLfZbKga7.cb1Fl5Z1QQWR6LE06jyrJcVwiqdNoHG',NULL,NULL,'Offline'),(14,35,NULL,0,'Williams@gmail.com','Williams',1,'','api/user/images/getImage/14.jpg','2020-06-19 20:07:45','2020-06-19 20:07:45','McClure',NULL,'1-957-541-5426','$2a$10$aa7JSIWsRyeRsEOJyEd0kugBG6mQL.iwR1FgTiBNe/8ic/LlDxjtC',NULL,NULL,'Online'),(15,38,NULL,0,'Britt@gmail.com','Britt',1,'','api/user/images/getImage/15.jpg','2020-06-19 20:07:45','2020-06-19 20:07:45','Fahey',NULL,'549.396.0343','$2a$10$6IJubo9BJB79JqnJoDfss.5ehd6T7N.5Lu0IX3tnx2orCcyHKPfEG',NULL,NULL,'Offline');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ville`
--

DROP TABLE IF EXISTS `ville`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ville` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gouv_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlccd08j1u9y10p8ekofwevbrw` (`gouv_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ville`
--

LOCK TABLES `ville` WRITE;
/*!40000 ALTER TABLE `ville` DISABLE KEYS */;
INSERT INTO `ville` (`id`, `name`, `gouv_id`) VALUES (1,'Tunis',1),(2,'Bardo',1),(3,'Kram',1),(4,'Goulette',1),(5,'Carthage',1),(6,'Sidi Bou Said',1),(7,'Marsa',1),(8,'Sidi Hassine',1),(9,'Ariana',2),(10,'Soukra',2),(11,'Raoued',2),(12,'Kalâat el-Andalous',2),(13,'Sidi Thabet',2),(14,'Ettadhamen-Mnihla',2),(15,'Ben Arous',3),(16,'El Mourouj',3),(17,'Hammam Lif',3),(18,'Hammam Chott',3),(19,'Bou Mhel el-Bassatine',3),(20,'Ezzahra',3),(21,'Radès',3),(22,'Mégrine',3),(23,'Mohamedia-Fouchana',3),(24,'Mornag',3),(25,'Khalidia',3),(26,'Manouba',4),(27,'Den Den',4),(28,'Douar Hicher',4),(29,'Oued Ellil',4),(30,'Mornaguia',4),(31,'Borj El Amri',4),(32,'Djedeida',4),(33,'Tebourba',4),(34,'El Battan',4),(35,'Nabeul',5),(36,'Dar Chaabane',5),(37,'Béni Khiar',5),(38,'El Maâmoura',5),(39,'Somâa',5),(40,'Korba',5),(41,'Tazerka',5),(42,'Menzel Temime',5),(43,'Menzel Horr',5),(44,'El Mida',5),(45,'Kelibia',5),(46,'Azmour',5),(47,'Hammam Ghezèze',5),(48,'Dar Allouch',5),(49,'El Haouaria',5),(50,'Takelsa',5),(51,'Soliman',5),(52,'Korbous',5),(53,'Menzel Bouzelfa',5),(54,'Béni Khalled',5),(55,'Zaouiet Djedidi',5),(56,'Grombalia',5),(57,'Bou Argoub',5),(58,'Hammamet',5),(59,'Zaghouan',6),(60,'Zriba',6),(61,'Bir Mcherga',6),(62,'Djebel Oust',6),(63,'El Fahs',6),(64,'Nadhour',6),(65,'Bizerte',7),(66,'Sejnane',7),(67,'Mateur',7),(68,'Menzel Bourguiba',7),(69,'Tinja',7),(70,'Ghar al Milh',7),(71,'Aousja',7),(72,'Menzel Jemil',7),(73,'Menzel Abderrahmane',7),(74,'El Alia',7),(75,'Ras Jebel',7),(76,'Metline',7),(77,'Raf Raf',7),(78,'Béja',8),(79,'El Maâgoula',8),(80,'Zahret Medien',8),(81,'Nefza',8),(82,'Téboursouk',8),(83,'Testour',8),(84,'Goubellat',8),(85,'Majaz al Bab',8),(86,'Jendouba',9),(87,'Bou Salem',9),(88,'Tabarka',9),(89,'Aïn Draham',9),(90,'Fernana',9),(91,'Beni M\'Tir',9),(92,'Ghardimaou',9),(93,'Oued Melliz',9),(94,'El Kef',10),(95,'Nebeur',10),(96,'Touiref',10),(97,'Sakiet Sidi Youssef',10),(98,'Tajerouine',10),(99,'Menzel Salem',10),(100,'Kalaat es Senam',10),(101,'Kalâat Khasba',10),(102,'Jérissa',10),(103,'El Ksour',10),(104,'Dahmani',10),(105,'Sers',10),(106,'Siliana',11),(107,'Bou Arada',11),(108,'Gaâfour',11),(109,'El Krib',11),(110,'Sidi Bou Rouis',11),(111,'Maktar',11),(112,'Rouhia',11),(113,'Kesra',11),(114,'Bargou',11),(115,'El Aroussa',11),(116,'Sousse',12),(117,'Ksibet Thrayet',12),(118,'Ezzouhour',12),(119,'Zaouiet Sousse',12),(120,'Hammam Sousse',12),(121,'Akouda',12),(122,'Kalâa Kebira',12),(123,'Sidi Bou Ali',12),(124,'Hergla',12),(125,'Enfidha',12),(126,'Bouficha',12),(127,'Sidi El Hani',12),(128,'M\'saken',12),(129,'Kalâa Seghira',12),(130,'Messaadine',12),(131,'Kondar',12),(132,'Monastir',13),(133,'Khniss',13),(134,'Ouerdanin',13),(135,'Sahline Moôtmar',13),(136,'Sidi Ameur',13),(137,'Zéramdine',13),(138,'Beni Hassen',13),(139,'Ghenada',13),(140,'Jemmal',13),(141,'Menzel Kamel',13),(142,'Zaouiet Kontoch',13),(143,'Bembla-Mnara',13),(144,'Menzel Ennour',13),(145,'El Masdour',13),(146,'Moknine',13),(147,'Sidi Bennour',13),(148,'Menzel Farsi',13),(149,'Amiret El Fhoul',13),(150,'Amiret Touazra',13),(151,'Amiret El Hojjaj',13),(152,'Cherahil',13),(153,'Bekalta',13),(154,'Téboulba',13),(155,'Ksar Hellal',13),(156,'Ksibet El Mediouni',13),(157,'Benen Bodher',13),(158,'Touza',13),(159,'Sayada',13),(160,'Lemta',13),(161,'Bouhjar',13),(162,'Menzel Hayet',13),(163,'Mahdia',14),(164,'Rejiche',14),(165,'Bou Merdes',14),(166,'Ouled Chamekh',14),(167,'Chorbane',14),(168,'Hebira',14),(169,'Essouassi',14),(170,'El Djem',14),(171,'Kerker',14),(172,'Chebba',14),(173,'Melloulèche',14),(174,'Sidi Alouane',14),(175,'Ksour Essef',14),(176,'El Bradâa',14),(177,'Sfax',15),(178,'Sakiet Ezzit',15),(179,'Chihia',15),(180,'Sakiet Eddaïer',15),(181,'Gremda',15),(182,'El Ain',15),(183,'Thyna',15),(184,'Agareb',15),(185,'Jebiniana',15),(186,'El Hencha',15),(187,'Menzel Chaker',15),(188,'Ghraïba',15),(189,'Bir Ali Ben Khélifa',15),(190,'Skhira',15),(191,'Mahares',15),(192,'Kerkennah',15),(193,'Kairouan',16),(194,'Chebika, Kairouan',16),(195,'Sbikha',16),(196,'Oueslatia',16),(197,'Aïn Djeloula',16),(198,'Haffouz',16),(199,'Alaâ',16),(200,'Hajeb El Ayoun',16),(201,'Nasrallah',16),(202,'Menzel Mehiri',16),(203,'Echrarda',16),(204,'Bou Hajla',16),(205,'Kasserine',17),(206,'Sbeitla',17),(207,'Sbiba',17),(208,'Jedelienne',17),(209,'Thala',17),(210,'Haïdra',17),(211,'Foussana',17),(212,'Fériana',17),(213,'Thélepte',17),(214,'Magel Bel Abbès',17),(215,'Sidi Bouzid',18),(216,'Jilma',18),(217,'Cebalet',18),(218,'Bir El Hafey',18),(219,'Sidi Ali Ben Aoun',18),(220,'Menzel Bouzaiane',18),(221,'Meknassy',18),(222,'Mezzouna',18),(223,'Regueb',18),(224,'Ouled Haffouz',18),(225,'Gabès',19),(226,'Chenini Nahal',19),(227,'Ghannouch',19),(228,'Métouia',19),(229,'Oudhref',19),(230,'El Hamma',19),(231,'Matmata',19),(232,'Nouvelle Matmata',19),(233,'Mareth',19),(234,'Zarat',19),(235,'Medenine',20),(236,'Beni Khedache',20),(237,'Ben Gardane',20),(238,'Zarzis',20),(239,'Houmt El Souk',20),(240,'Midoun',20),(241,'Ajim',20),(242,'Tataouine',21),(243,'Bir Lahmar',21),(244,'Ghomrassen',21),(245,'Dehiba',21),(246,'Remada',21),(247,'Gafsa',22),(248,'El Ksar',22),(249,'Moularès',22),(250,'Redeyef',22),(251,'Métlaoui',22),(252,'Mdhila',22),(253,'El Guettar',22),(254,'Sened',22),(255,'Tozeur',23),(256,'Degache',23),(257,'Hamet Jerid',23),(258,'Nafta',23),(259,'Tamerza',23),(260,'Kebili',24),(261,'Djemna',24),(262,'Douz',24),(263,'El Golâa',24),(264,'Souk Lahad',24);
/*!40000 ALTER TABLE `ville` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 21:12:58
