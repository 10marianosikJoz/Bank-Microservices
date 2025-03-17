CREATE TABLE IF NOT EXISTS `loans` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(15) NOT NULL, UNIQUE(customer_email),
  `loan_number` varchar(100) NOT NULL, UNIQUE(loan_number),
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  PRIMARY KEY (`loan_id`)
);