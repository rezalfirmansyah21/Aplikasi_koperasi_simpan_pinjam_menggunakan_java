-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Apr 2021 pada 00.45
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `koperasi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(128) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama_admin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `nama_admin`) VALUES
(1, 'arief', '121212', 'Arief');

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `id` varchar(128) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_telp` varchar(50) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`id`, `nama`, `no_telp`, `pekerjaan`, `alamat`, `tanggal`) VALUES
('A0001', 'Mahfud', '081290871290', 'Karyawan Swasta', 'Depok', '2020-07-08'),
('A0002', 'Samsul', '081290789012', 'Wiraswasta', 'Depok', '2020-07-08');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenispinjaman`
--

CREATE TABLE `jenispinjaman` (
  `no` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `maxpinjaman` int(50) NOT NULL,
  `maxangsuran` int(50) NOT NULL,
  `bunga` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jenispinjaman`
--

INSERT INTO `jenispinjaman` (`no`, `jenis`, `maxpinjaman`, `maxangsuran`, `bunga`, `tanggal`) VALUES
('K0001', 'Pinjaman Pokok', 30000000, 30, 10, '2020-06-28'),
('K0002', 'Pinjaman Mahasiswa', 20000000, 20, 5, '2020-06-28'),
('K0003', 'Pinjaman Usaha', 50000000, 50, 15, '2020-06-28');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE `pembayaran` (
  `notransaksi` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `angsuran` int(50) NOT NULL,
  `bayar` int(50) NOT NULL,
  `kembalian` int(50) NOT NULL,
  `sisaangsuran` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pembayaran`
--

INSERT INTO `pembayaran` (`notransaksi`, `id`, `nama`, `jenis`, `angsuran`, `bayar`, `kembalian`, `sisaangsuran`, `tanggal`) VALUES
('P0001', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 1050000, 1050000, 0, 15, '2020-07-06'),
('P0002', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 1050000, 2100000, 0, 13, '2020-07-07'),
('P0003', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 1050000, 1050000, 0, 12, '2020-07-07'),
('P0004', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 1050000, 1050000, 0, 19, '2020-07-08'),
('P0005', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 1050000, 1050000, 0, 19, '2020-07-18');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjaman`
--

CREATE TABLE `pinjaman` (
  `notransaksi` varchar(128) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `maxpinjaman` int(50) NOT NULL,
  `maxangsuran` int(50) NOT NULL,
  `bunga` int(50) NOT NULL,
  `angsuran` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pinjaman`
--

INSERT INTO `pinjaman` (`notransaksi`, `id`, `nama`, `jenis`, `maxpinjaman`, `maxangsuran`, `bunga`, `angsuran`, `tanggal`) VALUES
('P0001', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 20000000, 18, 5, 1050000, '2020-07-08'),
('P0002', 'A0002', 'Samsul', 'Pinjaman Usaha', 50000000, 50, 15, 1150000, '2020-07-08'),
('P0003', 'A0001', 'Mahfud', 'Pinjaman Mahasiswa', 20000000, 19, 5, 1050000, '2020-07-18');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rekening`
--

CREATE TABLE `rekening` (
  `id` varchar(128) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `saldo` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `rekening`
--

INSERT INTO `rekening` (`id`, `nama`, `saldo`, `tanggal`) VALUES
('A0001', 'Mahfud', '200000', '2020-07-08'),
('A0002', 'Samsul', '100000', '2020-07-08');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tmp_pembayaran`
--

CREATE TABLE `tmp_pembayaran` (
  `no` int(128) NOT NULL,
  `notransaksi` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `bayar` int(50) NOT NULL,
  `kembalian` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `tmp_pembayaran`
--
DELIMITER $$
CREATE TRIGGER `bayar_pinjaman` AFTER INSERT ON `tmp_pembayaran` FOR EACH ROW BEGIN 
UPDATE pinjaman SET maxangsuran = maxangsuran - new.jumlah 
WHERE id = new.id; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksipenarikan`
--

CREATE TABLE `transaksipenarikan` (
  `notransaksi` varchar(128) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jumlahpenarikan` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksipenarikan`
--

INSERT INTO `transaksipenarikan` (`notransaksi`, `id`, `nama`, `jumlahpenarikan`, `tanggal`) VALUES
('S0001', 'A0001', 'Mahfud', 10000, '2020-07-07'),
('S0002', 'A0001', 'Mahfud', 90000, '2020-07-07'),
('S0003', 'A0001', 'Mahfud', 10000, '2020-07-07'),
('S0004', 'A0001', 'Mahfud', 10000, '2020-07-07'),
('S0005', 'A0002', 'Angga', 10000, '2020-07-07'),
('S0006', 'A0001', 'Mahfud', 50000, '2020-07-18'),
('S0007', 'A0001', 'Mahfud', 50000, '2020-07-18');

--
-- Trigger `transaksipenarikan`
--
DELIMITER $$
CREATE TRIGGER `ambil_penarikan` AFTER INSERT ON `transaksipenarikan` FOR EACH ROW BEGIN 
UPDATE rekening SET saldo = saldo - new.jumlahpenarikan 
WHERE id = new.id; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksisimpanan`
--

CREATE TABLE `transaksisimpanan` (
  `notransaksi` varchar(128) NOT NULL,
  `id` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jumlahsimpan` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksisimpanan`
--

INSERT INTO `transaksisimpanan` (`notransaksi`, `id`, `nama`, `jumlahsimpan`, `tanggal`) VALUES
('S0001', 'A0001', 'Mahfud', 100000, '2020-06-27'),
('S0002', 'A0001', 'Mahfud', 50000, '2020-06-27'),
('S0003', 'A0001', 'Mahfud', 50000, '2020-06-27'),
('S0004', 'A0001', 'Mahfud', 110000, '2020-07-07'),
('S0005', 'A0001', 'Mahfud', 150000, '2020-07-18'),
('S0006', 'A0001', 'Mahfud', 50000, '2020-07-18');

--
-- Trigger `transaksisimpanan`
--
DELIMITER $$
CREATE TRIGGER `tambah_simpan` AFTER INSERT ON `transaksisimpanan` FOR EACH ROW BEGIN 
UPDATE rekening SET saldo = saldo +  new.jumlahsimpan
WHERE id = new.id; 
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `jenispinjaman`
--
ALTER TABLE `jenispinjaman`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`notransaksi`);

--
-- Indeks untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`notransaksi`);

--
-- Indeks untuk tabel `rekening`
--
ALTER TABLE `rekening`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tmp_pembayaran`
--
ALTER TABLE `tmp_pembayaran`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `transaksipenarikan`
--
ALTER TABLE `transaksipenarikan`
  ADD PRIMARY KEY (`notransaksi`);

--
-- Indeks untuk tabel `transaksisimpanan`
--
ALTER TABLE `transaksisimpanan`
  ADD PRIMARY KEY (`notransaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(128) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tmp_pembayaran`
--
ALTER TABLE `tmp_pembayaran`
  MODIFY `no` int(128) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
