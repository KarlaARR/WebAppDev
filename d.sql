-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: ProyectoFinal
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

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
-- Current Database: `ProyectoFinal`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ProyectoFinal` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ProyectoFinal`;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `imagen` varchar(50) DEFAULT 'categoria.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
INSERT INTO `Categoria` VALUES (1,'Perfumeria','Fragancias para hombre y mujer','perfumes.png'),(2,'Maquillaje','Todo tipo de maquillaje','maquillaje.png'),(3,'Rostro','Todo lo necesario para tener un rostro perfecto','rostro.png');
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estado`
--

DROP TABLE IF EXISTS `Estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `clave` varchar(2) NOT NULL,
  `abreviacion` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado`
--

LOCK TABLES `Estado` WRITE;
/*!40000 ALTER TABLE `Estado` DISABLE KEYS */;
INSERT INTO `Estado` VALUES (1,'Aguascalientes','AS','Agu'),(2,'Baja California','BC','BCN'),(3,'Baja California Sur','BS','BCS');
/*!40000 ALTER TABLE `Estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Producto`
--

DROP TABLE IF EXISTS `Producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `categoria` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `imagen` varchar(100) DEFAULT 'producto.png',
  PRIMARY KEY (`id`),
  KEY `FKsronu4ja2553s22559fv0e04j` (`categoria`),
  CONSTRAINT `FKsronu4ja2553s22559fv0e04j` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`),
  CONSTRAINT `Producto_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Producto`
--

LOCK TABLES `Producto` WRITE;
/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
INSERT INTO `Producto` VALUES (1,'Luna','Flores rojas con almendra y canela',1,80,'luna.png'),(2,'Luna Radiante','Vo a llora',1,80,'luna.png'),(3,'Lapiz para ojos','Lapiz en fibra para ojos negro',2,200,'lapiz.png'),(4,'Protector Solar','Fluido multiprotector FPS 50',3,50,'protector.png'),(6,'farfaefaer','faerf',2,100,NULL),(8,'vsev','vserv',3,10,NULL),(9,'ferf','dawe',3,100,NULL);
/*!40000 ALTER TABLE `Producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoUsuario`
--

DROP TABLE IF EXISTS `TipoUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoUsuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoUsuario`
--

LOCK TABLES `TipoUsuario` WRITE;
/*!40000 ALTER TABLE `TipoUsuario` DISABLE KEYS */;
INSERT INTO `TipoUsuario` VALUES (1,'Administrador'),(2,'Vendedor'),(3,'Almacen');
/*!40000 ALTER TABLE `TipoUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `paterno` varchar(50) NOT NULL,
  `email` varchar(80) NOT NULL,
  `tipo` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(16) NOT NULL,
  `foto` varchar(100) DEFAULT 'usuario.png',
  `estado` int(11) NOT NULL,
  `materno` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnaibsucag0uey9pji687qprtp` (`tipo`),
  KEY `FK1uue02jrpld2lc6wyj1m48vg8` (`estado`),
  CONSTRAINT `FK1uue02jrpld2lc6wyj1m48vg8` FOREIGN KEY (`estado`) REFERENCES `Estado` (`id`),
  CONSTRAINT `FKb5r17npdte26nvp2yiwo8vodj` FOREIGN KEY (`tipo`) REFERENCES `Categoria` (`id`),
  CONSTRAINT `FKnaibsucag0uey9pji687qprtp` FOREIGN KEY (`tipo`) REFERENCES `TipoUsuario` (`id`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `TipoUsuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Usuario_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `Estado` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'Alondra','Perez','alondra@gmail.com',2,'AlondraP','123456','Alondra.png',1,'Paz'),(3,'Ernesto','Hernandez','erneto@gmail.com',3,'ernestoH','123456','Ernesto.png',2,'Ruiz'),(4,'Pedro','Perez','pedro@gmail.com',1,'pedro','12345','Pedro.png',1,'Sanchez');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-25 17:07:25
