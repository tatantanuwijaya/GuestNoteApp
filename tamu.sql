-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 15, 2022 at 03:46 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tamu`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftarhadiah_table`
--

CREATE TABLE `daftarhadiah_table` (
  `ID` int(10) NOT NULL,
  `NAMA` text NOT NULL,
  `ALAMAT` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `daftartamu_table`
--

CREATE TABLE `daftartamu_table` (
  `ID` int(20) NOT NULL,
  `NAMA` text NOT NULL,
  `UANG` int(20) NOT NULL,
  `BERAS` int(10) NOT NULL,
  `OPAK` int(10) NOT NULL,
  `PISANG` int(10) NOT NULL,
  `RANGINANG` int(10) NOT NULL,
  `WAJIT` int(10) NOT NULL,
  `KUEALI` int(11) NOT NULL,
  `STATUS` text NOT NULL,
  `LAIN` text NOT NULL,
  `ALAMAT` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `syukuran_table`
--

CREATE TABLE `syukuran_table` (
  `ID` int(10) NOT NULL,
  `NAMA` text NOT NULL,
  `BERAS` int(20) NOT NULL,
  `LAIN` text NOT NULL,
  `ALAMAT` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
