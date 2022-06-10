-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2022 at 07:50 PM
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
(224, 190, 524, 11),
(225, 190, 525, 11),
(226, 190, 526, 11),
(227, 191, 527, 11),
(228, 191, 528, 11),
(229, 192, 529, 10),
(230, 192, 530, 10),
(231, 192, 531, 10),
(232, 193, 532, 10),
(233, 193, 533, 10),
(234, 193, 534, 10);

-- --------------------------------------------------------

--
-- Table structure for table `fontis_useradminhistory`
--

CREATE TABLE `fontis_useradminhistory` (
  `admin_historyID` int(11) NOT NULL,
  `admin_historyprodID` int(11) NOT NULL,
  `admin_userID` int(11) NOT NULL,
  `admin_prodDate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_useradminhistory`
--

INSERT INTO `fontis_useradminhistory` (`admin_historyID`, `admin_historyprodID`, `admin_userID`, `admin_prodDate`) VALUES
(33, 190, 11, '09-6-2022'),
(34, 192, 10, '09-6-2022'),
(35, 191, 11, '10-6-2022'),
(36, 193, 10, '09-6-2022');

-- --------------------------------------------------------

--
-- Table structure for table `fontis_useradminorders`
--

CREATE TABLE `fontis_useradminorders` (
  `admin_prodID` int(11) NOT NULL,
  `admin_historyprodID` int(11) NOT NULL,
  `admin_userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(117, 524, 11, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230933', '190', 'not delivered'),
(118, 525, 11, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230933', '190', 'not delivered'),
(119, 526, 11, 3, 149, 'Fontis 5 Gallon Mineral Water(Slim container)', 'Refreshing natural mineral water in a slim container with faucet for portability.', 1, '2131230934', '190', 'not delivered'),
(120, 527, 11, 18, 169, 'Fontis 24 ounce Stainless Steel Bottle', 'Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.', 1, '2131230932', '191', 'not delivered'),
(121, 528, 11, 17, 30, 'Fontis 16 ounce infused Water', 'A refreshing and healthy twist to your good old thirst quencher.(comes in different flavors: lime, strawberry, apple, kiwi, cucumber & peach)', 1, '2131230931', '191', 'not delivered'),
(122, 529, 10, 4, 75, 'Fontis 2.5 Gallon Mineral Water', 'Compact water container with faucet, good for carrying on picnics and outdoor activities.', 1, '2131230935', '192', 'not delivered'),
(123, 530, 10, 3, 149, 'Fontis 5 Gallon Mineral Water(Slim container)', 'Refreshing natural mineral water in a slim container with faucet for portability.', 1, '2131230934', '192', 'not delivered'),
(124, 531, 10, 5, 68, 'Fontis 5 liter Mineral Water', 'Portable water container for quick thirst quencher and refreshment.', 1, '2131230936', '192', 'not delivered'),
(125, 532, 10, 6, 195, '3 pack Fontis 5 liter Mineral Water', 'If one is not enough, add more refreshing natural mineral water for quick thirst quencher and refreshment.', 1, '2131230937', '193', 'not delivered'),
(126, 533, 10, 5, 68, 'Fontis 5 liter Mineral Water', 'Portable water container for quick thirst quencher and refreshment.', 1, '2131230936', '193', 'not delivered'),
(127, 534, 10, 4, 75, 'Fontis 2.5 Gallon Mineral Water', 'Compact water container with faucet, good for carrying on picnics and outdoor activities.', 1, '2131230935', '193', 'not delivered');

-- --------------------------------------------------------

--
-- Table structure for table `fontis_userhistory`
--

CREATE TABLE `fontis_userhistory` (
  `prod_id` int(11) NOT NULL,
  `history_userID` int(11) NOT NULL,
  `prod_price` int(11) NOT NULL,
  `prod_date` varchar(255) NOT NULL,
  `prod_amt` int(11) NOT NULL,
  `prod_img` varchar(255) NOT NULL,
  `prod_adminAccepted` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_userhistory`
--

INSERT INTO `fontis_userhistory` (`prod_id`, `history_userID`, `prod_price`, `prod_date`, `prod_amt`, `prod_img`, `prod_adminAccepted`) VALUES
(190, 11, 339, '09-6-2022', 3, '2131165281', 'true'),
(191, 11, 199, '09-6-2022', 2, '2131165281', 'true'),
(192, 10, 292, '09-6-2022', 3, '2131165281', 'true'),
(193, 10, 338, '09-6-2022', 3, '2131165281', 'true');

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
  `birthdate` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `employment_status` varchar(255) NOT NULL,
  `marital_status` varchar(255) NOT NULL,
  `user_description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fontis_userprofiles`
--

INSERT INTO `fontis_userprofiles` (`id`, `username`, `password`, `first_name`, `last_name`, `contact_no`, `address`, `birthdate`, `email_address`, `employment_status`, `marital_status`, `user_description`) VALUES
(4, 'Zeke2', '4297f44b13955235245b2497399d7a93', 'Ezekiel', 'Ezeke', '123123', '59 Acacia st.', '11/23/2001', 'myemail@gmail.com', 'Employed', 'Married', ''),
(5, 'Try', '4297f44b13955235245b2497399d7a93', 'Kiel', 'Lmao', '123123', '59 Acacia', '11/23/2001', 'smir@gmail.com', 'Employed', 'Not YET', ''),
(8, 'Jabarcs', '06f90940b05fea6fb55410443d0883f0', 'Jabs', 'Barcs', '123123', 'Makati City Taguig', '11/23/2001', 'jabarcs@gmail.com', 'Employed', 'Single', ''),
(10, 'MarkBonks', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Mark', 'Bonks', '0958182472475', '69 Acacia st. Cembo, Makati City.', '11/23/2004', 'yo@gmail.com', 'Employedment', 'Single', 'hahaha'),
(11, 'Decorus', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Ezekiel', 'Ojerio', '09562294726', '59 Acacia st. Cembo, Makati City', '11/23/2001', 'ojerioezekiel@gmail.com', 'Employed', 'Single', 'TestTest'),
(12, 'itlognamaalat21', '33f5a7e8f4f310f9774894d807728e3c', 'Ezekiel', 'Ojerio', '09562294726', '126 West Boulevard, Los Angeles, California', '11/23/1996', 'joemama@gmail.com', 'Self-Employed', 'Single', ''),
(13, 'HeyBarbara', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Hey', 'Barbara', '095124819248', 'Beverly Ave', '06/13/2020', 'Dancingnow@gmail.com', 'Unemployed', 'Separated', ''),
(14, 'Fontis', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Ben', 'Lucido', '0929025854', 'California Street', 'August 25, 2001', 'fontis@gmail.com', 'Self-Employed', 'Married', 'Ako si Nomar');

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
  MODIFY `admin_breakdownID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=235;

--
-- AUTO_INCREMENT for table `fontis_useradminhistory`
--
ALTER TABLE `fontis_useradminhistory`
  MODIFY `admin_historyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `fontis_useradminorders`
--
ALTER TABLE `fontis_useradminorders`
  MODIFY `admin_prodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `fontis_usercarts`
--
ALTER TABLE `fontis_usercarts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=535;

--
-- AUTO_INCREMENT for table `fontis_usercartscheckout`
--
ALTER TABLE `fontis_usercartscheckout`
  MODIFY `admin_cartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  MODIFY `prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=194;

--
-- AUTO_INCREMENT for table `fontis_userprofiles`
--
ALTER TABLE `fontis_userprofiles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
