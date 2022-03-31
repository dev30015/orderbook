DROP DATABASE IF EXISTS `orderbook`;
CREATE DATABASE IF NOT EXISTS `orderbook`;
USE `orderbook`;

CREATE TABLE `users`(
    `uid`               BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`              VARCHAR(50) NOT NULL
);

INSERT INTO `users` VALUES
(1, "Eric"),
(2, "Alan");

CREATE TABLE `orders`(
    `oid`                  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `content`              VARCHAR(80) NOT NULL,
    `price`                FLOAT(2)    NOT NULL,
    `ordered_by`           BIGINT      NOT NULL,
    `order_placed`         DATETIME   NOT NULL,
    `order_execution_date` DATETIME,
    `executed`             BOOLEAN,
    FOREIGN KEY (`ordered_by`) REFERENCES `users`(`uid`)
);

INSERT INTO `orders` VALUES
(1, "Buy order", "100.00", 1, "2000-1-01 13:00:00", "2000-1-01 13:00:00", TRUE),
(2, "Buy order", "150.00", 1, "2000-2-01 13:00:00", "2000-2-01 13:00:00", TRUE),
(3, "Sell order", "350.00", 1, "2000-3-01 13:00:00", "2000-3-01 13:00:00", TRUE),
(4, "Sell order", "200.00", 1, "2000-5-01 13:00:00", NULL, FALSE),
(5, "Sell order", "250.00", 1, "2000-5-01 13:00:00", NULL, FALSE),
(6, "Buy order", "100.00", 2, "2001-1-01 13:00:00", "2001-1-01 13:00:00", TRUE),
(7, "Buy order", "350.00", 2, "2001-2-01 13:00:00", "2001-2-01 13:00:00", TRUE),
(8, "Sell order", "200.00", 2, "2001-3-01 13:00:00", "2001-3-01 13:00:00", TRUE),
(9, "Sell order", "350.00", 2, "2001-5-01 13:00:00", NULL, FALSE),
(10, "Sell order", "800.00", 2, "2001-5-01 13:00:00", NULL, FALSE),
(11, "Buy order", "1000.00", 1, "2002-1-01 13:00:00", "2002-1-01 13:00:00", TRUE),
(12, "Buy order", "1500.00", 1, "2002-2-01 13:00:00", "2002-2-01 13:00:00", TRUE),
(13, "Sell order", "1200.00", 1, "2002-3-01 13:00:00", "2002-3-01 13:00:00", TRUE),
(14, "Sell order", "1300.00", 1, "2002-5-01 13:00:00", NULL, FALSE),
(15, "Sell order", "1500.00", 1, "2002-5-01 13:00:00", NULL, FALSE),
(16, "Buy order", "1300.00", 2, "2002-1-01 13:00:00", "2002-1-01 13:00:00", TRUE),
(17, "Buy order", "500.00", 2, "2002-2-01 13:00:00", "2002-2-01 13:00:00", TRUE),
(18, "Sell order", "600.00", 2, "2002-3-01 13:00:00", "2002-3-01 13:00:00", TRUE),
(19, "Sell order", "700.00", 2, "2002-5-01 13:00:00", NULL, FALSE),
(20, "Sell order", "750.00", 2, "2002-5-01 13:00:00", NULL, FALSE);
