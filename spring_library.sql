-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 02 Lip 2022, 20:23
-- Wersja serwera: 5.7.24
-- Wersja PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `spring_library`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `authors`
--

CREATE TABLE `authors` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `authors`
--

INSERT INTO `authors` (`id`, `name`) VALUES
(12, 'Maciek Polak'),
(13, 'Paweł Poloński');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publishing_house_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `shopping_cart_books` tinyblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `books`
--

INSERT INTO `books` (`id`, `name`, `publishing_house_id`, `author_id`, `quantity`, `photo`, `price`, `shopping_cart_books`) VALUES
(10, 'W pustyni i w puszczy', 3, 13, 6, 'download.jpg', 10, NULL),
(11, 'Król Lew', 4, 12, 5, 'download.jpg', 20, NULL),
(12, 'Mały Książe', 4, 13, 8, 'i-maly-ksiaze.webp', 100, NULL),
(13, 'Harry Potter', 3, 12, 9, 'download.jpg', 15, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_state`
--

CREATE TABLE `book_state` (
  `id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `publishing_house`
--

CREATE TABLE `publishing_house` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `publishing_house`
--

INSERT INTO `publishing_house` (`id`, `name`) VALUES
(3, 'Helion '),
(4, 'Nowa Era');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(2, 'User'),
(3, 'Admin');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `schopping_carts`
--

CREATE TABLE `schopping_carts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `shopping_cart_status_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `schopping_carts`
--

INSERT INTO `schopping_carts` (`id`, `user_id`, `created_at`, `updated_at`, `shopping_cart_status_id`) VALUES
(1, 7, '2022-07-02 16:25:21.354000', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `schopping_carts_books`
--

CREATE TABLE `schopping_carts_books` (
  `shopping_cart_id` int(11) NOT NULL,
  `books_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `schopping_carts_books`
--

INSERT INTO `schopping_carts_books` (`shopping_cart_id`, `books_id`) VALUES
(1, 10),
(1, 11),
(1, 12);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `shopping_cart_statuses`
--

CREATE TABLE `shopping_cart_statuses` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`, `created_at`, `updated_at`) VALUES
(4, 'jakubowskimichal1210@gmail.com', '$2a$10$Qkz9qI.2IbpgciPF0DXgmu59Ty8Su6sHZPc3tTjOvjqxv.hnLS9Hq', 'mjakubow', NULL, NULL),
(5, 'user@example.com', '$2a$10$cWXNvwDxvznBFsnl4xbLTODcrXzMsLxfpUekLyH3EBaBT6eBGcxQi', 'admin123', '2022-04-26 17:55:38.927000', '2022-04-26 17:55:38.914000'),
(6, 'jakubowskimichal1211@gmail.com', '$2a$10$qXqqRJWZ/xbN9Mo28jpwsedTn029aXsiIUwAaXCQzDEMupNW18giK', 'micjak12', '2022-06-07 21:30:52.758000', '2022-06-07 21:30:52.748000'),
(7, 'pocieszko123@gmail.com', '$2a$10$Gv/v1ue9f/cZL9W9JrzsCuMsrbFzjt4V1v5EdYVh/3ZG3JSgVbpUG', 'pocieszko1', '2022-07-02 14:38:04.217000', '2022-07-02 14:38:04.211000');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `roles_id`) VALUES
(5, 2),
(6, 2),
(7, 2),
(4, 3);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3if4ows52t9oymwflre4ja11l` (`publishing_house_id`),
  ADD KEY `FKfjixh2vym2cvfj3ufxj91jem7` (`author_id`);

--
-- Indeksy dla tabeli `book_state`
--
ALTER TABLE `book_state`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ivxkhl7q611y2xv81dbfbaum` (`book_id`);

--
-- Indeksy dla tabeli `publishing_house`
--
ALTER TABLE `publishing_house`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `schopping_carts`
--
ALTER TABLE `schopping_carts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjsx1ywjhha67j9d1kpd3r9qcf` (`user_id`),
  ADD KEY `FKm1xucg8okkmy05s3ucqy3nvsq` (`shopping_cart_status_id`);

--
-- Indeksy dla tabeli `schopping_carts_books`
--
ALTER TABLE `schopping_carts_books`
  ADD PRIMARY KEY (`shopping_cart_id`,`books_id`),
  ADD UNIQUE KEY `UK_e2xt6nu4848y89laj3vm3puxt` (`books_id`);

--
-- Indeksy dla tabeli `shopping_cart_statuses`
--
ALTER TABLE `shopping_cart_statuses`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`roles_id`),
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `authors`
--
ALTER TABLE `authors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `book_state`
--
ALTER TABLE `book_state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `publishing_house`
--
ALTER TABLE `publishing_house`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `schopping_carts`
--
ALTER TABLE `schopping_carts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `shopping_cart_statuses`
--
ALTER TABLE `shopping_cart_statuses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `FK3if4ows52t9oymwflre4ja11l` FOREIGN KEY (`publishing_house_id`) REFERENCES `publishing_house` (`id`),
  ADD CONSTRAINT `FKfjixh2vym2cvfj3ufxj91jem7` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

--
-- Ograniczenia dla tabeli `book_state`
--
ALTER TABLE `book_state`
  ADD CONSTRAINT `FK9ivxkhl7q611y2xv81dbfbaum` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);

--
-- Ograniczenia dla tabeli `schopping_carts`
--
ALTER TABLE `schopping_carts`
  ADD CONSTRAINT `FKjsx1ywjhha67j9d1kpd3r9qcf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKm1xucg8okkmy05s3ucqy3nvsq` FOREIGN KEY (`shopping_cart_status_id`) REFERENCES `shopping_cart_statuses` (`id`);

--
-- Ograniczenia dla tabeli `schopping_carts_books`
--
ALTER TABLE `schopping_carts_books`
  ADD CONSTRAINT `FK7dopbocmg8401b8doris3rucc` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FKm114hhvwin3u5jvob3dplmth7` FOREIGN KEY (`shopping_cart_id`) REFERENCES `schopping_carts` (`id`);

--
-- Ograniczenia dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
