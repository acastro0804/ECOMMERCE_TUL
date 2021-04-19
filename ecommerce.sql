-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 13-04-2021 a las 10:05:27
-- Versión del servidor: 8.0.21
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ecommerce`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

DROP TABLE IF EXISTS `carrito`;
CREATE TABLE IF NOT EXISTS `carrito` (
  `idCarrito` int NOT NULL AUTO_INCREMENT,
  `fechaCreacion` datetime DEFAULT NULL,
  `fechaProcesamiento` datetime DEFAULT NULL,
  `estado` int DEFAULT NULL,
  PRIMARY KEY (`idCarrito`),
  KEY `Carrito_idCarrito` (`idCarrito`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`idCarrito`, `fechaCreacion`, `fechaProcesamiento`, `estado`) VALUES
(35, '2021-04-12 17:54:02', '2021-04-13 02:46:19', 1),
(36, '2021-04-12 00:59:09', '2021-04-13 02:46:33', 1),
(37, '2021-04-12 02:47:09', '2021-04-19 02:47:37', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carritoproducto`
--

DROP TABLE IF EXISTS `carritoproducto`;
CREATE TABLE IF NOT EXISTS `carritoproducto` (
  `idCarritoProducto` int NOT NULL AUTO_INCREMENT,
  `idCarrito` int DEFAULT NULL,
  `idProducto` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `precioProducto` double(15,2) DEFAULT NULL,
  PRIMARY KEY (`idCarritoProducto`),
  KEY `Carrito_idCarrito` (`idCarrito`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `carritoproducto`
--

INSERT INTO `carritoproducto` (`idCarritoProducto`, `idCarrito`, `idProducto`, `cantidad`, `precioProducto`) VALUES
(34, 36, 87, 1, 0.12),
(33, 36, 86, 2, 8.90),
(37, 37, 72, 5, 11.50),
(31, 35, 87, 1, 0.12),
(38, 37, 84, 1, 12.00),
(36, 35, 86, 2, 8.90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `sku` varchar(50) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double(15,2) DEFAULT NULL,
  `tipo` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `producto_sku` (`sku`)
) ENGINE=MyISAM AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `sku`, `nombre`, `descripcion`, `precio`, `tipo`, `stock`) VALUES
(72, '00167', 'Cemento Blanco', 'Cemento Blanco Disensa ISO', 11.50, 0, 14),
(86, '00169', 'Ceramica Graiman', '45x45 cm', 8.90, 0, 100),
(82, '00168', 'Cemento rocafuerte', 'Cemento Holcin', 9.80, 0, 150),
(84, '00018', 'Desarmador Kliton', 'Desarmador Kliton 8\'\'', 12.00, 0, 500),
(87, '00178', 'Clavos de acero', 'Calvos 7\'\'', 0.12, 0, 1000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
