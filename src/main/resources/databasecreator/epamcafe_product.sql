-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: epamcafe
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный индификационный номер(Синтетический ключ) Продукта, использующийся для связи таблицы Меню с другими таблицами.',
  `productType` varchar(45) NOT NULL COMMENT 'Тип продука. Например, напиток, пицца...',
  `productNameRu` varchar(45) NOT NULL COMMENT 'Название блюда.',
  `productWeight` int(11) NOT NULL COMMENT 'Вес продукта.',
  `productCost` double NOT NULL COMMENT 'Стоимость Продукта.',
  `productStatus` varchar(45) NOT NULL COMMENT 'Наличие данного продукта в данный момент.',
  `productDescriptionRu` text COMMENT 'Описание продукта. Состав.',
  `productImage` tinytext COMMENT 'Путь для подгрузки изображения продукта.',
  `productNameEn` varchar(45) NOT NULL,
  `productDescriptionEn` text,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `menuId_UNIQUE` (`productId`),
  UNIQUE KEY `dishName_UNIQUE` (`productNameRu`),
  UNIQUE KEY `product_prioductNameEn_uindex` (`productNameEn`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Таблица Меню содержит информацию о доступных продектах, блюдах для заказа Пользователем.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,'напиток','Кока-Кола',250,2.5,'there',NULL,'cola.jpg','Coca-cola',NULL),(10,'суп','Борщ',250,1.4,'there',NULL,'borsch.jpg','Borsch',NULL),(11,'салат','Греческий',200,2.4,'there',NULL,'greece.jpg','Greece',NULL),(12,'салат','Мимоза',220,1.3,'there',NULL,'mimosa.jpg','Mimosa',NULL),(13,'горячее блюдо','Котлета по-киевски',120,2,'there',NULL,'kiev.jpg','Tre Kiev`s cutlet',NULL),(14,'гарнир','Рис',200,1.2,'there',NULL,'rice.jpg','Rice',NULL),(15,'гарнир','Картошка',200,1,'there',NULL,'bulba.jpg','Potato',NULL),(16,'напиток','Яблочный компот',250,1.4,'there',NULL,'apple.jpg','Apple compot',NULL),(17,'выпечка','Смаженка',100,5.4,'there','','smajenka.jpg','Smajenka',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-04 13:12:03
