-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Jul 2023 pada 22.17
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `kd_buku` varchar(10) NOT NULL,
  `isbn` varchar(18) DEFAULT NULL,
  `judul_buku` varchar(254) DEFAULT NULL,
  `penulis` varchar(254) DEFAULT NULL,
  `penerbit` varchar(254) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`kd_buku`, `isbn`, `judul_buku`, `penulis`, `penerbit`, `status`) VALUES
('AA000.A1', '978-979-29-2759-7', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'Arief, M.Rudyannto', 'CV. ANDI OFFSET', 'Tersedia'),
('AA000.A2', '978-979-29-2759-7', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'Arief, M.Rudyannto', 'CV. ANDI OFFSET', 'Tidak Tersedia'),
('AA000.A3', '978-979-29-2759-7', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'Arief, M.Rudyannto', 'CV. ANDI OFFSET', 'Tersedia'),
('AB000.B1', '979-756-000-7', 'PEMROGAMAN BERORIENTASI OBJEK DENGAN JAVA', 'Sutopo, Ariesto Hadi', 'GRAHA ILMU', 'Tersedia'),
('AB001.B1', '978-602-8759-07-6', 'MODUL PEMBELAJARAN PEMROGAMAN BERORIENTASI OBJEK DENGAN BAHASA PEMROGRAMAN C++, PHP DAN JAVA', 'Rosa', 'MODULA', 'Tersedia'),
('AC000.C1', '978-602-8758-42-0', 'PEMROGAMAN C DAN IMPLEMENTASINYA', 'Raharjo, Budi', 'INFORMATIKA', 'Tidak Tersedia'),
('AC000.C2', '978-602-8988-42-0', 'PEMROGAMAN C++ DAN IMPLEMENTASINYA', 'Raharjo, Budi', 'INFORMATIKA', 'Tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(12) NOT NULL,
  `nm_mhs` varchar(50) DEFAULT NULL,
  `jns_kelamin` varchar(50) DEFAULT NULL,
  `tlp` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `jurusan` varchar(50) DEFAULT NULL,
  `fakultas` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nm_mhs`, `jns_kelamin`, `tlp`, `email`, `jurusan`, `fakultas`) VALUES
('A11200013439', 'Tata', 'Perempuan', '0984072939', 'tata@mhs.dinus.ac.id', 'Teknik Informatika', 'Ilmu Komputer'),
('A11202113439', 'Gata', 'Perempuan', '085740879323', 'gata@mhs.dinus.ac.id', 'Teknik Informatika', 'Ilmu Komputer'),
('A11202113457', 'Mahsa', 'Perempuan', '086740459123', 'mahsa@mhs.dinus.ac.id', 'Teknik Informatika', 'Ilmu Komputer'),
('A11202113475', 'Laila', 'Perempuan', '081763429666', 'lala@mhs.dinus.ac.id', 'Teknik Informatika', 'Ilmu Komputer'),
('A11202113489', 'Suri', 'Perempuan', '088040480480', 'suri@mhs.dinus.ac.id', 'Teknik Informatika', 'Ilmu Komputer');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `kd_pinjam` varchar(50) NOT NULL,
  `nim` varchar(12) NOT NULL,
  `nm_mhs` varchar(50) DEFAULT NULL,
  `kd_buku` varchar(10) DEFAULT NULL,
  `judul_buku` varchar(254) DEFAULT NULL,
  `tgl_pinjam` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`kd_pinjam`, `nim`, `nm_mhs`, `kd_buku`, `judul_buku`, `tgl_pinjam`) VALUES
('DINUS071023001', 'A11202113439', 'Gata', 'AB000.B1', 'PEMROGAMAN BERORIENTASI OBJEK DENGAN JAVA', '2023-07-11'),
('DINUS071023002', 'A11202113489', 'Suri', 'AA000.A1', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', '2023-07-08'),
('DINUS071023003', 'A11202113475', 'Laila', 'AC000.C2', 'PEMROGAMAN C++ DAN IMPLEMENTASINYA', '2023-07-10'),
('DINUS0712', 'A11202113475', 'Laila', 'AC000.C2', 'PEMROGAMAN C++ DAN IMPLEMENTASINYA', '2023-07-13');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `kd_kembali` varchar(50) NOT NULL,
  `nim` varchar(12) NOT NULL,
  `nm_mhs` varchar(50) DEFAULT NULL,
  `kd_buku` varchar(10) DEFAULT NULL,
  `judul_buku` varchar(254) DEFAULT NULL,
  `kd_pinjam` varchar(50) NOT NULL,
  `tgl_pinjam` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`kd_kembali`, `nim`, `nm_mhs`, `kd_buku`, `judul_buku`, `kd_pinjam`, `tgl_pinjam`, `tgl_kembali`) VALUES
('DINUS120723001', 'A11202113489', 'Suri', 'AA000.A1', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'DINUS071023002', '2023-07-08', '2023-07-12'),
('kembali01', 'A11202113475', 'Laila', 'AC000.C2', 'PEMROGAMAN C++ DAN IMPLEMENTASINYA', 'DINUS071023003', '2023-07-10', '2023-07-13');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengunjung`
--

CREATE TABLE `pengunjung` (
  `nim` varchar(12) NOT NULL,
  `nm_mhs` varchar(50) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengunjung`
--

INSERT INTO `pengunjung` (`nim`, `nm_mhs`, `tanggal`) VALUES
('A11202113439', 'Gata', '2023-07-11'),
('A11202113457', 'Mahsa', '2023-07-11'),
('A11202113475', 'Laila', '2023-07-20'),
('A11202113489', 'Suri', '2023-07-11');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `kd_transaksi` varchar(50) NOT NULL,
  `kd_kembali` varchar(50) NOT NULL,
  `nim` varchar(12) NOT NULL,
  `nm_mhs` varchar(50) DEFAULT NULL,
  `kd_buku` varchar(10) DEFAULT NULL,
  `judul_buku` varchar(254) DEFAULT NULL,
  `kd_pinjam` varchar(50) NOT NULL,
  `tgl_pinjam` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `jml` int(50) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`kd_transaksi`, `kd_kembali`, `nim`, `nm_mhs`, `kd_buku`, `judul_buku`, `kd_pinjam`, `tgl_pinjam`, `tgl_kembali`, `jml`, `total`) VALUES
('coba1', 'DINUS120723001', 'A11202113489', 'Suri', 'AA000.A1', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'DINUS071023002', '2023-07-08', '2023-07-12', 2, 10000),
('coba2', 'DINUS120723001', 'A11202113489', 'Suri', 'AA000.A1', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'DINUS071023002', '2023-07-08', '2023-07-12', 5, 25000),
('cobacoba', 'DINUS120723001', 'A11202113489', 'Suri', 'AA000.A1', 'PEMROGAMAN WEB DINAMIS MENGGUNAKAN PHP DAN MYSQL', 'DINUS071023002', '2023-07-08', '2023-07-12', 3, 15000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `nip` int(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`nip`, `name`, `jabatan`, `password`) VALUES
(13439, 'gata', 'mahasiswa', 'pbo123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kd_buku`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`kd_pinjam`),
  ADD KEY `kd_buku` (`kd_buku`),
  ADD KEY `peminjaman_ibfk_2` (`nim`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`kd_kembali`),
  ADD KEY `kd_buku` (`kd_buku`),
  ADD KEY `kd_pinjam` (`kd_pinjam`),
  ADD KEY `nim` (`nim`);

--
-- Indeks untuk tabel `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`kd_transaksi`),
  ADD KEY `kd_kembali` (`kd_kembali`),
  ADD KEY `kd_pinjam` (`kd_pinjam`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kd_buku` (`kd_buku`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`nip`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`kd_buku`) REFERENCES `buku` (`kd_buku`),
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`);

--
-- Ketidakleluasaan untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD CONSTRAINT `pengembalian_ibfk_1` FOREIGN KEY (`kd_buku`) REFERENCES `buku` (`kd_buku`),
  ADD CONSTRAINT `pengembalian_ibfk_2` FOREIGN KEY (`kd_pinjam`) REFERENCES `peminjaman` (`kd_pinjam`),
  ADD CONSTRAINT `pengembalian_ibfk_3` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`kd_kembali`) REFERENCES `pengembalian` (`kd_kembali`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`kd_pinjam`) REFERENCES `peminjaman` (`kd_pinjam`),
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`),
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`kd_buku`) REFERENCES `buku` (`kd_buku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
