-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2020 at 03:13 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `covid19`
--

-- --------------------------------------------------------

--
-- Table structure for table `daerah`
--

CREATE TABLE `daerah` (
  `id` int(11) NOT NULL,
  `kode` varchar(20) NOT NULL,
  `provinsi` varchar(100) NOT NULL,
  `kota` varchar(100) NOT NULL,
  `zona` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daerah`
--

INSERT INTO `daerah` (`id`, `kode`, `provinsi`, `kota`, `zona`) VALUES
(1, 'JOG', 'DIY', 'Yogyakarta', 'Merah'),
(2, 'SLMN', 'DIY', 'Sleman', 'Merah'),
(3, 'BNTL', 'DIY', 'Bantul', 'Merah'),
(4, 'KP', 'DIY', 'Kulon Progo', 'Hijau'),
(5, 'GK', 'DIY', 'Gunung Kidul', 'Hijau'),
(6, 'BGR', 'Jawa Barat', 'Bogor', 'Merah'),
(7, 'DPK', 'Jawa Barat', 'Depok', 'Merah'),
(8, 'BDG', 'Jawa Barat', 'Bandung', 'Merah');

-- --------------------------------------------------------

--
-- Table structure for table `status_orang`
--

CREATE TABLE `status_orang` (
  `id` int(11) NOT NULL,
  `id_daerah` int(11) NOT NULL,
  `positif` int(11) NOT NULL,
  `pdp` int(11) NOT NULL,
  `odp` int(11) NOT NULL,
  `tgl_update` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_orang`
--

INSERT INTO `status_orang` (`id`, `id_daerah`, `positif`, `pdp`, `odp`, `tgl_update`) VALUES
(1, 1, 0, 0, 0, '2020-05-15 06:06:05'),
(2, 2, 0, 0, 0, '2020-05-15 06:06:21'),
(3, 3, 0, 0, 0, '2020-05-15 06:06:32'),
(4, 4, 0, 0, 0, '2020-05-15 06:06:50'),
(5, 5, 0, 0, 0, '2020-05-15 06:07:01'),
(6, 1, 0, 3, 7, '2020-05-15 06:07:43'),
(7, 2, 2, 5, 10, '2020-05-15 06:08:00'),
(8, 3, 1, 5, 8, '2020-05-15 06:08:13'),
(9, 4, 1, 7, 8, '2020-05-15 06:08:35'),
(10, 5, 0, 2, 11, '2020-05-15 06:08:52'),
(11, 1, 3, 13, 20, '2020-05-15 07:47:24'),
(12, 4, 0, 7, 10, '2020-05-15 08:02:53'),
(13, 6, 0, 0, 0, '2020-05-15 08:04:28'),
(14, 6, 5, 20, 33, '2020-05-15 08:04:51'),
(15, 7, 0, 0, 0, '2020-05-15 08:08:10'),
(16, 7, 17, 19, 45, '2020-05-15 08:08:41'),
(17, 8, 0, 0, 0, '2020-05-15 08:10:14'),
(18, 8, 8, 17, 19, '2020-05-15 08:11:01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daerah`
--
ALTER TABLE `daerah`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode` (`kode`);

--
-- Indexes for table `status_orang`
--
ALTER TABLE `status_orang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_daerah` (`id_daerah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daerah`
--
ALTER TABLE `daerah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `status_orang`
--
ALTER TABLE `status_orang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `status_orang`
--
ALTER TABLE `status_orang`
  ADD CONSTRAINT `id_daerah` FOREIGN KEY (`id_daerah`) REFERENCES `daerah` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
