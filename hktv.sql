-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: hktvTest
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `hktv_location`
--

DROP TABLE IF EXISTS `hktv_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hktv_location` (
  `location_name` varchar(100) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`location_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hktv_location`
--

LOCK TABLES `hktv_location` WRITE;
/*!40000 ALTER TABLE `hktv_location` DISABLE KEYS */;
INSERT INTO `hktv_location` VALUES ('CWB',22),('MK',21),('TKO',20);
/*!40000 ALTER TABLE `hktv_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hktv_product`
--

DROP TABLE IF EXISTS `hktv_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hktv_product` (
  `product_code` varchar(100) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_weight` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hktv_product`
--

LOCK TABLES `hktv_product` WRITE;
/*!40000 ALTER TABLE `hktv_product` DISABLE KEYS */;
INSERT INTO `hktv_product` VALUES (' MK-20','Ironman',6,13),('EXVS-001','Gundam Extreme VS',5,12),('FM-HKTV01','Face mask',100,11),('Gov-600','777',200,16),('HKTV-200','Wiki Wong',50,15),('IP-02','Iphone',30,17),('Mac-1000','MacBook',790,19),('MK-1','Ironman',10,14),('Samsung-600','Samsung Galaxy',500,18);
/*!40000 ALTER TABLE `hktv_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hktv_storage`
--

DROP TABLE IF EXISTS `hktv_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hktv_storage` (
  `product_code` varchar(100) NOT NULL,
  `product_qty` int(11) NOT NULL,
  `location_name` varchar(100) NOT NULL,
  `storage_id` int(11) NOT NULL,
  KEY `product_code` (`product_code`),
  KEY `location_name` (`location_name`),
  CONSTRAINT `hktv_storage_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `hktv_product` (`product_code`),
  CONSTRAINT `hktv_storage_ibfk_2` FOREIGN KEY (`location_name`) REFERENCES `hktv_location` (`location_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hktv_storage`
--

LOCK TABLES `hktv_storage` WRITE;
/*!40000 ALTER TABLE `hktv_storage` DISABLE KEYS */;
INSERT INTO `hktv_storage` VALUES ('FM-HKTV01',8,'TKO',23),('EXVS-001',80,'CWB',24),('HKTV-200',4,'TKO',25),('Mac-1000',32,'CWB',26),('Mac-1000',80,'MK',27),('MK-1',5,'CWB',28),('IP-02',400,'TKO',29),('IP-02',700,'CWB',30),('IP-02',200,'MK',31);
/*!40000 ALTER TABLE `hktv_storage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-20 23:17:47
