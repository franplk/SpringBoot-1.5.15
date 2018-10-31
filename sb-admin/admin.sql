-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: db_study
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_package`
--

DROP TABLE IF EXISTS `app_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_type` smallint(6) DEFAULT NULL,
  `file_md5` varchar(255) DEFAULT NULL,
  `linkurl` varchar(255) DEFAULT NULL,
  `pack_name` varchar(255) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_package`
--

LOCK TABLES `app_package` WRITE;
/*!40000 ALTER TABLE `app_package` DISABLE KEYS */;
INSERT INTO `app_package` VALUES (1,0,'126fa89fc2a30e7449c18b21bfb5883c','https://d.anbangjr.com/dfs/group1/M00/00/04/CgQEeFvGrNKEe8ipAAAAANDFlZE356.jpg','测试图片',0,NULL,'2018-10-17 11:34:15','3.1.1'),(2,1,'','','IOS安装包',0,NULL,'2018-10-17 11:46:51','3.2.1'),(3,0,'4623aeec5d0513a8064f4addc0b30e86','https://d.anbangjr.com/dfs/group1/M00/00/05/CgQEeFvG4uOEOSz4AAAAAAL9l0M97.docx','Word文档',0,NULL,'2018-10-17 15:24:27','3.9');
/*!40000 ALTER TABLE `app_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chunk_file`
--

DROP TABLE IF EXISTS `chunk_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chunk_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chunk_num` int(11) DEFAULT NULL,
  `file_md5` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chunk_file`
--

LOCK TABLES `chunk_file` WRITE;
/*!40000 ALTER TABLE `chunk_file` DISABLE KEYS */;
INSERT INTO `chunk_file` VALUES (1,0,'126fa89fc2a30e7449c18b21bfb5883c','M00/00/04/CgQEeFvGrNKEe8ipAAAAANDFlZE356.jpg',64174,'https://d.anbangjr.com/dfs/group1/M00/00/04/CgQEeFvGrNKEe8ipAAAAANDFlZE356.jpg',0),(2,1,'082097d3a0b3ef219ed0936071b30f2f','M00/00/06/CgQEd1vGsJKEE7L-AAAAAKfHGH8965.jpg',507157,'https://d.anbangjr.com/dfs/group1/M00/00/06/CgQEd1vGsJKEE7L-AAAAAKfHGH8965.jpg',1),(3,4,'4623aeec5d0513a8064f4addc0b30e86','M00/00/05/CgQEeFvG4uOEOSz4AAAAAAL9l0M97.docx',861867,'https://d.anbangjr.com/dfs/group1/M00/00/05/CgQEeFvG4uOEOSz4AAAAAAL9l0M97.docx',0);
/*!40000 ALTER TABLE `chunk_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_model`
--

DROP TABLE IF EXISTS `file_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_md5` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_model`
--

LOCK TABLES `file_model` WRITE;
/*!40000 ALTER TABLE `file_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `icon` varchar(16) DEFAULT NULL,
  `desc_info` varchar(128) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_item`
--

LOCK TABLES `menu_item` WRITE;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` VALUES (1,'任务调度',0,'',NULL,NULL,'定时任务管理','2018-10-15 16:32:53'),(2,'任务列表',1,'quartz','2018-09-26 17:20:30',NULL,'任务列表',NULL),(3,'系统管理',0,'','2018-09-26 17:30:30',NULL,'系统管理',NULL),(4,'菜单管理',3,'menu','2018-09-26 18:30:30',NULL,'菜单管理',NULL),(5,'用户管理',3,'user','2018-09-27 17:55:57',NULL,'管理用户',NULL),(6,'个人设置',0,'','2018-10-12 09:28:38',NULL,'个人设置',NULL),(7,'更新密码',6,'account/pass/update','2018-10-12 09:32:12',NULL,'账号密码修改','2018-10-15 16:45:54'),(8,'资料更新',6,'account/info/update','2018-10-15 15:56:05',NULL,'用户个人信息维护','2018-10-15 16:45:21'),(10,'文件管理',0,'','2018-10-16 08:37:59',NULL,'文件上传管理','2018-10-16 08:37:59'),(11,'APP安装包上传',10,'app/package','2018-10-16 08:40:43',NULL,'APP安装包上传管理','2018-10-16 08:40:43');
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_desc` varchar(255) DEFAULT NULL,
  `permission_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l3pmqryh8vgle52647itattb9` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_role_permission`
--

DROP TABLE IF EXISTS `r_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK304lf7gbiu0pojuba9a13ftvd` (`permission_id`),
  KEY `FKoasvh6iy0312i275g673b2lqs` (`role_id`),
  CONSTRAINT `FK304lf7gbiu0pojuba9a13ftvd` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKoasvh6iy0312i275g673b2lqs` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_role_permission`
--

LOCK TABLES `r_role_permission` WRITE;
/*!40000 ALTER TABLE `r_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_user_role`
--

DROP TABLE IF EXISTS `r_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKqtburc3pvgt0jbgwm07etp7jk` (`role_id`),
  KEY `FK30tuws4ya5t2iqs5namqc3jvm` (`user_id`),
  CONSTRAINT `FK30tuws4ya5t2iqs5namqc3jvm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqtburc3pvgt0jbgwm07etp7jk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_user_role`
--

LOCK TABLES `r_user_role` WRITE;
/*!40000 ALTER TABLE `r_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) DEFAULT NULL,
  `role_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permissionid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `regtime` datetime NOT NULL,
  `username` varchar(32) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'franplk@126.com','franplp','123456','2018-09-17 12:17:20','franplk',0),(9,'franplk@126.com','franplk','7cD8ya37','2018-10-11 17:08:05','admin',1),(16,'franplk@126.com','feibby','p1936dAK','2018-10-11 17:45:57','feibby',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-17 16:45:33
