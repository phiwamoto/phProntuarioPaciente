CREATE TABLE `perfis` (
  `pessoa_id` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL,
  KEY `FKsobr8hl9guwr8775lyl1mncg2` (`pessoa_id`),
  CONSTRAINT `FKsobr8hl9guwr8775lyl1mncg2` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;