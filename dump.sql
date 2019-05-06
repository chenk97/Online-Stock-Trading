-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: mysql4.cs.stonybrook.edu    Database: jiarchen
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Account` (
  `AccountId` bigint(20) NOT NULL AUTO_INCREMENT,
  `DateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ClientId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`AccountId`),
  KEY `Account_Client__fk` (`ClientId`),
  CONSTRAINT `Account_Client__fk` FOREIGN KEY (`ClientId`) REFERENCES `Client` (`ClientId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` (`AccountId`, `DateCreated`, `ClientId`) VALUES (1,'2006-10-15 04:00:00',1);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Client` (
  `ClientId` bigint(20) NOT NULL AUTO_INCREMENT,
  `SSN` char(20) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `CreditCardNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ClientId`),
  KEY `Client_Person__fk` (`SSN`),
  CONSTRAINT `Client_Person__fk` FOREIGN KEY (`SSN`) REFERENCES `Person` (`SSN`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` (`ClientId`, `SSN`, `Rating`, `CreditCardNumber`) VALUES (1,'123459876',5,2222222222222222);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConditionalOrderHistory`
--

DROP TABLE IF EXISTS `ConditionalOrderHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ConditionalOrderHistory` (
  `HisId` bigint(20) NOT NULL AUTO_INCREMENT,
  `OrderId` bigint(20) DEFAULT NULL,
  `StockSymbol` char(20) DEFAULT NULL,
  `PricePerShare` decimal(15,2) DEFAULT NULL,
  `DateNTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`HisId`),
  KEY `ConditionalOrderHistory_Orders__fk` (`OrderId`),
  CONSTRAINT `ConditionalOrderHistory_Orders__fk` FOREIGN KEY (`OrderId`) REFERENCES `Orders` (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConditionalOrderHistory`
--

LOCK TABLES `ConditionalOrderHistory` WRITE;
/*!40000 ALTER TABLE `ConditionalOrderHistory` DISABLE KEYS */;
INSERT INTO `ConditionalOrderHistory` (`HisId`, `OrderId`, `StockSymbol`, `PricePerShare`, `DateNTime`) VALUES (1,5,'BMI',15.00,'2019-05-05 19:40:56'),(2,6,'BMI',15.00,'2019-05-05 19:40:56'),(3,7,'BMI',15.00,'2019-05-05 19:55:33'),(4,10,'BMI',15.00,'2019-05-05 20:13:40'),(5,11,'BMI',15.00,'2019-05-05 20:14:00'),(6,12,'BMI',15.00,'2019-05-05 20:34:31');
/*!40000 ALTER TABLE `ConditionalOrderHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Employee` (
  `EmpId` char(20) NOT NULL,
  `StartDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `HourlyRate` double DEFAULT NULL,
  `Level` char(50) DEFAULT NULL,
  PRIMARY KEY (`EmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` (`EmpId`, `StartDate`, `HourlyRate`, `Level`) VALUES ('111111111','2019-05-04 19:10:49',30,'Customer Representative'),('123456789','2019-05-04 21:18:10',59,'Manager');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HiddenStop`
--

DROP TABLE IF EXISTS `HiddenStop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `HiddenStop` (
  `StopId` bigint(20) NOT NULL AUTO_INCREMENT,
  `OrderId` bigint(20) DEFAULT NULL,
  `StopPrice` double DEFAULT NULL,
  PRIMARY KEY (`StopId`),
  KEY `OrderId` (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HiddenStop`
--

LOCK TABLES `HiddenStop` WRITE;
/*!40000 ALTER TABLE `HiddenStop` DISABLE KEYS */;
INSERT INTO `HiddenStop` (`StopId`, `OrderId`, `StopPrice`) VALUES (1,6,1),(2,11,2);
/*!40000 ALTER TABLE `HiddenStop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Location` (
  `Zipcode` int(11) NOT NULL,
  `City` char(20) NOT NULL,
  `State` char(20) NOT NULL,
  `LocationId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`LocationId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` (`Zipcode`, `City`, `State`, `LocationId`) VALUES (11727,'Coram','NY',1),(11790,'STONY BROOK','NY',2),(11780,'Cold Spring Harbor','NY',3);
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Orders` (
  `OrderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `NumShares` int(11) DEFAULT NULL,
  `PricePerShare` decimal(15,2) DEFAULT NULL,
  `DateNTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OrderType` char(5) DEFAULT NULL,
  `PriceType` char(20) DEFAULT NULL,
  `StockSymbol` char(20) DEFAULT NULL,
  PRIMARY KEY (`OrderId`),
  KEY `fk_cs` (`StockSymbol`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`StockSymbol`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` (`OrderId`, `NumShares`, `PricePerShare`, `DateNTime`, `OrderType`, `PriceType`, `StockSymbol`) VALUES (1,10,20.00,'2019-05-05 18:52:37','Buy','Market','BMI'),(2,10,20.00,'2019-05-05 18:53:15','Buy','MarketOnClose','BMI'),(3,100,20.00,'2019-05-05 19:16:48','Buy','Market','BMI'),(4,50,20.00,'2019-05-05 19:39:32','Buy','Market','GM'),(5,10,15.00,'2019-05-05 19:40:56','Sell','TrailingStop','BMI'),(6,10,15.00,'2019-05-05 19:55:33','Sell','HiddenStop','BMI'),(7,10,15.00,'2019-05-05 19:56:58','Sell','TrailingStop','BMI'),(8,11,15.00,'2019-05-05 20:04:58','Sell','Market','BMI'),(9,3,15.00,'2019-05-05 20:13:17','Sell','MarketOnClose','BMI'),(10,2,20.00,'2019-05-05 20:13:40','Sell','TrailingStop','GM'),(11,4,15.00,'2019-05-05 20:14:00','Sell','HiddenStop','BMI'),(12,10,15.00,'2019-05-05 20:34:31','Sell','TrailingStop','BMI');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Person` (
  `SSN` char(20) NOT NULL,
  `LastName` char(20) NOT NULL,
  `FirstName` char(20) NOT NULL,
  `Zipcode` int(11) DEFAULT NULL,
  `Telephone` bigint(20) DEFAULT NULL,
  `Address` char(100) DEFAULT NULL,
  `Email` char(30) DEFAULT NULL,
  `LocationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `SSN` (`SSN`),
  KEY `Person_Location__fk` (`Zipcode`),
  KEY `Person_Location__fk1` (`LocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` (`SSN`, `LastName`, `FirstName`, `Zipcode`, `Telephone`, `Address`, `Email`, `LocationId`) VALUES ('111111111','customer','representative',11111,1234567890,'123 hello rd','employee@gmail.com',1),('123456789','CHEN','cc',11790,987654321,'600 Health Science Drive','chen@g.com',2),('123459876','Chen','Karin',11780,123456789,'807 Avalon Pines Dr.','jiar@gmail.com',3);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Portfolio`
--

DROP TABLE IF EXISTS `Portfolio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Portfolio` (
  `PortfolioId` bigint(20) NOT NULL AUTO_INCREMENT,
  `AccountId` bigint(20) DEFAULT NULL,
  `StockSymbol` char(20) DEFAULT NULL,
  `NumShares` int(11) DEFAULT NULL,
  PRIMARY KEY (`PortfolioId`),
  KEY `StockSymbol` (`StockSymbol`),
  KEY `Portfolio_Account__fk` (`AccountId`),
  CONSTRAINT `Portfolio_Account__fk` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`AccountId`),
  CONSTRAINT `portfolio_ibfk_3` FOREIGN KEY (`StockSymbol`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Portfolio`
--

LOCK TABLES `Portfolio` WRITE;
/*!40000 ALTER TABLE `Portfolio` DISABLE KEYS */;
INSERT INTO `Portfolio` (`PortfolioId`, `AccountId`, `StockSymbol`, `NumShares`) VALUES (1,1,'BMI',10),(2,1,'BMI',10),(3,1,'BMI',100),(4,1,'GM',50),(5,1,'BMI',-10),(6,1,'BMI',-10),(7,1,'BMI',-10),(8,1,'BMI',-11),(9,1,'BMI',-3),(10,1,'GM',-2),(11,1,'BMI',-4),(12,1,'BMI',-10);
/*!40000 ALTER TABLE `Portfolio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stock`
--

DROP TABLE IF EXISTS `Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Stock` (
  `StockSymbol` char(20) NOT NULL,
  `CompanyName` char(20) NOT NULL,
  `Type` char(20) NOT NULL,
  `PricePerShare` decimal(15,2) DEFAULT NULL,
  `NumShares` int(11) DEFAULT NULL,
  PRIMARY KEY (`StockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stock`
--

LOCK TABLES `Stock` WRITE;
/*!40000 ALTER TABLE `Stock` DISABLE KEYS */;
INSERT INTO `Stock` (`StockSymbol`, `CompanyName`, `Type`, `PricePerShare`, `NumShares`) VALUES ('BMI','BMI Foundation','Computer',15.00,999999958),('GM','GM Company','Automobile',20.00,999999952);
/*!40000 ALTER TABLE `Stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockPriceHistory`
--

DROP TABLE IF EXISTS `StockPriceHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `StockPriceHistory` (
  `HisId` bigint(20) NOT NULL AUTO_INCREMENT,
  `StockSymbol` char(20) DEFAULT NULL,
  `PricePerShare` decimal(15,2) DEFAULT NULL,
  `DateNTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`HisId`),
  KEY `StockSymbol` (`StockSymbol`),
  CONSTRAINT `stockpricehistory_ibfk_1` FOREIGN KEY (`StockSymbol`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockPriceHistory`
--

LOCK TABLES `StockPriceHistory` WRITE;
/*!40000 ALTER TABLE `StockPriceHistory` DISABLE KEYS */;
INSERT INTO `StockPriceHistory` (`HisId`, `StockSymbol`, `PricePerShare`, `DateNTime`) VALUES (1,'BMI',15.00,'2019-05-05 19:36:39'),(2,'GM',20.00,'2019-05-05 19:36:51');
/*!40000 ALTER TABLE `StockPriceHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trade`
--

DROP TABLE IF EXISTS `Trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Trade` (
  `TradeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `AccountId` bigint(20) DEFAULT NULL,
  `BrokerId` char(20) DEFAULT NULL,
  `OrderId` bigint(20) DEFAULT NULL,
  `TransactionId` bigint(20) DEFAULT NULL,
  `StockSymbol` char(20) DEFAULT NULL,
  PRIMARY KEY (`TradeId`),
  KEY `TransactionId` (`TransactionId`),
  KEY `StockSymbol` (`StockSymbol`),
  KEY `Trade_Orders__fk` (`OrderId`),
  KEY `Trade_Account__fk` (`AccountId`),
  KEY `Trade_Employee__fk` (`BrokerId`),
  CONSTRAINT `Trade_Account__fk` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`AccountId`),
  CONSTRAINT `Trade_Employee__fk` FOREIGN KEY (`BrokerId`) REFERENCES `Employee` (`EmpId`),
  CONSTRAINT `trade_ibfk_5` FOREIGN KEY (`StockSymbol`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trade`
--

LOCK TABLES `Trade` WRITE;
/*!40000 ALTER TABLE `Trade` DISABLE KEYS */;
INSERT INTO `Trade` (`TradeId`, `AccountId`, `BrokerId`, `OrderId`, `TransactionId`, `StockSymbol`) VALUES (1,1,NULL,1,1,'BMI'),(2,1,NULL,2,2,'BMI'),(3,1,'111111111',3,3,'BMI'),(4,1,'111111111',4,4,'GM'),(5,1,'111111111',5,5,'BMI'),(6,1,'111111111',6,6,'BMI'),(7,1,'111111111',7,7,'BMI'),(8,1,'111111111',8,8,'BMI'),(9,1,'111111111',9,9,'BMI'),(10,1,'111111111',10,10,'GM'),(11,1,'111111111',11,11,'BMI'),(12,1,'111111111',12,12,'BMI');
/*!40000 ALTER TABLE `Trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrailingStop`
--

DROP TABLE IF EXISTS `TrailingStop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `TrailingStop` (
  `StopId` bigint(20) NOT NULL AUTO_INCREMENT,
  `OrderId` bigint(20) DEFAULT NULL,
  `Percentage` double DEFAULT NULL,
  PRIMARY KEY (`StopId`),
  KEY `OrderId` (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrailingStop`
--

LOCK TABLES `TrailingStop` WRITE;
/*!40000 ALTER TABLE `TrailingStop` DISABLE KEYS */;
INSERT INTO `TrailingStop` (`StopId`, `OrderId`, `Percentage`) VALUES (1,7,10),(2,5,15),(3,10,1),(4,12,2);
/*!40000 ALTER TABLE `TrailingStop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Transaction` (
  `TransactionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `TransFee` decimal(15,2) DEFAULT NULL,
  `DateNTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `PricePerShare` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`TransactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
INSERT INTO `Transaction` (`TransactionId`, `TransFee`, `DateNTime`, `PricePerShare`) VALUES (1,10.00,'2019-05-05 18:52:37',20.00),(2,10.00,'2019-05-05 18:53:15',20.00),(3,100.00,'2019-05-05 19:16:48',20.00),(4,50.00,'2019-05-05 19:39:32',20.00),(5,7.50,'2019-05-05 19:40:56',15.00),(6,7.50,'2019-05-05 19:55:33',15.00),(7,7.50,'2019-05-05 19:56:58',15.00),(8,8.25,'2019-05-05 20:04:58',15.00),(9,2.25,'2019-05-05 20:13:17',15.00),(10,2.00,'2019-05-05 20:13:40',20.00),(11,3.00,'2019-05-05 20:14:00',15.00),(12,7.50,'2019-05-05 20:34:31',15.00);
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `username` char(30) NOT NULL,
  `password` char(30) DEFAULT NULL,
  `role` char(30) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`username`, `password`, `role`) VALUES ('chen@g.com','123456','manager'),('employee@gmail.com','123456','customerRepresentative'),('jiar@gmail.com','123456','customer');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `corder`
--

DROP TABLE IF EXISTS `corder`;
/*!50001 DROP VIEW IF EXISTS `corder`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `corder` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `StockType`,
 1 AS `ClientId`,
 1 AS `AccountId`,
 1 AS `SSN`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `CreditCardNumber`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `Rating`,
 1 AS `LocationId`,
 1 AS `Fee`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `crevenue`
--

DROP TABLE IF EXISTS `crevenue`;
/*!50001 DROP VIEW IF EXISTS `crevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `crevenue` AS SELECT 
 1 AS `ClientId`,
 1 AS `AccountId`,
 1 AS `SSN`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `CreditCardNumber`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `Rating`,
 1 AS `LocationId`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `crorder`
--

DROP TABLE IF EXISTS `crorder`;
/*!50001 DROP VIEW IF EXISTS `crorder`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `crorder` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `StockType`,
 1 AS `Id`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `StartDate`,
 1 AS `HourlyRate`,
 1 AS `LocationId`,
 1 AS `Fee`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `crrevenue`
--

DROP TABLE IF EXISTS `crrevenue`;
/*!50001 DROP VIEW IF EXISTS `crrevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `crrevenue` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `StockType`,
 1 AS `Id`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `StartDate`,
 1 AS `HourlyRate`,
 1 AS `LocationId`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `employeeinfo`
--

DROP TABLE IF EXISTS `employeeinfo`;
/*!50001 DROP VIEW IF EXISTS `employeeinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `employeeinfo` AS SELECT 
 1 AS `Id`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `StartDate`,
 1 AS `HourlyRate`,
 1 AS `LocationId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `highestcrevenue`
--

DROP TABLE IF EXISTS `highestcrevenue`;
/*!50001 DROP VIEW IF EXISTS `highestcrevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `highestcrevenue` AS SELECT 
 1 AS `ClientId`,
 1 AS `AccountId`,
 1 AS `SSN`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `CreditCardNumber`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `Rating`,
 1 AS `LocationId`,
 1 AS `MaxRevenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `highestrevenue`
--

DROP TABLE IF EXISTS `highestrevenue`;
/*!50001 DROP VIEW IF EXISTS `highestrevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `highestrevenue` AS SELECT 
 1 AS `Id`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `StartDate`,
 1 AS `HourlyRate`,
 1 AS `LocationId`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `mostactivestock`
--

DROP TABLE IF EXISTS `mostactivestock`;
/*!50001 DROP VIEW IF EXISTS `mostactivestock`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `mostactivestock` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `Times`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `mostrecentorder`
--

DROP TABLE IF EXISTS `mostrecentorder`;
/*!50001 DROP VIEW IF EXISTS `mostrecentorder`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `mostrecentorder` AS SELECT 
 1 AS `OrderId`,
 1 AS `StockSymbol`,
 1 AS `Type`,
 1 AS `DateAndTime`,
 1 AS `PricePerShare`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `mostrecentstockorder`
--

DROP TABLE IF EXISTS `mostrecentstockorder`;
/*!50001 DROP VIEW IF EXISTS `mostrecentstockorder`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `mostrecentstockorder` AS SELECT 
 1 AS `OrderId`,
 1 AS `StockSymbol`,
 1 AS `Type`,
 1 AS `DateAndTime`,
 1 AS `PricePerShare`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `name`
--

DROP TABLE IF EXISTS `name`;
/*!50001 DROP VIEW IF EXISTS `name`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `name` AS SELECT 
 1 AS `ClientId`,
 1 AS `AccountId`,
 1 AS `SSN`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Email`,
 1 AS `CreditCardNumber`,
 1 AS `Address`,
 1 AS `State`,
 1 AS `City`,
 1 AS `Zipcode`,
 1 AS `Telephone`,
 1 AS `Rating`,
 1 AS `LocationId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `selectedorders`
--

DROP TABLE IF EXISTS `selectedorders`;
/*!50001 DROP VIEW IF EXISTS `selectedorders`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `selectedorders` AS SELECT 
 1 AS `SSN`,
 1 AS `LastName`,
 1 AS `FirstName`,
 1 AS `Id`,
 1 AS `AccountId`,
 1 AS `StockSysmbol`,
 1 AS `DateandTime`,
 1 AS `PriceperSare`,
 1 AS `Fee`,
 1 AS `OrderType`,
 1 AS `CompanyName`,
 1 AS `StockType`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `stocktraded`
--

DROP TABLE IF EXISTS `stocktraded`;
/*!50001 DROP VIEW IF EXISTS `stocktraded`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `stocktraded` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `TransactionId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `tradedtimes`
--

DROP TABLE IF EXISTS `tradedtimes`;
/*!50001 DROP VIEW IF EXISTS `tradedtimes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `tradedtimes` AS SELECT 
 1 AS `StockSymbol`,
 1 AS `Times`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `corder`
--

/*!50001 DROP VIEW IF EXISTS `corder`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `corder` AS select distinct `S`.`StockSymbol` AS `StockSymbol`,`S`.`Type` AS `StockType`,`n`.`ClientId` AS `ClientId`,`n`.`AccountId` AS `AccountId`,`n`.`SSN` AS `SSN`,`n`.`LastName` AS `LastName`,`n`.`FirstName` AS `FirstName`,`n`.`Email` AS `Email`,`n`.`CreditCardNumber` AS `CreditCardNumber`,`n`.`Address` AS `Address`,`n`.`State` AS `State`,`n`.`City` AS `City`,`n`.`Zipcode` AS `Zipcode`,`n`.`Telephone` AS `Telephone`,`n`.`Rating` AS `Rating`,`n`.`LocationId` AS `LocationId`,`T`.`TransFee` AS `Fee` from (((`trade` `Tr` join `stock` `S`) join `transaction` `T`) join `name` `N`) where ((`Tr`.`StockSymbol` = `S`.`StockSymbol`) and (`T`.`TransactionId` = `Tr`.`TransactionId`) and (`n`.`AccountId` = `Tr`.`AccountId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `crevenue`
--

/*!50001 DROP VIEW IF EXISTS `crevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `crevenue` AS select `c`.`ClientId` AS `ClientId`,`c`.`AccountId` AS `AccountId`,`c`.`SSN` AS `SSN`,`c`.`LastName` AS `LastName`,`c`.`FirstName` AS `FirstName`,`c`.`Email` AS `Email`,`c`.`CreditCardNumber` AS `CreditCardNumber`,`c`.`Address` AS `Address`,`c`.`State` AS `State`,`c`.`City` AS `City`,`c`.`Zipcode` AS `Zipcode`,`c`.`Telephone` AS `Telephone`,`c`.`Rating` AS `Rating`,`c`.`LocationId` AS `LocationId`,sum(`c`.`Fee`) AS `Revenue` from `corder` `C` group by `c`.`LastName`,`c`.`FirstName` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `crorder`
--

/*!50001 DROP VIEW IF EXISTS `crorder`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `crorder` AS select distinct `S`.`StockSymbol` AS `StockSymbol`,`S`.`Type` AS `StockType`,`e`.`Id` AS `Id`,`e`.`LastName` AS `LastName`,`e`.`FirstName` AS `FirstName`,`e`.`Email` AS `Email`,`e`.`Address` AS `Address`,`e`.`State` AS `State`,`e`.`City` AS `City`,`e`.`Zipcode` AS `Zipcode`,`e`.`Telephone` AS `Telephone`,`e`.`StartDate` AS `StartDate`,`e`.`HourlyRate` AS `HourlyRate`,`e`.`LocationId` AS `LocationId`,`T`.`TransFee` AS `Fee` from (((`trade` `Tr` join `stock` `S`) join `transaction` `T`) join `employeeinfo` `E`) where ((`Tr`.`StockSymbol` = `S`.`StockSymbol`) and (`T`.`TransactionId` = `Tr`.`TransactionId`) and (`e`.`Id` = `Tr`.`BrokerId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `crrevenue`
--

/*!50001 DROP VIEW IF EXISTS `crrevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `crrevenue` AS select `c`.`StockSymbol` AS `StockSymbol`,`c`.`StockType` AS `StockType`,`c`.`Id` AS `Id`,`c`.`LastName` AS `LastName`,`c`.`FirstName` AS `FirstName`,`c`.`Email` AS `Email`,`c`.`Address` AS `Address`,`c`.`State` AS `State`,`c`.`City` AS `City`,`c`.`Zipcode` AS `Zipcode`,`c`.`Telephone` AS `Telephone`,`c`.`StartDate` AS `StartDate`,`c`.`HourlyRate` AS `HourlyRate`,`c`.`LocationId` AS `LocationId`,sum(`c`.`Fee`) AS `Revenue` from `crorder` `C` group by `c`.`LastName`,`c`.`FirstName` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `employeeinfo`
--

/*!50001 DROP VIEW IF EXISTS `employeeinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `employeeinfo` AS select `E`.`EmpId` AS `Id`,`P`.`LastName` AS `LastName`,`P`.`FirstName` AS `FirstName`,`P`.`Email` AS `Email`,`P`.`Address` AS `Address`,`L`.`State` AS `State`,`L`.`City` AS `City`,`P`.`Zipcode` AS `Zipcode`,`P`.`Telephone` AS `Telephone`,`E`.`StartDate` AS `StartDate`,`E`.`HourlyRate` AS `HourlyRate`,`L`.`LocationId` AS `LocationId` from ((`employee` `E` join `person` `P`) join `location` `L`) where ((`E`.`EmpId` = `P`.`SSN`) and (`P`.`LocationId` = `L`.`LocationId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `highestcrevenue`
--

/*!50001 DROP VIEW IF EXISTS `highestcrevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `highestcrevenue` AS select `c`.`ClientId` AS `ClientId`,`c`.`AccountId` AS `AccountId`,`c`.`SSN` AS `SSN`,`c`.`LastName` AS `LastName`,`c`.`FirstName` AS `FirstName`,`c`.`Email` AS `Email`,`c`.`CreditCardNumber` AS `CreditCardNumber`,`c`.`Address` AS `Address`,`c`.`State` AS `State`,`c`.`City` AS `City`,`c`.`Zipcode` AS `Zipcode`,`c`.`Telephone` AS `Telephone`,`c`.`Rating` AS `Rating`,`c`.`LocationId` AS `LocationId`,max(`c`.`Revenue`) AS `MaxRevenue` from `crevenue` `C` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `highestrevenue`
--

/*!50001 DROP VIEW IF EXISTS `highestrevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `highestrevenue` AS select `c`.`Id` AS `Id`,`c`.`LastName` AS `LastName`,`c`.`FirstName` AS `FirstName`,`c`.`Email` AS `Email`,`c`.`Address` AS `Address`,`c`.`State` AS `State`,`c`.`City` AS `City`,`c`.`Zipcode` AS `Zipcode`,`c`.`Telephone` AS `Telephone`,`c`.`StartDate` AS `StartDate`,`c`.`HourlyRate` AS `HourlyRate`,`c`.`LocationId` AS `LocationId`,max(`c`.`Revenue`) AS `Revenue` from `crrevenue` `C` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mostactivestock`
--

/*!50001 DROP VIEW IF EXISTS `mostactivestock`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `mostactivestock` AS select `tradedtimes`.`StockSymbol` AS `StockSymbol`,max(`tradedtimes`.`Times`) AS `Times` from `tradedtimes` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mostrecentorder`
--

/*!50001 DROP VIEW IF EXISTS `mostrecentorder`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `mostrecentorder` AS select `O`.`OrderId` AS `OrderId`,`O`.`StockSymbol` AS `StockSymbol`,`S`.`Type` AS `Type`,`O`.`DateNTime` AS `DateAndTime`,`T`.`PricePerShare` AS `PricePerShare` from (((`orders` `O` join `transaction` `T`) join `trade` `Tr`) join `stock` `S`) where ((`O`.`OrderId` = `Tr`.`OrderId`) and (`T`.`TransactionId` = `Tr`.`TransactionId`) and (`S`.`StockSymbol` = `O`.`StockSymbol`) and (`S`.`Type` = 'Computer') and (`O`.`DateNTime` > ((2006 - 6) - 23))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mostrecentstockorder`
--

/*!50001 DROP VIEW IF EXISTS `mostrecentstockorder`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `mostrecentstockorder` AS select `O`.`OrderId` AS `OrderId`,`O`.`StockSymbol` AS `StockSymbol`,`S`.`Type` AS `Type`,`O`.`DateNTime` AS `DateAndTime`,`T`.`PricePerShare` AS `PricePerShare` from (((`orders` `O` join `transaction` `T`) join `trade` `Tr`) join `stock` `S`) where (((`O`.`OrderId` = `Tr`.`OrderId`) and (`T`.`TransactionId` = `Tr`.`TransactionId`) and (`S`.`StockSymbol` = `O`.`StockSymbol`) and (`O`.`DateNTime` > ((2006 - 9) - 23)) and (`S`.`Type` like 'Computer')) or (`S`.`CompanyName` like 'Foundation')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `name`
--

/*!50001 DROP VIEW IF EXISTS `name`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `name` AS select `C`.`ClientId` AS `ClientId`,`A`.`AccountId` AS `AccountId`,`C`.`SSN` AS `SSN`,`P`.`LastName` AS `LastName`,`P`.`FirstName` AS `FirstName`,`P`.`Email` AS `Email`,`C`.`CreditCardNumber` AS `CreditCardNumber`,`P`.`Address` AS `Address`,`L`.`State` AS `State`,`L`.`City` AS `City`,`P`.`Zipcode` AS `Zipcode`,`P`.`Telephone` AS `Telephone`,`C`.`Rating` AS `Rating`,`L`.`LocationId` AS `LocationId` from (((`account` `A` join `client` `C`) join `person` `P`) join `location` `L`) where ((`L`.`LocationId` = `P`.`LocationId`) and (`A`.`ClientId` = `C`.`ClientId`) and (`C`.`SSN` = `P`.`SSN`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `selectedorders`
--

/*!50001 DROP VIEW IF EXISTS `selectedorders`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `selectedorders` AS select `P`.`SSN` AS `SSN`,`P`.`LastName` AS `LastName`,`P`.`FirstName` AS `FirstName`,`C`.`ClientId` AS `Id`,`A`.`AccountId` AS `AccountId`,`O`.`StockSymbol` AS `StockSysmbol`,`T`.`DateNTime` AS `DateandTime`,`T`.`PricePerShare` AS `PriceperSare`,`T`.`TransFee` AS `Fee`,`O`.`OrderType` AS `OrderType`,`S`.`CompanyName` AS `CompanyName`,`S`.`Type` AS `StockType` from ((((((`person` `P` join `client` `C`) join `account` `A`) join `orders` `O`) join `transaction` `T`) join `stock` `S`) join `trade` `Tr`) where ((`P`.`SSN` = `C`.`SSN`) and (`C`.`ClientId` = `A`.`ClientId`) and (`Tr`.`AccountId` = `A`.`AccountId`) and (`Tr`.`TransactionId` = `T`.`TransactionId`) and (`Tr`.`OrderId` = `O`.`OrderId`) and (`O`.`StockSymbol` = `S`.`StockSymbol`) and (`P`.`SSN` = 123459876)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `stocktraded`
--

/*!50001 DROP VIEW IF EXISTS `stocktraded`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `stocktraded` AS select `S`.`StockSymbol` AS `StockSymbol`,`Tr`.`TransactionId` AS `TransactionId` from ((`orders` `O` join `trade` `Tr`) join `stock` `S`) where ((`O`.`StockSymbol` = `S`.`StockSymbol`) and (`O`.`OrderId` = `Tr`.`OrderId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tradedtimes`
--

/*!50001 DROP VIEW IF EXISTS `tradedtimes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jiarchen`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tradedtimes` AS select `stocktraded`.`StockSymbol` AS `StockSymbol`,count(`stocktraded`.`StockSymbol`) AS `Times` from `stocktraded` group by `stocktraded`.`StockSymbol` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-05 18:29:52
