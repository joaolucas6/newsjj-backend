CREATE TABLE `tb_news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `text` varchar(10000) DEFAULT NULL,
  `title` varchar(300) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmpjyo1lfs521k649owob8qp1j` (`author_id`),
  CONSTRAINT `FKmpjyo1lfs521k649owob8qp1j` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

