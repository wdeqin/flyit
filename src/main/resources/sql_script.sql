CREATE TABLE `test_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL DEFAULT '',
  `status` char(1) NOT NULL DEFAULT '',
  `class` varchar(35) NOT NULL DEFAULT '',
  `concurrency` tinyint(4) NOT NULL DEFAULT '0',
  `op_size` bigint(20) NOT NULL DEFAULT '0',
  `key_num_1` bigint(20) DEFAULT NULL,
  `key_num_2` bigint(20) DEFAULT NULL,
  `key_num_3` bigint(20) DEFAULT NULL,
  `key_char_1` char(20) DEFAULT NULL,
  `key_char_2` char(20) DEFAULT NULL,
  `key_char_3` char(20) DEFAULT NULL,
  `creation_date` date NOT NULL,
  `creation_time` time NOT NULL,
  `update_date` date DEFAULT NULL,
  `update_time` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
