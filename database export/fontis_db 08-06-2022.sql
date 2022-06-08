-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2022 at 10:53 AM
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
(25, 88, 356, 11),
(26, 88, 357, 11),
(27, 88, 358, 11);

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
(31, 88, 11);

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

--
-- Dumping data for table `fontis_usercarts`
--

INSERT INTO `fontis_usercarts` (`cart_id`, `cart_userID`, `cart_prodTag`, `cart_prodPrice`, `cart_prodName`, `cart_prodDesc`, `cart_prodQty`, `cart_prodImg`) VALUES
(356, 11, 1, 35, 'Fontis 5 Gallon Mineral Water(refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230937'),
(357, 11, 2, 155, 'Fontis 5 Gallon Mineral Water(non-refill)', 'Refreshing natural mineral water in a round container best used for water dispensers.', 1, '2131230937'),
(358, 11, 3, 149, 'Fontis 5 Gallon Mineral Water(Slim container)', 'Refreshing natural mineral water in a slim container with faucet for portability.', 1, '2131230945');

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
(88, 11, 339, '08-6-2022', 3, '2131165281', 'false');

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
(13, 'HeyBarbara', '7c6f5bdc16b3748b481fb5ea98bd4ace', 'Hey', 'Barbara', '095124819248', 'Beverly Ave', '06/13/2020', 'Dancingnow@gmail.com', 'Unemployed', 'Separated', '');

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
  MODIFY `admin_breakdownID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `fontis_useradminorders`
--
ALTER TABLE `fontis_useradminorders`
  MODIFY `admin_prodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `fontis_usercarts`
--
ALTER TABLE `fontis_usercarts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=359;

--
-- AUTO_INCREMENT for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  MODIFY `prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `fontis_userprofiles`
--
ALTER TABLE `fontis_userprofiles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fontis_useradminbreakdown`
--
ALTER TABLE `fontis_useradminbreakdown`
  ADD CONSTRAINT `admin_cartID_to_cartID` FOREIGN KEY (`admin_cartID`) REFERENCES `fontis_usercarts` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
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
-- Constraints for table `fontis_userhistory`
--
ALTER TABLE `fontis_userhistory`
  ADD CONSTRAINT `history_userID_to_id` FOREIGN KEY (`history_userID`) REFERENCES `fontis_userprofiles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
