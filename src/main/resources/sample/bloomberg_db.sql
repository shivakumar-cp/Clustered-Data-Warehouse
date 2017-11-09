-- Table structure for table `accumulative_deals`

DROP TABLE IF EXISTS `accumulative_deals`;

CREATE TABLE `accumulative_deals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordering_currency` varchar(128) DEFAULT NULL,
  `count` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Table structure for table `invalid_deal`

DROP TABLE IF EXISTS `invalid_deal`;

CREATE TABLE `invalid_deal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_currency_iso_code` varchar(128) DEFAULT NULL,
  `to_currency_iso_code` varchar(128) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deal_amount` double DEFAULT '0',
  `file_name` varchar(256) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Table structure for table `valid_deal`

DROP TABLE IF EXISTS `valid_deal`;

CREATE TABLE `valid_deal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_currency_iso_code` varchar(128) DEFAULT NULL,
  `to_currency_iso_code` varchar(128) DEFAULT NULL,
  `deal_amount` double DEFAULT NULL,
  `file_name` varchar(256) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2187826 DEFAULT CHARSET=utf8;
