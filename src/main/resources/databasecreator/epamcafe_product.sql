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
  `productId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Уникальный индификационный номер(Синтетический ключ) Продукта, использующийся для связи таблицы Меню с другими таблицами.',
  `productType` varchar(45) NOT NULL COMMENT 'Тип продука. Например, напиток, пицца...',
  `productNameRu` varchar(45) NOT NULL COMMENT 'Название блюда.',
  `productWeight` int(11) NOT NULL COMMENT 'Вес продукта.',
  `productCost` decimal(10,2) NOT NULL COMMENT 'Стоимость Продукта.',
  `productStatus` varchar(45) NOT NULL COMMENT 'Наличие данного продукта в данный момент.',
  `productDescriptionRu` text COMMENT 'Описание продукта. Состав.',
  `productImage` tinytext NOT NULL COMMENT 'Путь для подгрузки изображения продукта.',
  `productNameEn` varchar(45) NOT NULL,
  `productDescriptionEn` text,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `menuId_UNIQUE` (`productId`),
  UNIQUE KEY `product_prioductNameEn_uindex` (`productNameEn`),
  UNIQUE KEY `productNameRu_UNIQUE` (`productNameRu`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='Таблица Меню содержит информацию о доступных продектах, блюдах для заказа Пользователем.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,'lemonade','Кока-Кола',500,1.05,'there',NULL,'cola.jpg','Coca-cola',NULL),(10,'soup','Борщ',250,1.40,'there',NULL,'borsch.jpg','Borsch',NULL),(11,'salad','Греческий',200,1.30,'there',NULL,'greece.jpg','Greece',NULL),(12,'salad','Мимоза',220,1.60,'there',NULL,'mimosa.jpg','Mimosa',NULL),(13,'hotDish','Котлета по-киевски',120,1.85,'there',NULL,'kiev.jpg','Tre Kiev`s cutlet',NULL),(14,'garnish','Рис',200,1.20,'there',NULL,'rice.jpg','Rice',NULL),(15,'garnish','Картошка',200,1.00,'there',NULL,'bulba.jpg','Potato',NULL),(16,'juice','Яблочный компот',400,0.80,'there',NULL,'apple.jpg','Apple compot',NULL),(17,'bakeryProduct','Смаженка',150,1.10,'there','','smajenka.jpg','Smajenka',NULL),(18,'soup','Гороховый суп',350,1.20,'true','Очень вкусный и сытный суп','pea soup.jpg','Pea soup','Very well'),(19,'hotDish','Стейк',500,18.00,'true','','steak.jpg','Steak',''),(20,'bakeryProduct','Пирог',450,7.40,'true','','pie.jpg','Pie',''),(21,'dessert','Мороженое',150,3.20,'true','','ice-scream.jpg','Ice-scream',''),(22,'dessert','Тирамису',100,2.60,'true','','tiramisu.jpg','Tiramisu',''),(23,'juice','Томатный сок',300,0.85,'true','','tomato juice.jpg','Tomato juice',''),(24,'water','Вода',400,0.50,'true','','water.jpg','Water',''),(25,'hotDrink','Чай',350,1.40,'true','','tea.jpg','Tea',''),(26,'hotDrink','Кофе',250,3.50,'true','','coffe.jpg','Coffe',''),(27,'lemonade','Фанта',250,1.20,'true','','fanta.jpg','Fanta','');
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

-- Dump completed on 2018-02-11  0:35:00
