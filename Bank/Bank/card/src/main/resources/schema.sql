CREATE TABLE IF NOT EXISTS `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(15) NOT NULL, UNIQUE(customer_email),
  `card_number` varchar(100) NOT NULL, UNIQUE(card_number),
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `available_amount` int NOT NULL,
  PRIMARY KEY (`card_id`)
);