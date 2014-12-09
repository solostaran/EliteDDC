-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 02 Décembre 2014 à 19:44
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `elite`
--

-- --------------------------------------------------------

--
-- Structure de la table `solar_systems`
--

CREATE TABLE IF NOT EXISTS `solar_systems` (
  `idSolarSystem` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idSolarSystem`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Contenu de la table `solar_systems`
--

INSERT INTO `solar_systems` (`idSolarSystem`, `name`) VALUES
(1, 'Carne666');

-- --------------------------------------------------------

--
-- Structure de la table `stations`
--

CREATE TABLE IF NOT EXISTS `stations` (
  `idStation` bigint(20) NOT NULL AUTO_INCREMENT,
  `isBlackMarket` bit(1) NOT NULL,
  `isMarket` bit(1) NOT NULL,
  `isOutfitting` bit(1) NOT NULL,
  `isShipyard` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `idSolarSystem` bigint(20) NOT NULL,
  PRIMARY KEY (`idStation`),
  KEY `FK_sedox8rgwae2tcgks8eurklb3` (`idSolarSystem`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=67 ;

--
-- Contenu de la table `stations`
--

INSERT INTO `stations` (`idStation`, `isBlackMarket`, `isMarket`, `isOutfitting`, `isShipyard`, `name`, `idSolarSystem`) VALUES
(1, b'0', b'0', b'0', b'0', 'TestTotal1', 1),
(2, b'0', b'1', b'0', b'1', 'TestEsso2', 1);

-- --------------------------------------------------------

--
-- Structure de la table `goods_categories`
--

CREATE TABLE IF NOT EXISTS `goods_categories` (
  `idGoodsCategory` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idGoodsCategory`),
  UNIQUE KEY `UK_h92xrsw84fb32gcwhc8cwa88o` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `goods_categories`
--

INSERT INTO `goods_categories` (`idGoodsCategory`, `name`) VALUES
(1, 'Chemicals'),
(2, 'Consumer items'),
(3, 'Foods'),
(4, 'Industrial materials'),
(5, 'Legal drugs'),
(6, 'Machinery'),
(7, 'Medicines'),
(8, 'Metals'),
(9, 'Minerals'),
(10, 'Technology'),
(11, 'Textiles'),
(12, 'Waste'),
(13, 'Weapons');

-- --------------------------------------------------------

--
-- Structure de la table `goods_designations`
--

CREATE TABLE IF NOT EXISTS `goods_designations` (
  `idGoodsDesignation` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `idGoodsCategory` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idGoodsDesignation`),
  UNIQUE KEY `UK_rc9u7umagmn8qcn4hfocjkxh2` (`name`),
  KEY `FK_ih6i8npbv4at0fq1iggfc9ahx` (`idGoodsCategory`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=80 ;

--
-- Contenu de la table `goods_designations`
--

INSERT INTO `goods_designations` (`idGoodsDesignation`, `name`, `idGoodsCategory`) VALUES
(1, 'Explosives', 1),
(2, 'Hydrogen fuel', 1),
(3, 'Mineral oil', 1),
(4, 'Pesticides', 1),
(5, 'Clothing', 2),
(6, 'Consumer technology', 2),
(7, 'Dom. appliances', 2),
(8, 'Algae', 3),
(9, 'Animal meat', 3),
(10, 'Coffee', 3),
(11, 'Fish', 3),
(12, 'Food cartridges', 3),
(13, 'Fruit and vegetables', 3),
(14, 'Grain', 3),
(15, 'Synthetic meat', 3),
(16, 'Tea', 3),
(17, 'Polymers', 4),
(18, 'Semiconductors', 4),
(19, 'Superconductors', 4),
(20, 'Beer', 5),
(21, 'Liquor', 5),
(22, 'Narcotics', 5),
(23, 'Tobacco', 5),
(24, 'Wine', 5),
(25, 'Atmospheric processor', 6),
(26, 'Crop Harvesters', 6),
(27, 'Hel-static furnaces', 6),
(28, 'Marine equipment', 6),
(29, 'Mineral extractors', 6),
(30, 'Microbial furnaces', 6),
(31, 'Power generators', 6),
(32, 'Water purifiers', 6),
(33, 'Agri-medicines', 7),
(34, 'Basic medicines', 7),
(35, 'Combat stabilisers', 7),
(36, 'Performance enhancers', 7),
(37, 'Progenitor cells', 7),
(38, 'Aluminium', 8),
(39, 'Beryllium', 8),
(40, 'Cobalt', 8),
(41, 'Copper', 8),
(42, 'Gallium', 8),
(43, 'Gold', 8),
(44, 'Indium', 8),
(45, 'Lithium', 8),
(46, 'Palladium', 8),
(47, 'Platinum', 8),
(48, 'Silver', 8),
(49, 'Tantalum', 8),
(50, 'Titanium', 8),
(51, 'Uranium', 8),
(52, 'Bauxite', 9),
(53, 'Bertrandite', 9),
(54, 'Coltan', 9),
(55, 'Gallite', 9),
(56, 'Indite', 9),
(57, 'Lepidolite', 9),
(58, 'Rutile', 9),
(59, 'Uraninite', 9),
(60, 'Advanced catalysers', 10),
(61, 'Animal monitors', 10),
(62, 'Aquaponic systems', 10),
(63, 'Auto-fabricators', 10),
(64, 'Bioreducing lichen', 10),
(65, 'Computer components', 10),
(66, 'H.E. suits', 10),
(67, 'Land enrichment systems', 10),
(68, 'Resonating separators', 10),
(69, 'Robotics', 10),
(70, 'Leather', 11),
(71, 'Natural fabrics', 11),
(72, 'Synthetic fabrics', 11),
(73, 'Biowaste', 12),
(74, 'Chemical waste', 12),
(75, 'Scrap', 12),
(76, 'Battle weapons', 13),
(77, 'Non-lethal wpns', 13),
(78, 'Personal weapons', 13),
(79, 'Reactive armour', 13);

-- --------------------------------------------------------

--
-- Structure de la table `goods`
--

CREATE TABLE IF NOT EXISTS `goods` (
  `idGoods` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastUpdated` bigint(20) NOT NULL,
  `number` bigint(20) NOT NULL,
  `price` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  `supplyOrDemand` int(11) NOT NULL,
  `idGoodsDesignation` bigint(20) NOT NULL,
  `idStation` bigint(20) NOT NULL,
  PRIMARY KEY (`idGoods`),
  KEY `FK_hh6yhm08j7r39j8cfvv92xbaw` (`idGoodsDesignation`),
  KEY `FK_j83vdxc6jcmtvaybwi0t81bqf` (`idStation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `goods`
--

INSERT INTO `goods` (`idGoods`, `lastUpdated`, `number`, `price`, `priority`, `supplyOrDemand`, `idGoodsDesignation`, `idStation`) VALUES
(2, 1417188397551, 15689, 1200, 3, 2, 30, 2),
(3, 1417188397839, 500, 6005, 1, 1, 52, 2);

-- --------------------------------------------------------

--
-- Structure de la table `ship_outfit_categories`
--

CREATE TABLE IF NOT EXISTS `ship_outfit_categories` (
  `idShipOutfitCategory` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idShipOutfitCategory`),
  UNIQUE KEY `UK_nq9hhxyo3nq2f2jl2w7ak455a` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ship_outfit_categories`
--

INSERT INTO `ship_outfit_categories` (`idShipOutfitCategory`, `name`) VALUES
(3, 'Bulkheads'),
(7, 'Environmental control'),
(6, 'Frame Shift Drive housing'),
(10, 'Fuel Store'),
(1, 'Hardpoint'),
(11, 'Internal compartment'),
(8, 'Power coupling'),
(4, 'Reactor bay'),
(9, 'Sensor suite'),
(5, 'Thruster mounting'),
(2, 'Utility Mount');

-- --------------------------------------------------------

--
-- Structure de la table `ship_buyables`
--

CREATE TABLE IF NOT EXISTS `ship_buyables` (
  `idShipBuyable` bigint(20) NOT NULL AUTO_INCREMENT,
  `defaultMaxRange` float NOT NULL,
  `defaultMinRange` float NOT NULL,
  `mass` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` bigint(20) NOT NULL,
  PRIMARY KEY (`idShipBuyable`),
  UNIQUE KEY `UK_n5h2rmotarvvx7rqqm4mymw4q` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=55 ;

--
-- Contenu de la table `ship_buyables`
--

INSERT INTO `ship_buyables` (`idShipBuyable`, `defaultMaxRange`, `defaultMinRange`, `mass`, `name`, `price`) VALUES
(1, 7.79, 7.56, 25, 'Sidewinder', 32000),
(2, 8.76, 8.27, 50, 'Eagle', 44800),
(3, 0, 0, 14, 'Hauler', 52720),
(4, 7.12, 6.92, 60, 'Viper', 142931),
(5, 10.46, 9.78, 180, 'Cobra Mk3', 279718),
(6, 0, 0, 155, 'Type-6 Transporter', 1045945),
(7, 0, 0, 280, 'ASP', 6661153),
(8, 0, 0, 580, 'Federal Dropship', 37814205),
(9, 0, 0, 1000, 'Lakon Type-9 Heavy', 76500000),
(10, 0, 0, 400, 'Anaconda', 146969451);

-- --------------------------------------------------------

--
-- Structure de la table `ship_outfit_slots`
--

CREATE TABLE IF NOT EXISTS `ship_outfit_slots` (
  `idShipOutfitSlot` bigint(20) NOT NULL AUTO_INCREMENT,
  `size` int(11) NOT NULL,
  `idShipBuyable` bigint(20) NOT NULL,
  `idShipOutfitCategory` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idShipOutfitSlot`),
  KEY `FK_hbsv7y4ol1t6tlpiwoioqr07c` (`idShipBuyable`),
  KEY `FK_tf755ointevojr7oat0g625qo` (`idShipOutfitCategory`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=196 ;

--
-- Contenu de la table `ship_outfit_slots`
--

INSERT INTO `ship_outfit_slots` (`idShipOutfitSlot`, `size`, `idShipBuyable`, `idShipOutfitCategory`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 1),
(3, 0, 1, 2),
(4, 0, 1, 2),
(5, 8, 1, 3),
(6, 2, 1, 4),
(7, 2, 1, 5),
(8, 2, 1, 6),
(9, 1, 1, 7),
(10, 1, 1, 8),
(11, 1, 1, 9),
(12, 1, 1, 10),
(13, 2, 1, 11),
(14, 2, 1, 11),
(15, 1, 1, 11),
(16, 1, 2, 1),
(17, 1, 2, 1),
(18, 1, 2, 1),
(19, 0, 2, 2),
(20, 8, 2, 3),
(21, 2, 2, 4),
(22, 3, 2, 5),
(23, 3, 2, 6),
(24, 1, 2, 7),
(25, 2, 2, 8),
(26, 2, 2, 9),
(27, 2, 2, 10),
(28, 3, 2, 11),
(29, 2, 2, 11),
(30, 1, 2, 11),
(31, 1, 3, 1),
(32, 0, 3, 2),
(33, 0, 3, 2),
(34, 8, 3, 3),
(35, 2, 3, 4),
(36, 2, 3, 5),
(37, 2, 3, 6),
(38, 1, 3, 7),
(39, 1, 3, 8),
(40, 1, 3, 9),
(41, 2, 3, 10),
(42, 3, 3, 11),
(43, 3, 3, 11),
(44, 2, 3, 11),
(45, 1, 3, 11),
(46, 1, 4, 1),
(47, 1, 4, 1),
(48, 2, 4, 1),
(49, 2, 4, 1),
(50, 0, 4, 2),
(51, 0, 4, 2),
(52, 8, 4, 3),
(53, 3, 4, 4),
(54, 3, 4, 5),
(55, 3, 4, 6),
(56, 2, 4, 7),
(57, 3, 4, 8),
(58, 3, 4, 9),
(59, 2, 4, 10),
(60, 3, 4, 11),
(61, 3, 4, 11),
(62, 2, 4, 11),
(63, 1, 4, 11),
(64, 1, 5, 1),
(65, 1, 5, 1),
(66, 2, 5, 1),
(67, 2, 5, 1),
(68, 0, 5, 2),
(69, 0, 5, 2),
(70, 8, 5, 3),
(71, 4, 5, 4),
(72, 4, 5, 5),
(73, 4, 5, 6),
(74, 3, 5, 7),
(75, 3, 5, 8),
(76, 3, 5, 9),
(77, 8, 5, 10),
(78, 4, 5, 11),
(79, 4, 5, 11),
(80, 4, 5, 11),
(81, 2, 5, 11),
(82, 2, 5, 11),
(83, 2, 5, 11),
(84, 1, 6, 1),
(85, 1, 6, 1),
(86, 0, 6, 2),
(87, 0, 6, 2),
(88, 0, 6, 2),
(89, 8, 6, 10),
(90, 5, 6, 11),
(91, 5, 6, 11),
(92, 4, 6, 11),
(93, 4, 6, 11),
(94, 3, 6, 11),
(95, 2, 6, 11),
(96, 2, 6, 11),
(97, 1, 7, 1),
(98, 1, 7, 1),
(99, 1, 7, 1),
(100, 1, 7, 1),
(101, 2, 7, 1),
(102, 2, 7, 1),
(103, 0, 7, 2),
(104, 0, 7, 2),
(105, 0, 7, 2),
(106, 0, 7, 2),
(107, 16, 7, 10),
(108, 6, 7, 11),
(109, 6, 7, 11),
(110, 5, 7, 11),
(111, 3, 7, 11),
(112, 3, 7, 11),
(113, 3, 7, 11),
(114, 2, 7, 11),
(115, 2, 7, 11),
(116, 2, 8, 1),
(117, 2, 8, 1),
(118, 2, 8, 1),
(119, 2, 8, 1),
(120, 3, 8, 1),
(121, 0, 8, 2),
(122, 0, 8, 2),
(123, 0, 8, 2),
(124, 0, 8, 2),
(125, 8, 8, 10),
(126, 6, 8, 11),
(127, 5, 8, 11),
(128, 5, 8, 11),
(129, 4, 8, 11),
(130, 3, 8, 11),
(131, 3, 8, 11),
(132, 2, 8, 11),
(133, 1, 9, 1),
(134, 1, 9, 1),
(135, 2, 9, 1),
(136, 2, 9, 1),
(137, 2, 9, 1),
(138, 0, 9, 2),
(139, 0, 9, 2),
(140, 0, 9, 2),
(141, 0, 9, 2),
(142, 16, 9, 10),
(143, 8, 9, 11),
(144, 7, 9, 11),
(145, 6, 9, 11),
(146, 5, 9, 11),
(147, 4, 9, 11),
(148, 4, 9, 11),
(149, 3, 9, 11),
(150, 3, 9, 11),
(151, 2, 9, 11),
(152, 1, 10, 1),
(153, 1, 10, 1),
(154, 2, 10, 1),
(155, 2, 10, 1),
(156, 3, 10, 1),
(157, 3, 10, 1),
(158, 3, 10, 1),
(159, 4, 10, 1),
(160, 0, 10, 2),
(161, 0, 10, 2),
(162, 0, 10, 2),
(163, 0, 10, 2),
(164, 0, 10, 2),
(165, 0, 10, 2),
(166, 0, 10, 2),
(167, 0, 10, 2),
(168, 16, 10, 10),
(169, 7, 10, 11),
(170, 6, 10, 11),
(171, 6, 10, 11),
(172, 6, 10, 11),
(173, 5, 10, 11),
(174, 5, 10, 11),
(175, 5, 10, 11),
(176, 4, 10, 11),
(177, 4, 10, 11),
(178, 4, 10, 11),
(179, 2, 10, 11);


-- --------------------------------------------------------

--
-- Structure de la table `station_shipyard`
--

CREATE TABLE IF NOT EXISTS `station_shipyard` (
  `idStation` bigint(20) NOT NULL,
  `idShipBuyable` bigint(20) NOT NULL,
  KEY `FK_f5oipyi0c882aq4w4g16fvokd` (`idShipBuyable`),
  KEY `FK_nwlb1utojdn50pki66ax87eem` (`idStation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `station_shipyard`
--

INSERT INTO `station_shipyard` (`idStation`, `idShipBuyable`) VALUES
(2, 1),
(2, 10);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `goods`
--
ALTER TABLE `goods`
  ADD CONSTRAINT `FK_hh6yhm08j7r39j8cfvv92xbaw` FOREIGN KEY (`idGoodsDesignation`) REFERENCES `goods_designations` (`idGoodsDesignation`),
  ADD CONSTRAINT `FK_j83vdxc6jcmtvaybwi0t81bqf` FOREIGN KEY (`idStation`) REFERENCES `stations` (`idStation`);

--
-- Contraintes pour la table `goods_designations`
--
ALTER TABLE `goods_designations`
  ADD CONSTRAINT `FK_ih6i8npbv4at0fq1iggfc9ahx` FOREIGN KEY (`idGoodsCategory`) REFERENCES `goods_categories` (`idGoodsCategory`);

--
-- Contraintes pour la table `ship_outfit_slots`
--
ALTER TABLE `ship_outfit_slots`
  ADD CONSTRAINT `FK_hbsv7y4ol1t6tlpiwoioqr07c` FOREIGN KEY (`idShipBuyable`) REFERENCES `ship_buyables` (`idShipBuyable`),
  ADD CONSTRAINT `FK_tf755ointevojr7oat0g625qo` FOREIGN KEY (`idShipOutfitCategory`) REFERENCES `ship_outfit_categories` (`idShipOutfitCategory`);

--
-- Contraintes pour la table `stations`
--
ALTER TABLE `stations`
  ADD CONSTRAINT `FK_sedox8rgwae2tcgks8eurklb3` FOREIGN KEY (`idSolarSystem`) REFERENCES `solar_systems` (`idSolarSystem`);

--
-- Contraintes pour la table `station_shipyard`
--
ALTER TABLE `station_shipyard`
  ADD CONSTRAINT `FK_f5oipyi0c882aq4w4g16fvokd` FOREIGN KEY (`idShipBuyable`) REFERENCES `ship_buyables` (`idShipBuyable`),
  ADD CONSTRAINT `FK_nwlb1utojdn50pki66ax87eem` FOREIGN KEY (`idStation`) REFERENCES `stations` (`idStation`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
