CREATE TABLE `tb_news_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `news_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsb5xjsmjkja30cus3ylm8aoc7` (`author_id`),
  KEY `FKossr7pe00xc814fxffvpfi5pp` (`news_id`),
  CONSTRAINT `FKossr7pe00xc814fxffvpfi5pp` FOREIGN KEY (`news_id`) REFERENCES `tb_news` (`id`),
  CONSTRAINT `FKsb5xjsmjkja30cus3ylm8aoc7` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

