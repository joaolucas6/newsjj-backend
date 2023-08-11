CREATE TABLE `tb_news_dislike` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `news_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdh9my9f5hy4j2uurhexp90bd9` (`author_id`),
  KEY `FK2t5chgi56bd0ec98qlrn5r6a6` (`news_id`),
  CONSTRAINT `FK2t5chgi56bd0ec98qlrn5r6a6` FOREIGN KEY (`news_id`) REFERENCES `tb_news` (`id`),
  CONSTRAINT `FKdh9my9f5hy4j2uurhexp90bd9` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

