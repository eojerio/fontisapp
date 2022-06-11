-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2022 at 01:07 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fontis_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `fontis_useradminbreakdown`
--

CREATE TABLE `fontis_useradminbreakdown` (
  `admin_breakdownID` int(11) NOT NULL,
  `admin_historyprodID` int(11) NOT NULL,
  `admin_cartID` int(11) NOT NULL,
  `admin_cartuserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_useradminbreakdown`
--

INSERT INTO `fontis_useradminbreakdown` (`admin_breakdownID`, `admin_historyprodID`, `admin_cartID`, `admin_cartuserID`) VALUES
(268, 209, 568, 11),
(269, 209, 569, 11),
(270, 210, 570, 11),
(271, 210, 571, 11),
(272, 211, 572, 11),
(273, 211, 573, 11),
(274, 211, 576, 11),
(275, 212, 577, 11),
(276, 212, 578, 11),
(277, 213, 579, 10),
(278, 213, 580, 10),
(279, 214, 581, 18),
(280, 214, 582, 18),
(281, 215, 584, 18),
(282, 215, 585, 18),
(283, 215, 586, 18),
(284, 216, 587, 10),
(285, 216, 588, 10),
(286, 216, 589, 10);

-- --------------------------------------------------------

--
-- Table structure for table `fontis_useradminhistory`
--

CREATE TABLE `fontis_useradminhistory` (
  `admin_historyID` int(11) NOT NULL,
  `admin_historyprodID` int(11) NOT NULL,
  `admin_userID` int(11) NOT NULL,
  `admin_prodDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_useradminhistory`
--

INSERT INTO `fontis_useradminhistory` (`admin_historyID`, `admin_historyprodID`, `admin_userID`, `admin_prodDate`) VALUES
(41, 209, 11, '2022-06-10'),
(42, 210, 11, '2022-06-10'),
(43, 211, 11, '2022-06-10'),
(44, 213, 10, '2022-06-11'),
(45, 212, 11, '2022-06-11'),
(46, 216, 10, '2022-06-11');

-- --------------------------------------------------------

--
-- Table structure for table `fontis_useradminorders`
--

CREATE TABLE `fontis_useradminorders` (
  `admin_prodID` int(11) NOT NULL,
  `admin_historyprodID` int(11) NOT NULL,
  `admin_userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_useradminorders`
--

INSERT INTO `fontis_useradminorders` (`admin_prodID`, `admin_historyprodID`, `admin_userID`) VALUES
(165, 214, 18),
(166, 215, 18);

-- --------------------------------------------------------

--
-- Table structure for table `fontis_usercarts`
--

CREATE TABLE `fontis_usercarts` (
  `cart_id` int(11) NOT NULL,
  `cart_userID` int(11) NOT NULL,
  `cart_prodTag` int(11) NOT NULL,
  `cart_prodPrice` double NOT NULL,
  `cart_prodName` varchar(255) NOT NULL,
  `cart_prodDesc` varchar(255) NOT NULL,
  `cart_prodQty` int(11) NOT NULL,
  `cart_prodImg` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `fontis_usercartscheckout`
--

CREATE TABLE `fontis_usercartscheckout` (
  `admin_cartID` int(11) NOT NULL,
  `admincart_id` int(11) NOT NULL,
  `cart_userID` int(11) NOT NULL,
  `cart_prodTag` int(11) NOT NULL,
  `cart_prodPrice` double NOT NULL,
  `cart_prodName` varchar(255) NOT NULL,
  `cart_prodDesc` varchar(255) NOT NULL,
  `cart_prodQty` int(11) NOT NULL,
  `cart_prodImg` varchar(255) NOT NULL,
  `cart_historyID` varchar(255) NOT NULL,
  `cart_deliveredStatus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_usercartscheckout`
--

INSERT INTO `fontis_usercartscheckout` (`admin_cartID`, `admincart_id`, `cart_userID`, `cart_prodTag`, `cart_prodPrice`, `cart_prodName`, `cart_prodDesc`, `cart_prodQty`, `cart_prodImg`, `cart_historyID`, `cart_deliveredStatus`) VALUES
(161, 568, 11, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230933', '209', 'not delivered'),
(162, 569, 11, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230933', '209', 'not delivered'),
(163, 570, 11, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 1, '2131230932', '210', 'not delivered'),
(164, 571, 11, 17, 30, 'Fontis 16 ounce infused Water', 'A refreshing and healthy twist to your good old thirst quencher.(comes in different flavors: lime, strawberry, apple, kiwi, cucumber & peach)', 1, '2131230931', '210', 'not delivered'),
(165, 572, 11, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230933', '211', 'not delivered'),
(166, 573, 11, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 2, '2131230933', '211', 'not delivered'),
(167, 576, 11, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 2, '2131230962', '211', 'not delivered'),
(168, 577, 11, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230955', '212', 'not delivered'),
(169, 578, 11, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230955', '212', 'not delivered'),
(170, 579, 10, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230955', '213', 'not delivered'),
(171, 580, 10, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 1, '2131230962', '213', 'not delivered'),
(172, 581, 18, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 4, '2131230955', '214', 'not delivered'),
(173, 582, 18, 10, 55, '3 pack Fontis 1 liter Alkaline Water', 'Alkaline thirst quencher to share with a friend or gym buddies after exercising.', 2, '2131230970', '214', 'not delivered'),
(174, 584, 18, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 2, '2131230955', '215', 'not delivered'),
(175, 585, 18, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 2, '2131230962', '215', 'not delivered'),
(176, 586, 18, 17, 30, 'Fontis 16 ounce infused Water', 'A refreshing and healthy twist to your good old thirst quencher.(comes in different flavors: lime, strawberry, apple, kiwi, cucumber & peach)', 2, '2131230961', '215', 'not delivered'),
(177, 587, 10, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 2, '2131230962', '216', 'not delivered'),
(178, 588, 10, 16, 210, '3 pack Fontis 5 liter Purified Water', 'If one is not enough, add more refreshing purified water for quick thirst quencher and refreshment.', 2, '2131230960', '216', 'not delivered'),
(179, 589, 10, 13, 159, 'Fontis 5 Gallon Purified Water(Slim container)', 'Purified refreshment mineral water in a slim container with faucet for portability.', 4, '2131230957', '216', 'not delivered');

-- --------------------------------------------------------

--
-- Table structure for table `fontis_userhistory`
--

CREATE TABLE `fontis_userhistory` (
  `prod_id` int(11) NOT NULL,
  `history_userID` int(11) NOT NULL,
  `prod_price` int(11) NOT NULL,
  `prod_date` date NOT NULL,
  `prod_amt` int(11) NOT NULL,
  `prod_img` varchar(255) NOT NULL,
  `prod_adminAccepted` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_userhistory`
--

INSERT INTO `fontis_userhistory` (`prod_id`, `history_userID`, `prod_price`, `prod_date`, `prod_amt`, `prod_img`, `prod_adminAccepted`) VALUES
(209, 11, 190, '2022-06-10', 2, '2131165281', 'true'),
(210, 11, 199, '2022-06-10', 2, '2131165281', 'true'),
(211, 11, 683, '2022-06-10', 5, '2131165281', 'true'),
(212, 11, 190, '2022-06-11', 2, '2131165281', 'true'),
(213, 10, 324, '2022-06-11', 2, '2131165281', 'true'),
(214, 18, 250, '2022-06-11', 6, '2131165281', 'false'),
(215, 18, 468, '2022-06-11', 6, '2131165281', 'false'),
(216, 10, 1394, '2022-06-11', 8, '2131165281', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `fontis_userhistorybreakdown`
--

CREATE TABLE `fontis_userhistorybreakdown` (
  `history_breakoutID` int(11) NOT NULL,
  `history_prodID` int(11) NOT NULL,
  `history_cartID` int(11) NOT NULL,
  `history_userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_userhistorybreakdown`
--

INSERT INTO `fontis_userhistorybreakdown` (`history_breakoutID`, `history_prodID`, `history_cartID`, `history_userID`) VALUES
(1, 209, 568, 11),
(2, 209, 569, 11),
(3, 210, 570, 11),
(4, 210, 571, 11),
(5, 211, 572, 11),
(6, 211, 573, 11),
(7, 211, 576, 11),
(8, 212, 577, 11),
(9, 212, 578, 11),
(10, 213, 579, 10),
(11, 213, 580, 10),
(12, 214, 581, 18),
(13, 214, 582, 18),
(14, 215, 584, 18),
(15, 215, 585, 18),
(16, 215, 586, 18),
(17, 216, 587, 10),
(18, 216, 588, 10),
(19, 216, 589, 10);

-- --------------------------------------------------------

--
-- Table structure for table `fontis_userprofiles`
--

CREATE TABLE `fontis_userprofiles` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `employment_status` varchar(255) NOT NULL,
  `marital_status` varchar(255) NOT NULL,
  `user_description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_userprofiles`
--

INSERT INTO `fontis_userprofiles` (`id`, `username`, `password`, `first_name`, `last_name`, `contact_no`, `address`, `birthdate`, `email_address`, `employment_status`, `marital_status`, `user_description`) VALUES
(4, 'Zeke2', '4297f44b13955235245b2497399d7a93', 'Ezekiel', 'Ezeke', '123123', '59 Acacia st.', '2001-11-23', 'myemail@gmail.com', 'Employed', 'Married', ''),
(5, 'Try', '4297f44b13955235245b2497399d7a93', 'Kiel', 'Lmao', '123123', '59 Acacia', '2001-11-23', 'smir@gmail.com', 'Employed', 'Not YET', ''),
(8, 'Jabarcs', '06f90940b05fea6fb55410443d0883f0', 'Jabs', 'Barcs', '123123', 'Makati City Taguig', '2001-11-23', 'jabarcs@gmail.com', 'Employed', 'Single', ''),
(10, 'MarkBonks', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Mark', 'Bonks', '0958182472475', '69 Acacia st. Cembo, Makati City.', '2001-11-23', 'yo@gmail.com', 'Employed', 'Married', 'hahaha'),
(11, 'Decorus', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Ezekiel', 'Ojerio', '09562294726', '59 Acacia st. Cembo, Makati City', '2001-11-23', 'ojerioezekiel@gmail.com', 'Employed', 'Single', 'TestTest'),
(12, 'itlognamaalat21', '33f5a7e8f4f310f9774894d807728e3c', 'Ezekiel', 'Ojerio', '09562294726', '126 West Boulevard, Los Angeles, California', '2004-11-23', 'joemama@gmail.com', 'Self-Employed', 'Single', ''),
(13, 'HeyBarbara', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Hey', 'Barbara', '095124819248', 'Beverly Ave', '2001-11-23', 'Dancingnow@gmail.com', 'Unemployed', 'Separated', ''),
(15, 'Legendary', '035c38b95750ab68cc7544f3f821e7f1', 'Charizard', 'Pokemon', '09653294781', 'Brgy. Concepcion San Pablo City Laguna', '1996-11-23', 'charizard6969@gmail.com', 'Unemployed', 'Single', ''),
(16, 'Ben', '035c38b95750ab68cc7544f3f821e7f1', 'Benedicto', 'Lucido Jr.', '09369733382', '3487 Gracia St. Pinagkaisahan Makati City Philippines', '2020-06-13', 'benedictlucido69@gmail.com', 'Employed', 'Married', ''),
(17, 'Bro', '4297f44b13955235245b2497399d7a93', 'Ezkeiel', 'bro', '0958124819', '59 Acacia st. Cembo, Makati City', '2002-11-11', 'myname@gmail.com', 'Employed', 'Married', ''),
(18, 'Zeke', '33f5a7e8f4f310f9774894d807728e3c', 'Ezekiel', 'Ojerio', '09562294726', '59 Acacia st. Cembo, Makati City', '2001-11-23', 'rivennation201@gmail.com', 'Employed', 'Married', 'Hello I\'m Zeke');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fontis_useradminbreakdown`
--
ALTER TABLE `fontis_useradminbreakdown`
  ADD PRIMARY KEY (`admin_breakdownID`),
  ADD UNIQUE KEY `admin_cartID` (`admin_cartID`),
  ADD KEY `admin_historyprodID` (`admin_historyprodID`),
  ADD KEY `admin_cartuserID_to_id` (`admin_cartuserID`);

--
-- Indexes for table `fontis_useradminhistory`
--
ALTER TABLE `fontis_useradminhistory`
  ADD PRIMARY KEY (`admin_historyID`),
  ADD KEY `admin_historyprodID` (`admin_historyprodID`),
  ADD KEY `admin_userID` (`admin_userID`);

--
-- Indexes for table `fontis_useradminorders`
--
ALTER TABLE `fontis_useradminorders`
  ADD PRIMARY KEY (`admin_prodID`),
  ADD KEY `admin_historyprodID` (`admin_historyprodID`),
  ADD KEY `admin_userID_to_id` (`admin_userID`);

--
-- Indexes for table `fontis_usercarts`
--
ALTER TABLE `fontis_usercarts`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `cart_userID` (`cart_userID`);

--
-- Indexes for table `fontis_usercartscheckout`
--
ALTER TABLE `fontis_usercartscheckout`
  ADD PRIMARY KEY (`admin_cartID`),
  ADD UNIQUE KEY `admincart_id` (`admincart_id`),
  ADD KEY `cart_userID` (`cart_userID`),
  ADD KEY `admincart_id_2` (`admincart_id`),
  ADD KEY `admincart_id_3` (`admincart_id`);

--
-- Indexes for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  ADD PRIMARY KEY (`prod_id`),
  ADD KEY `history_userID` (`history_userID`);

--
-- Indexes for table `fontis_userhistorybreakdown`
--
ALTER TABLE `fontis_userhistorybreakdown`
  ADD PRIMARY KEY (`history_breakoutID`),
  ADD KEY `history_prodID` (`history_prodID`),
  ADD KEY `history_cartID` (`history_cartID`),
  ADD KEY `history_userID` (`history_userID`);

--
-- Indexes for table `fontis_userprofiles`
--
ALTER TABLE `fontis_userprofiles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email_address` (`email_address`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fontis_useradminbreakdown`
--
ALTER TABLE `fontis_useradminbreakdown`
  MODIFY `admin_breakdownID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=287;

--
-- AUTO_INCREMENT for table `fontis_useradminhistory`
--
ALTER TABLE `fontis_useradminhistory`
  MODIFY `admin_historyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `fontis_useradminorders`
--
ALTER TABLE `fontis_useradminorders`
  MODIFY `admin_prodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=168;

--
-- AUTO_INCREMENT for table `fontis_usercarts`
--
ALTER TABLE `fontis_usercarts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=590;

--
-- AUTO_INCREMENT for table `fontis_usercartscheckout`
--
ALTER TABLE `fontis_usercartscheckout`
  MODIFY `admin_cartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;

--
-- AUTO_INCREMENT for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  MODIFY `prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=217;

--
-- AUTO_INCREMENT for table `fontis_userhistorybreakdown`
--
ALTER TABLE `fontis_userhistorybreakdown`
  MODIFY `history_breakoutID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `fontis_userprofiles`
--
ALTER TABLE `fontis_userprofiles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fontis_useradminbreakdown`
--
ALTER TABLE `fontis_useradminbreakdown`
  ADD CONSTRAINT `admin_cartuserID_to_id` FOREIGN KEY (`admin_cartuserID`) REFERENCES `fontis_userprofiles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `admin_historyprodID_to_prodID` FOREIGN KEY (`admin_historyprodID`) REFERENCES `fontis_userhistory` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fontis_useradminorders`
--
ALTER TABLE `fontis_useradminorders`
  ADD CONSTRAINT `admin_historyprodIDAdmin_to_prodID` FOREIGN KEY (`admin_historyprodID`) REFERENCES `fontis_userhistory` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `admin_userID_to_id` FOREIGN KEY (`admin_userID`) REFERENCES `fontis_userprofiles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fontis_usercarts`
--
ALTER TABLE `fontis_usercarts`
  ADD CONSTRAINT `AddToCart` FOREIGN KEY (`cart_userID`) REFERENCES `fontis_userprofiles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fontis_usercartscheckout`
--
ALTER TABLE `fontis_usercartscheckout`
  ADD CONSTRAINT `cart_userID_to_ID` FOREIGN KEY (`cart_userID`) REFERENCES `fontis_userprofiles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  ADD CONSTRAINT `history_userID_to_id` FOREIGN KEY (`history_userID`) REFERENCES `fontis_userprofiles` (`id`);

--
-- Constraints for table `fontis_userhistorybreakdown`
--
ALTER TABLE `fontis_userhistorybreakdown`
  ADD CONSTRAINT `history_prodID_to_prodID` FOREIGN KEY (`history_prodID`) REFERENCES `fontis_userhistory` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
